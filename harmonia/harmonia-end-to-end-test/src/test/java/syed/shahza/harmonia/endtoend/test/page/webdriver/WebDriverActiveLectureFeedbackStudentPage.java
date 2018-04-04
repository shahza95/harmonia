package syed.shahza.harmonia.endtoend.test.page.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureFeedbackStudentPage;

public class WebDriverActiveLectureFeedbackStudentPage extends WebDriverPage implements ActiveLectureFeedbackStudentPage {
	
    public WebDriverActiveLectureFeedbackStudentPage(WebDriver webDriver, String baseUrl) {
    	super(webDriver, baseUrl);
    }

    @Override
    public void enterFeedback(FeedbackDto feedbackDto) {
    	//wait till redirected to feedback page
    	WebDriverWait wait = new WebDriverWait(this.getWebDriver(), 10);
    	wait.until(ExpectedConditions.titleContains("Feedback"));
    	
    	this.findTextByString(feedbackDto.getRating() + " stars").ifPresent(label -> label.click());
    	this.findInputByName("message").ifPresent(input -> input.fill(feedbackDto.getMessage()));
    }

    @Override
    public void clickSubmitButton() {
        this.findButtonByClass("btn").ifPresent(button -> button.submit());
    }
}