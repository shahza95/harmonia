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
import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestCommentDto;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackendConfiguration.class)
@EnableAutoConfiguration
public class ActiveLectureCommentEndpointsIntegrationTest {
	private LectureDto lectureDto;
	private CommentDto commentDto;
	
    @LocalServerPort
    private int randomServerPort;

    @Before
    public void before() {
        RestAssured.port = this.randomServerPort;
    	this.lectureDto = aValidLectureDto().build();
    	this.commentDto = TestCommentDto.aValidCommentDto().lectureDto(lectureDto).build();
    	given().contentType("application/json").body(lectureDto).when().post("/lecturer/lecture/create");
    }

    
    // test commenting features endpoints
    @Test
    public void whenNoCommentsGettingAllCommentsReturnsEmptyCommentsDtoObject200Ok() {
    	given().contentType("application/json").get("lecture/active/" + lectureDto.getTitle() + "/comments").then().assertThat().body("commentDtoList", is(empty())).and().statusCode(200);
    }
    
    @Test
    public void addingCommentReturnsComment200Ok() {
    	given().contentType("application/json").body(commentDto).when().post("/student/lecture/active/comments/add").then().assertThat().body("message", is(commentDto.getMessage())).and().statusCode(200);
    }
    
    @Test
    public void gettingAllCommentsReturnsCommentsDtoObjectWithCorrectComment200Ok() {
    	given().contentType("application/json").body(commentDto).when().post("/student/lecture/active/comments/add");
    	given().contentType("application/json").get("lecture/active/" + lectureDto.getTitle() + "/comments").then().assertThat().body("commentDtoList.message", hasItems(commentDto.getMessage())).and().statusCode(200);
    }
    
    @Test
    public void disablingCommenting200Ok() {
    	lectureDto.setCommentsEnabled(false);
    	given().contentType("application/json").body(lectureDto).when().put("/lecturer/lecture/active").then().statusCode(200);
    }
    
    @Test
    public void enablingCommenting200Ok() {
    	lectureDto.setCommentsEnabled(true);
    	given().contentType("application/json").body(lectureDto).when().put("/lecturer/lecture/active").then().statusCode(200);
    }
}
