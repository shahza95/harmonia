package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface ActiveLectureQuestionLecturerPage extends Page {
	
	void clickQuestion(String question);

	void enterAnswer(String answer);

	void clickAnswerButton();

	void navigateToAllQuestions(String title);
}
