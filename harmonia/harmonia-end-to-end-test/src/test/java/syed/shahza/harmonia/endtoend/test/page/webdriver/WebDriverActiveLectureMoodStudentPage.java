package syed.shahza.harmonia.endtoend.test.page.webdriver;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureMoodStudentPage;

public class WebDriverActiveLectureMoodStudentPage extends WebDriverPage implements ActiveLectureMoodStudentPage {
	
    public WebDriverActiveLectureMoodStudentPage(WebDriver webDriver, String baseUrl) {
    	super(webDriver, baseUrl);
    }
    
    @Override
    public void navigateTo(String lectureTitle) {
    	super.navigateTo("student/lecture/active/" + lectureTitle + "/mood");
    }

    @Override
    public void enterEmoji(MoodDto moodDto) {
    	this.findSelectByName("mood").ifPresent(select -> select.choose(moodDto.getEmotionDto().toString() + " " + moodDto.getEmoji()));
    }

    @Override
    public void clickSendButton() {
        this.findButtonByName("Mood").ifPresent(button -> button.submit());
    }
}