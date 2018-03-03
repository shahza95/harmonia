package syed.shahza.harmonia.endtoend.test.step;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLecturerDto.aValidLecturerDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import syed.shahza.harmonia.endtoend.test.api.Action;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.context.ExecutionContext;
import syed.shahza.harmonia.endtoend.test.service.LoginService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class LoginStepDefs {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ExecutionContext executionContext;

    @Given("^I am a valid user")
    public void givenIAmAValidUser() {
    	this.executionContext.setCurrentUser(aValidLecturerDto().username("user").password("pass").build());
    }
    
    @When("^I enter my credentials and click the login button")
    public void whenIEnterMyCredentialsAndPressTheLoginButton() {
        Result result = this.loginService.login(this.executionContext.getCurrentUser());
        this.executionContext.addResult(Action.LOGIN, result);
    }

    @Then("^I get redirected to the success page")
    public void thenIGetRedirectedToTheSuccessPage() {
        assertThat(this.executionContext.getLastResultFor(Action.LOGIN), is(Result.SUCCESS));
    }
}
