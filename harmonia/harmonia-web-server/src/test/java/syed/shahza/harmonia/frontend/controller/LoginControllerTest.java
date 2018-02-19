package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LoginControllerTest {
    private LoginController loginController;

    @Before
    public void before() {
        this.loginController = new LoginController();
    }

    @Test
    public void controllerServesUpCorrectThymeleafPageOnGet() {
        assertThat(this.loginController.getLoginPage().getViewName(), is("login"));
    }
}
