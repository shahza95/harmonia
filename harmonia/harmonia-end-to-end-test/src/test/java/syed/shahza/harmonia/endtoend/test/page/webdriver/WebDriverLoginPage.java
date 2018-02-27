package syed.shahza.harmonia.endtoend.test.page.webdriver;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.LoginPage;

public class WebDriverLoginPage extends WebDriverPage implements LoginPage {
    private final static String LOGIN_URL = "login";

    public WebDriverLoginPage(WebDriver webDriver, String baseUrl) {
        super(webDriver, baseUrl);
    }

    @Override
    public void navigateTo() {
        this.navigateTo(LOGIN_URL);
    }
    
    @Override
    public void enterCredentials(LecturerDto currentUser) {
    	this.findInputByName("username").ifPresent(input -> input.fill(currentUser.getUsername()));
    	this.findInputByName("password").ifPresent(input -> input.fill(currentUser.getPassword()));
    }

    @Override
    public Result clickLoginButton() {
        this.findButtonByClass("button").ifPresent(button -> button.submit());
        return findTextByString("Successfully").isPresent() ? Result.SUCCESS : Result.FAILURE;
    }
}