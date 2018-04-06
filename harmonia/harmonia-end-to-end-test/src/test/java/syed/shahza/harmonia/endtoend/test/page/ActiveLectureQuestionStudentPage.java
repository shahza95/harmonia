package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface ActiveLectureQuestionStudentPage extends Page {
	
	void navigateTo(String lectureTitle);

	void enterQuestion(QuestionDto questionDto);

	void clickAskButton();

	void clickQuestion(String question);

	Result checkAnswerVisible(String answer);
}
