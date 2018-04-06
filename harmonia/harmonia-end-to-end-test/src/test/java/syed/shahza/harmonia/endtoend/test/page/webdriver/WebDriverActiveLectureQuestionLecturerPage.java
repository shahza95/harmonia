package syed.shahza.harmonia.endtoend.test.page.webdriver;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.endtoend.test.page.ActiveLectureQuestionLecturerPage;

public class WebDriverActiveLectureQuestionLecturerPage extends WebDriverPage implements ActiveLectureQuestionLecturerPage {
	
    public WebDriverActiveLectureQuestionLecturerPage(WebDriver webDriver, String baseUrl) {
    	super(webDriver, baseUrl);
    }
    
    @Override
    public void navigateToAllQuestions(String title) {
    	super.navigateTo("lecturer/lecture/active/" + title + "/questions");
    }
    
    @Override
    public void clickQuestion(String question) {
    	this.findTextByString(question).ifPresent(link -> link.click());
    }

    @Override
    public void enterAnswer(String answer) {
    	this.findTextareaByName("answer").ifPresent(textarea -> textarea.fill(answer));
    }

    @Override
    public void clickAnswerButton() {
        this.findButtonByClass("btn").ifPresent(button -> button.submit());
    }
}