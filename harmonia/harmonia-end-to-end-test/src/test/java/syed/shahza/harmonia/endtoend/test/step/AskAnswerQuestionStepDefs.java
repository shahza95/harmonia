package syed.shahza.harmonia.endtoend.test.step;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anActiveLectureDto;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.dto.TestQuestionDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.context.ExecutionContext;
import syed.shahza.harmonia.endtoend.test.service.LectureService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class AskAnswerQuestionStepDefs {
	private LectureDto lectureDto = anActiveLectureDto().title(RandomStringUtils.randomAlphabetic(10)).password(RandomStringUtils.randomAlphanumeric(6)).build();
	private QuestionDto questionDto = TestQuestionDto.aValidQuestionDto().question("my question here?").answer(null).lectureDto(this.lectureDto).build();
	private String answer =  "some answer here!";

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ExecutionContext executionContext;
    
    @Given("^I am inside an active lecture question page")
    public void givenLectureIsCreatedAndActive() {
    	this.lectureService.create(this.lectureDto);
    }

    @When("^I enter a question and click the ask button and the lecturer answers")
    public void whenStudentAsksQuestionAndLecturerAnswers() throws InterruptedException {
    	this.lectureService.join(this.lectureDto.getPassword());
        this.lectureService.addQuestion(this.questionDto);
        this.lectureService.answerQuestion(this.questionDto, answer);
    }

    @Then("^I see the question thread with the answer")
    public void thenTheStudentCanSeeTheThreadIncludingTheReply() {
        assertThat(this.lectureService.checkQuestionAnswered(this.questionDto, answer), is(Result.SUCCESS));
    }
}