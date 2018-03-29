package syed.shahza.harmonia.endtoend.test.page.webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureMoodLecturerPage;

public class WebDriverActiveLectureMoodLecturerPage extends WebDriverPage implements ActiveLectureMoodLecturerPage {
	JavascriptExecutor js = (JavascriptExecutor) this.getWebDriver();
	
    public WebDriverActiveLectureMoodLecturerPage(WebDriver webDriver, String baseUrl) {
    	super(webDriver, baseUrl);
    }
    
    @Override
    public void navigateTo(String lectureTitle) {
    	super.navigateTo("lecturer/lecture/active/" + lectureTitle + "/mood");
    }

	@Override
	public Result checkEmojiIsPresent(String emotionString) {
		Long result = (Long) js.executeScript("return " + emotionString);
		return (result == 1) ? Result.SUCCESS : Result.FAILURE;
	}

}