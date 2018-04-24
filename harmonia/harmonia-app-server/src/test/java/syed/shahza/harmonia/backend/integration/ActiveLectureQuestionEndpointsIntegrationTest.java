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
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestQuestionDto;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackendConfiguration.class)
@EnableAutoConfiguration
public class ActiveLectureQuestionEndpointsIntegrationTest {
	private LectureDto lectureDto;
	private QuestionDto questionDto;
	
    @LocalServerPort
    private int randomServerPort;

    @Before
    public void before() {
        RestAssured.port = this.randomServerPort;
    	this.lectureDto = aValidLectureDto().build();
    	this.questionDto = TestQuestionDto.aValidQuestionDto().lectureDto(lectureDto).build();
    	given().contentType("application/json").body(lectureDto).when().post("/lecturer/lecture/create");
    }

    
    // test question endpoints
    @Test
    public void whenNoQuestionsGettingAllQuestionsReturnsEmptyQuestionsDtoObject200Ok() {
    	given().contentType("application/json").get("lecture/active/" + lectureDto.getTitle() + "/questions").then().assertThat().body("questionDtoList", is(empty())).and().statusCode(200);
    }

    @Test
    public void whenNoQuestionGettingSpecificQuestionReturns500ServerError() {
    	given().contentType("application/json").get("lecture/active/questions/" + questionDto.getId()).then().statusCode(500);
    }
    
    @Test
    public void addingQuestionReturnsQuestion200Ok() {
    	given().contentType("application/json").body(questionDto).when().post("/student/lecture/active/question/add").then().assertThat().body("question", is(questionDto.getQuestion())).and().statusCode(200);
    }
    
    @Test
    public void gettingAllQuestionsReturnsQuestionsDtoObjectWithCorrectQuestion200Ok() {
    	given().contentType("application/json").body(questionDto).when().post("/student/lecture/active/question/add");
    	given().contentType("application/json").get("lecture/active/" + lectureDto.getTitle() + "/questions").then().assertThat().body("questionDtoList.question", hasItems(questionDto.getQuestion())).and().statusCode(200);
    }
    
    @Test
    public void gettingSpecificQuestionReturnsQuestionObject200Ok() {
    	given().contentType("application/json").body(questionDto).when().post("/student/lecture/active/question/add");
    	given().contentType("application/json").get("lecture/active/questions/" + questionDto.getId()).then().assertThat().body("question", is(questionDto.getQuestion())).and().statusCode(200);
    }
    
    @Test
    public void updatingQuestionWithAnswer200Ok() {
    	QuestionDto questionDtoInitiallyNoAnswer = TestQuestionDto.aValidQuestionNoAnswerDto().build();
    	given().contentType("application/json").body(questionDtoInitiallyNoAnswer).when().post("/student/lecture/active/question/add");
    	questionDtoInitiallyNoAnswer.setAnswer("answer");
    	given().contentType("application/json").body(questionDtoInitiallyNoAnswer).when().put("/lecturer/lecture/active/question").then().statusCode(200);
    }
    
    @Test
    public void updatingNonExistentQuestion500ServerError() {
    	questionDto.setAnswer("answer");
    	given().contentType("application/json").body(questionDto).when().put("/lecturer/lecture/active/question").then().statusCode(500);
    }
    
    @Test
    public void disablingQuestioning200Ok() {
    	lectureDto.setQuestionsEnabled(false);
    	given().contentType("application/json").body(lectureDto).when().put("/lecturer/lecture/active").then().statusCode(200);
    }
    
    @Test
    public void enablingQuestioning200Ok() {
    	lectureDto.setQuestionsEnabled(true);
    	given().contentType("application/json").body(lectureDto).when().put("/lecturer/lecture/active").then().statusCode(200);
    }
}
