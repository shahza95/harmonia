package syed.shahza.harmonia.endtoend.test.page.webdriver;

import java.util.concurrent.atomic.AtomicBoolean;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureFeedbackLecturerPage;

public class WebDriverActiveLectureFeedbackLecturerPage extends WebDriverPage implements ActiveLectureFeedbackLecturerPage {
    public WebDriverActiveLectureFeedbackLecturerPage(WebDriver webDriver, String baseUrl) {
    	super(webDriver, baseUrl);
    }
    
    @Override
    public void navigateTo(String lectureTitle) {
    	super.navigateTo("lecturer/lecture/active/" + lectureTitle + "/feedback");
    }

	@Override
	public Result checkFeedbackReceived(FeedbackDto feedbackDto) {
		AtomicBoolean ratingReceived = new AtomicBoolean(false);
		AtomicBoolean messageReceived = new AtomicBoolean(false);
		this.findTextByString(feedbackDto.getMessage()).ifPresent(text -> messageReceived.set(true));
		this.findTextByString(feedbackDto.getRating().toString()).ifPresent(text -> ratingReceived.set(true));
		return (ratingReceived.get() && messageReceived.get()) ? Result.SUCCESS : Result.FAILURE;
	}

}