package syed.shahza.harmonia.backend.endpoint;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LecturerService;
import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.backend.endpoint.adapter.LecturerAdapter;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LecturerService lecturerService;
    private final LecturerAdapter lecturerAdapter;

    public LoginController(LecturerService lecturerService, LecturerAdapter lecturerAdapter) {
        this.lecturerService = lecturerService;
        this.lecturerAdapter = lecturerAdapter;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Boolean login(@RequestBody LecturerDto lecturerDto) {
        return this.lecturerService.login(this.lecturerAdapter.toDomain(lecturerDto));
    }
}
