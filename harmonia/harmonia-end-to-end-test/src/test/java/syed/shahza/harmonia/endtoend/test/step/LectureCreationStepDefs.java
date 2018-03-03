package syed.shahza.harmonia.endtoend.test.step;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anActiveLectureDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import syed.shahza.harmonia.endtoend.test.api.Action;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.context.ExecutionContext;
import syed.shahza.harmonia.endtoend.test.service.LectureService;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class LectureCreationStepDefs {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private ExecutionContext executionContext;

//    @Given("^I am logged in")
//    public void givenIAmAValidUser() {
//    	this.executionContext.setCurrentUser(aValidLecturerDto().username("user").password("pass").build());
//    }
    
    @When("^I create a lecture starting now")
    public void whenIEnterMyCredentialsAndPressTheLoginButton() {
        Result result = this.lectureService.create(anActiveLectureDto().build());
        this.executionContext.addResult(Action.CREATE, result);
    }

    @Then("^I get redirected to the active lecture page")
    public void thenIGetRedirectedToTheActiveLecturePage() {
        assertThat(this.executionContext.getLastResultFor(Action.CREATE), is(Result.SUCCESS));
    }
}