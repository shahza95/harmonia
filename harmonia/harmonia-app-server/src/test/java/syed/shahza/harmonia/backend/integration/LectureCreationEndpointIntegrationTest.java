package syed.shahza.harmonia.backend.integration;

import static com.jayway.restassured.RestAssured.given;
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
import syed.shahza.harmonia.backend.dto.TestLectureDto;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackendConfiguration.class)
@EnableAutoConfiguration
public class LectureCreationEndpointIntegrationTest {
	
    @LocalServerPort
    private int randomServerPort;

    @Before
    public void before() {
        RestAssured.port = this.randomServerPort;
    }
   
    // test create lecture endpoint
    @Test
    public void createLectureWithAllFieldsReturns200Ok() {
    	given().contentType("application/json").body(aValidLectureDto().build()).when().post("/lecturer/lecture/create").then().statusCode(200);
    }
    
    @Test
    public void createLectureWithMissingFieldsReturns500ServerError() {
    	given().contentType("application/json").body(TestLectureDto.anEmptyLectureDto().build()).when().post("/lecturer/lecture/create").then().statusCode(500);
    }
}
