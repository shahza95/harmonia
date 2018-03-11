package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLecturerDto.aValidLecturerDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.restapi.action.LoginAction;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    private LoginController loginController;
    private LecturerDto lecturerDto;
    
    @Mock
    private LoginAction mockLoginAction;

    @Before
    public void before() {
    	lecturerDto = aValidLecturerDto().build();
        this.loginController = new LoginController(mockLoginAction);
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGet() {
        assertThat(this.loginController.getLoginPage().getViewName(), is("lecturer/login"));
    }
    
    @Test
    public void loginRedirectsToSuccessOnlyIfResponseTrue() {
    	when(mockLoginAction.login(lecturerDto)).thenReturn(true);
    	assertThat(this.loginController.login(lecturerDto).getViewName(),is("lecturer/success"));
    }
    
    @Test
    public void loginRedirectsBackToLoginOnlyIfResponseFalse() {
    	when(mockLoginAction.login(lecturerDto)).thenReturn(false);
    	assertThat(this.loginController.login(lecturerDto).getViewName(),is("lecturer/login"));
    }
}
