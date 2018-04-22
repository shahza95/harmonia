package syed.shahza.harmonia.backend.integration;

import static com.jayway.restassured.RestAssured.given;
import static syed.shahza.harmonia.backend.dto.TestLecturerDto.aValidLecturerDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jayway.restassured.RestAssured;

import syed.shahza.harmonia.backend.configuration.BackendConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackendConfiguration.class)
@EnableAutoConfiguration
public class EndpointIntegrationTest {
    @LocalServerPort
    private int randomServerPort;

    @Before
    public void before() {
        RestAssured.port = this.randomServerPort;
    }

    @Test
    public void postRequestToLoginWithEmptyFieldsReturns200Ok() {
        given().contentType("application/json").body(aValidLecturerDto().username("").password("").build()).when().post("/lecturer/login").then().statusCode(200);
    }
    
    @Test
    public void postRequestToLoginWithFieldsReturns200Ok() {
        given().contentType("application/json").body(aValidLecturerDto().build()).when().post("/lecturer/login").then().statusCode(200);
    }
    
    @Test
    public void postRequestToCreateLectureWithFieldsReturns200Ok() {
    	given().contentType("application/json").body(aValidLectureDto().build()).when().post("/lecturer/lecture/create").then().statusCode(200);
    }
    
    @Test
    public void postRequestToJoinLectureWithFieldsReturns500ServerError() {
    	given().contentType("application/json").body("aPassword").when().post("/student/lecture/join").then().statusCode(500);
    }
}
