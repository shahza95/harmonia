package syed.shahza.harmonia.endtoend.test.step;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static  syed.shahza.harmonia.backend.dto.TestLectureDtos.anActiveLectureDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Action;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.context.ExecutionContext;
import syed.shahza.harmonia.endtoend.test.service.LectureService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class JoinLectureStepDefs {
	private LectureDto lectureDto = anActiveLectureDto().password("thePassword").build();

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ExecutionContext executionContext;
    
    @Given("^there exists an active lecture")
    public void givenThereIsAnActiveLectureAlreadyCreated() {
    	this.lectureService.create(this.lectureDto);
    }

    @When("^I enter the valid password")
    public void whenIEnterTheCorrectPasswordAndPressTheJoinButton() {
        Result result = this.lectureService.join(this.lectureDto.getPassword());
        this.executionContext.addResult(Action.JOIN, result);
    }

    @Then("^I get redirected to that active lecture")
    public void thenIGetRedirectedToTheActiveLecture() {
        assertThat(this.executionContext.getLastResultFor(Action.JOIN), is(Result.SUCCESS));
    }
}
