package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface LoginPage extends Page {
	void navigateTo();

	Result clickLoginButton();

	void enterCredentials(LecturerDto currentUser);
}
