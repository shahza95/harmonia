package syed.shahza.harmonia.endtoend.test.page.webdriver;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureMoodLecturerPage;

public class WebDriverActiveLectureMoodLecturerPage extends WebDriverPage implements ActiveLectureMoodLecturerPage {
	
    public WebDriverActiveLectureMoodLecturerPage(WebDriver webDriver, String baseUrl) {
    	super(webDriver, baseUrl);
    }
    
    @Override
    public void navigateTo(String lectureTitle) {
    	super.navigateTo("lecturer/lecture/active/" + lectureTitle + "/mood");
    }

	@Override
	public Result checkEmojiIsPresent(String emoji) {
		return findTextByString(emoji).isPresent() ? Result.SUCCESS : Result.FAILURE;
	}

}