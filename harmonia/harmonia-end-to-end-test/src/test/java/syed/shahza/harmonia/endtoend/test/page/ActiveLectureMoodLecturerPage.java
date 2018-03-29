package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface ActiveLectureMoodLecturerPage extends Page {
	
	void navigateTo(String lectureTitle);

	Result checkEmojiIsPresent(String emotionString);

}
