package syed.shahza.harmonia.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.restapi.action.LoginAction;

@Controller
@RequestMapping("/lecturer/login")
public class LoginController {
	private final LoginAction loginAction;

	public LoginController(LoginAction loginAction) {
		this.loginAction = loginAction;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getLoginPage() {
		return new ModelAndView("lecturer/login");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView login(LecturerDto lecturerDto) {
		if (loginAction.login(lecturerDto)) {
			return new ModelAndView("lecturer/success");
		} 
		return new ModelAndView("lecturer/login");
	}
}
