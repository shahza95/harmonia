package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface LectureCreationPage extends Page {
	void navigateTo();

	Result clickCreateButton();

	void fillOutLectureForm(LectureDto lectureDto);
}
