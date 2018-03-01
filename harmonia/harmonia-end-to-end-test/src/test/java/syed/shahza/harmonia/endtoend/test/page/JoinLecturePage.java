package syed.shahza.harmonia.endtoend.test.page;

import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.service.page.Page;

public interface JoinLecturePage extends Page {
	void navigateTo();

	void enterPassword(String password);

	Result clickJoinButton();
}
