package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface ActiveLectureMoodStudentPage extends Page {
	
	void navigateTo(String lectureTitle);

	void enterEmoji(MoodDto moodDto);

	void clickSendButton();
}
