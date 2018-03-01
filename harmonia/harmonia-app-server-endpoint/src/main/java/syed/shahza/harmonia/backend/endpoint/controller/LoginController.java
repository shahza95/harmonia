package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LoginService;
import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.backend.endpoint.adapter.LecturerAdapter;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    private final LecturerAdapter lecturerAdapter;

    public LoginController(LoginService lecturerService, LecturerAdapter lecturerAdapter) {
        this.loginService = lecturerService;
        this.lecturerAdapter = lecturerAdapter;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Boolean login(@RequestBody LecturerDto lecturerDto) {
        return this.loginService.login(this.lecturerAdapter.toDomain(lecturerDto));
    }
}
