package syed.shahza.harmonia.backend.endpoint.configuration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.core.service.LecturerService;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LecturerAdapter;
import syed.shahza.harmonia.backend.endpoint.controller.LectureController;
import syed.shahza.harmonia.backend.endpoint.controller.LoginController;

@Configuration
public class EndpointConfiguration {
	
    @Resource(name = "lecturerService")
    private LecturerService lecturerService;
    
    @Resource(name = "lectureService")
    private LectureService lectureService;

    @Bean
    public LoginController loginController() {
        return new LoginController(this.lecturerService, new LecturerAdapter());
    }
    
    @Bean
    public LectureController lectureController() {
    	return new LectureController(this.lectureService, new LectureAdapter());
    }
}
