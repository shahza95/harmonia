package syed.shahza.harmonia.endtoend.test.service.page;

import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.LoginPage;
import syed.shahza.harmonia.endtoend.test.service.LoginService;


public class PageBasedLoginService implements LoginService {
    private final LoginPage loginPage;

    public PageBasedLoginService(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Override
    public Result login(LecturerDto currentUser) {
        this.loginPage.navigateTo();
        this.loginPage.enterCredentials(currentUser);
        return this.loginPage.clickLoginButton();
    }

}
