package syed.shahza.harmonia.backend.endpoint.configuration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.backend.core.service.LecturerService;
import syed.shahza.harmonia.backend.endpoint.LoginController;
import syed.shahza.harmonia.backend.endpoint.adapter.LecturerAdapter;

@Configuration
public class EndpointConfiguration {
    @Resource(name = "lecturerService")
    private LecturerService lecturerService;

    @Bean
    public LoginController loginController() {
        return new LoginController(this.lecturerService, new LecturerAdapter());
    }
}
