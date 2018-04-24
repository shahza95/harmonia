package syed.shahza.harmonia.backend.integration;

import static com.jayway.restassured.RestAssured.given;
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
import syed.shahza.harmonia.backend.dto.LectureDto;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackendConfiguration.class)
@EnableAutoConfiguration
public class JoinLectureEndpointIntegrationTest {
	private LectureDto lectureDto;
	
    @LocalServerPort
    private int randomServerPort;

    @Before
    public void before() {
        RestAssured.port = this.randomServerPort;
    	this.lectureDto = aValidLectureDto().build();
    	given().contentType("application/json").body(lectureDto).when().post("/lecturer/lecture/create");
    }

    
    // test join lecture endpoint
    @Test
    public void postRequestToJoinExistingButNotActiveLectureReturnsLectureObjectAnd200Ok() {
    	given().contentType("application/json").body(lectureDto.getPassword()).when().post("/student/lecture/join").then().assertThat().body("title", is(lectureDto.getTitle())).and().statusCode(200);
    }
    
    @Test
    public void postRequestToJoinExistingActiveLectureReturnsLectureObject200Ok() {
    	given().contentType("application/json").body(lectureDto.getPassword()).when().post("/student/lecture/join").then().assertThat().body("title", is(lectureDto.getTitle())).and().statusCode(200);
    }
    
    @Test
    public void postRequestToJoinNonExistentLectureReturns500ServerError() {
    	given().contentType("application/json").body("aPassword").when().post("/student/lecture/join").then().statusCode(500);
    }
}
