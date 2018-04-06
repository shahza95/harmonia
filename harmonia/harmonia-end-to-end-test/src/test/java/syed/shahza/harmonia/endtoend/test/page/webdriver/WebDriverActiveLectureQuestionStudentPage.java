package syed.shahza.harmonia.endtoend.test.page.webdriver;

import java.util.concurrent.atomic.AtomicBoolean;

import org.openqa.selenium.WebDriver;

import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureQuestionStudentPage;

public class WebDriverActiveLectureQuestionStudentPage extends WebDriverPage implements ActiveLectureQuestionStudentPage {
	
    public WebDriverActiveLectureQuestionStudentPage(WebDriver webDriver, String baseUrl) {
    	super(webDriver, baseUrl);
    }
    
    @Override
    public void navigateTo(String lectureTitle) {
    	super.navigateTo("student/lecture/active/" + lectureTitle + "/questions");
    }

    @Override
    public void enterQuestion(QuestionDto questionDto) {
    	this.findTextareaByName("question").ifPresent(textarea -> textarea.fill(questionDto.getQuestion()));
    }

    @Override
    public void clickAskButton() {
        this.findButtonByClass("btn").ifPresent(button -> button.submit());
    }

	@Override
	public void clickQuestion(String question) {
		this.findTextByString(question).ifPresent(link -> link.click());
	}

	@Override
	public Result checkAnswerVisible(String answer) {
		AtomicBoolean answerVisible = new AtomicBoolean(false);
		this.findTextByString(answer).ifPresent(text -> answerVisible.set(true));
		return (answerVisible.get()) ? Result.SUCCESS : Result.FAILURE;
	}
}