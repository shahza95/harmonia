package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface ActiveLectureFeedbackStudentPage extends Page {

	void enterFeedback(FeedbackDto feedbackDto);

	void clickSubmitButton();
}
