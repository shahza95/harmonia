package syed.shahza.harmonia.backend.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLecturerDto.aValidLecturerDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import syed.shahza.harmonia.backend.configuration.BackendConfiguration;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackendConfiguration.class)
@EnableAutoConfiguration
public class LoginEndpointIntegrationTest {
	
    @LocalServerPort
    private int randomServerPort;

    @Before
    public void before() {
        RestAssured.port = this.randomServerPort;
    }

    // test login endpoint
    @Test
    public void loginWithIncorrectCredentialsReturnsAuthenticatedFalse() {
        assertThat(given().contentType("application/json").body(aValidLecturerDto().username("").password("").build()).when().post("/lecturer/login").then().extract().asString(), is("false"));
    }
    
    @Test
    public void loginWithCorrectCredentialsReturnsAuthenticatedTrue() {
    	assertThat(given().contentType("application/json").body(aValidLecturerDto().build()).when().post("/lecturer/login").then().extract().asString(), is("true"));
    }
    
    @Test
    public void postRequestToLoginWithFieldsReturns200Ok() {
        given().contentType("application/json").body(aValidLecturerDto().build()).when().post("/lecturer/login").then().statusCode(200);
    }
}
