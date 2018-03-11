package syed.shahza.harmonia.endtoend.test.step;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anActiveLectureDto;
import static syed.shahza.harmonia.backend.dto.TestMoodDto.aValidMoodDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Action;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.context.ExecutionContext;
import syed.shahza.harmonia.endtoend.test.service.LectureService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class SendMoodStepDefs {
	private LectureDto lectureDto = anActiveLectureDto().password("thePassword").build();
	private MoodDto moodDto = aValidMoodDto().emoji(":S").lectureDto(this.lectureDto).build();

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ExecutionContext executionContext;
    
    @Given("^I am in an active lecture")
    public void givenIAmInAnActiveLecture() {
    	this.lectureService.create(this.lectureDto);
    	this.lectureService.join(this.lectureDto.getPassword());
    }

    @When("^I enter an emoji and click the send button")
    public void whenIEnterAnEmojiAndClickSend() {
        this.lectureService.addMood(this.moodDto);
    }

    @Then("^the lecturer can see the emoji")
    public void thenTheLecturerCanSeeMyEmoji() {
        assertThat(this.lectureService.checkEmojiReceived(this.moodDto.getLectureDto().getTitle(), this.moodDto.getEmoji()), is(Result.SUCCESS));
    }
}