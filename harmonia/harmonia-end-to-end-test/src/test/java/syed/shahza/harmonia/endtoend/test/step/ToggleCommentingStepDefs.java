package syed.shahza.harmonia.endtoend.test.step;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anActiveLectureDto;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.context.ExecutionContext;
import syed.shahza.harmonia.endtoend.test.service.LectureService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class ToggleCommentingStepDefs {
	private LectureDto lectureDto = anActiveLectureDto().title(RandomStringUtils.randomAlphabetic(10)).password(RandomStringUtils.randomAlphanumeric(6)).build();

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ExecutionContext executionContext;
    
    @Given("^I created an active lecture")
    public void givenICreatedAnActiveLecture() {
    	this.lectureService.create(this.lectureDto);
    }

    @When("^I click the disable button")
    public void whenIClickDisable() {
        this.lectureService.disableCommenting();
    }

    @Then("^the student cannot post comments")
    public void thenTheStudentCannotPostComments() {
    	this.lectureService.join(this.lectureDto.getPassword());
        assertThat(this.lectureService.checkCommentingDisabled(), is(Result.SUCCESS));
    }
}