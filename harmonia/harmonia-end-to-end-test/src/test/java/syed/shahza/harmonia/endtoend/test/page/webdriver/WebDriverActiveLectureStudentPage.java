package syed.shahza.harmonia.endtoend.test.page.webdriver;

import java.util.concurrent.atomic.AtomicBoolean;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureStudentPage;

public class WebDriverActiveLectureStudentPage extends WebDriverPage implements ActiveLectureStudentPage {

    public WebDriverActiveLectureStudentPage(WebDriver webDriver, String baseUrl) {
        super(webDriver, baseUrl);
    }

    @Override
    public void enterMessage(String message) {
    	this.findInputByName("message").ifPresent(input -> input.fill(message));
    }

    @Override
    public Result clickCommentButton(String message) {
        this.findButtonByName("Comment").ifPresent(button -> button.submit());
        return findTextByString(message).isPresent() ? Result.SUCCESS : Result.FAILURE;
    }

	@Override
	public Result checkCommentingDisabled() {
		AtomicBoolean enabled = new AtomicBoolean(true);
		this.findInputByName("message").ifPresent(input -> enabled.set(input.checkEnabled()));
		return enabled.get() ? Result.FAILURE : Result.SUCCESS;
	}
}