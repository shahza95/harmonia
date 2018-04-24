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
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestMoodDto;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackendConfiguration.class)
@EnableAutoConfiguration
public class ActiveLectureMoodEndpointsIntegrationTest {
	private LectureDto lectureDto;
	private MoodDto moodDto;
	
    @LocalServerPort
    private int randomServerPort;

    @Before
    public void before() {
        RestAssured.port = this.randomServerPort;
    	this.lectureDto = aValidLectureDto().build();
    	this.moodDto = TestMoodDto.aValidMoodDto().lectureDto(lectureDto).build();
    	given().contentType("application/json").body(lectureDto).when().post("/lecturer/lecture/create");
    }

    
    // test mood features endpoints
    @Test
    public void whenNoMoodsGettingAllMoodsReturnsEmptyMoodsDtoObject200Ok() {
    	given().contentType("application/json").get("lecture/active/" + lectureDto.getTitle() + "/moods").then().assertThat().body("moodDtoList", is(empty())).and().statusCode(200);
    }
    
    @Test
    public void addingMoodReturnsMood200Ok() {
    	given().contentType("application/json").body(moodDto).when().post("/student/lecture/active/mood").then().assertThat().body("emoji", is(moodDto.getEmoji())).and().statusCode(200);
    }
    
    @Test
    public void removingNonExistentMoodReturnsMood500ServerError() {
    	given().contentType("application/json").pathParams("lectureTitle", lectureDto.getTitle(), "emoji", moodDto.getEmoji()).when().delete("/student/lecture/active/{lectureTitle}/mood/{emoji}").then().statusCode(500);
    }
    
    @Test
    public void removingExistingMoodReturnsMood200Ok() {
    	given().contentType("application/json").body(moodDto).when().post("/student/lecture/active/mood");
    	given().contentType("application/json").pathParams("lectureTitle", lectureDto.getTitle(), "emoji", moodDto.getEmoji()).when().delete("/student/lecture/active/{lectureTitle}/mood/{emoji}").then().statusCode(200);
    }
    
    @Test
    public void gettingAllMoodsReturnsMoodsDtoObjectWithCorrectMood200Ok() {
    	given().contentType("application/json").body(moodDto).when().post("/student/lecture/active/mood");
    	given().contentType("application/json").get("lecture/active/" + lectureDto.getTitle() + "/moods").then().assertThat().body("moodDtoList.emoji", hasItems(moodDto.getEmoji())).and().statusCode(200);
    }
    
    @Test
    public void disablingMooding200Ok() {
    	lectureDto.setMoodEnabled(false);
    	given().contentType("application/json").body(lectureDto).when().put("/lecturer/lecture/active").then().statusCode(200);
    }
    
    @Test
    public void enablingMooding200Ok() {
    	lectureDto.setMoodEnabled(true);
    	given().contentType("application/json").body(lectureDto).when().put("/lecturer/lecture/active").then().statusCode(200);
    }
}
