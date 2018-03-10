package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface ActiveLectureStudentPage extends Page {

	void enterMessage(String message);

	Result clickCommentButton(String message);
}
