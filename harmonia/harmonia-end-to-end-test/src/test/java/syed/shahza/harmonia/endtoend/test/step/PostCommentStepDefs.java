package syed.shahza.harmonia.endtoend.test.step;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anActiveLectureDto;
import static syed.shahza.harmonia.backend.dto.TestCommentDto.aValidCommentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Action;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.context.ExecutionContext;
import syed.shahza.harmonia.endtoend.test.service.LectureService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class PostCommentStepDefs {
	private LectureDto lectureDto = anActiveLectureDto().password("thePassword").build();
	private CommentDto commentDto = aValidCommentDto().message("theMessage").lectureDto(this.lectureDto).build();

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ExecutionContext executionContext;
    
    @Given("^I have joined an active lecture")
    public void givenIHaveJoinedAnActiveLecture() {
    	this.lectureService.create(this.lectureDto);
    	this.lectureService.join(this.lectureDto.getPassword());
    }

    @When("^I enter a message and click the comment button")
    public void whenIEnterAMessageAndClickComment() {
        Result result = this.lectureService.addComment(this.commentDto);
        this.executionContext.addResult(Action.COMMENT, result);
    }

    @Then("^I see my comment appear in the table")
    public void thenISeeMyCommentAppear() {
        assertThat(this.executionContext.getLastResultFor(Action.COMMENT), is(Result.SUCCESS));
    }
}