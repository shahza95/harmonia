package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface ActiveLectureFeedbackLecturerPage extends Page {
	
	void navigateTo(String lectureTitle);

	Result checkFeedbackReceived(FeedbackDto feedbackDto);

}
