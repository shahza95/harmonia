package syed.shahza.harmonia.endtoend.test.page.webdriver;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.JoinLecturePage;

public class WebDriverJoinLecturePage extends WebDriverPage implements JoinLecturePage {
    private final static String JOIN_LECTURE_URL = "home";

    public WebDriverJoinLecturePage(WebDriver webDriver, String baseUrl) {
        super(webDriver, baseUrl);
    }

    @Override
    public void navigateTo() {
        this.navigateTo(JOIN_LECTURE_URL);
    }
    
    @Override
    public void enterPassword(String password) {
    	this.findInputByName("password").ifPresent(input -> input.fill(password));
    }

    @Override
    public Result clickJoinButton() {
        this.findButtonByName("join").ifPresent(button -> button.submit());
        return findTextByString("Comments").isPresent() ? Result.SUCCESS : Result.FAILURE;
    }
}