package syed.shahza.harmonia.endtoend.test.step;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestFeedbackDto.aValidFeedbackDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anActiveLectureDto;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.context.ExecutionContext;
import syed.shahza.harmonia.endtoend.test.service.LectureService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class SubmitEndOfLectureFeedbackStepDefs {
	private LectureDto lectureDto = anActiveLectureDto().title(RandomStringUtils.randomAlphabetic(10)).password(RandomStringUtils.randomAlphanumeric(6)).build();
	private FeedbackDto feedbackDto = aValidFeedbackDto().rating(5).message("my overall lecture feedback message").lectureDto(this.lectureDto).build();

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ExecutionContext executionContext;
    
    @Given("^the lecturer ends the lecturer")
    public void givenTheLecturerEndsAnActiveLecture() {
    	this.lectureService.create(this.lectureDto);
    	this.lectureService.endLecture(this.lectureDto.getTitle());
    }

    @When("^I enter feedback and click submit")
    public void whenStudentGivesEndOfLectureFeedback() {
    	this.lectureService.join(this.lectureDto.getPassword());
        this.lectureService.addFeedback(this.feedbackDto);
    }

    @Then("^the lecturer should see my feedback")
    public void thenTheLectureCanSeeMyFeedback() {
    	this.lectureService.viewAllFeedback(this.lectureDto.getTitle());
        assertThat(this.lectureService.checkFeedbackReceived(this.feedbackDto), is(Result.SUCCESS));
    }
}