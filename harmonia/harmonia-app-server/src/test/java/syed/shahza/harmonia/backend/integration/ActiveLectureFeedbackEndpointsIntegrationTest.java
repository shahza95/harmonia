package syed.shahza.harmonia.backend.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import syed.shahza.harmonia.backend.configuration.BackendConfiguration;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestFeedbackDto;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackendConfiguration.class)
@EnableAutoConfiguration
public class ActiveLectureFeedbackEndpointsIntegrationTest {
	private LectureDto lectureDto;
	private FeedbackDto feedbackDto;
	
    @LocalServerPort
    private int randomServerPort;

    @Before
    public void before() {
        RestAssured.port = this.randomServerPort;
    	this.lectureDto = aValidLectureDto().build();
    	this.feedbackDto = TestFeedbackDto.aValidFeedbackDto().lectureDto(lectureDto).build();
    	given().contentType("application/json").body(lectureDto).when().post("/lecturer/lecture/create");
    }

    
    // test feedback endpoints
    @Test
    public void whenNoFeedbacksGettingAllFeedbacksReturnsEmptyFeedbacksDtoObject200Ok() {
    	given().contentType("application/json").get("lecturer/lecture/active/" + lectureDto.getTitle() + "/feedback").then().assertThat().body("feedbackDtoList", is(empty())).and().statusCode(200);
    }
    
    @Test
    public void addingFeedbackReturnsFeedback200Ok() {
    	given().contentType("application/json").body(feedbackDto).when().post("/student/lecture/active/feedback/add").then().assertThat().body("message", is(feedbackDto.getMessage())).and().statusCode(200);
    }
    
    @Test
    public void gettingAllFeedbacksReturnsFeedbacksDtoObjectWithCorrectFeedback200Ok() {
    	given().contentType("application/json").body(feedbackDto).when().post("/student/lecture/active/feedback/add");
    	given().contentType("application/json").get("lecturer/lecture/active/" + lectureDto.getTitle() + "/feedback").then().assertThat().body("feedbackDtoList.message", hasItems(feedbackDto.getMessage())).and().statusCode(200);
    }
    
    @Test
    public void disablingFeedbacking200Ok() {
    	lectureDto.setFeedbackEnabled(false);
    	given().contentType("application/json").body(lectureDto).when().put("/lecturer/lecture/active").then().statusCode(200);
    }
    
    @Test
    public void enablingFeedbacking200Ok() {
    	lectureDto.setFeedbackEnabled(true);
    	given().contentType("application/json").body(lectureDto).when().put("/lecturer/lecture/active").then().statusCode(200);
    }
}
