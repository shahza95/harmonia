package syed.shahza.harmonia.backend.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.backend.core.repository.LectureRepository;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.core.service.LoginService;

@Configuration
public class CoreConfiguration {
	
	@Bean
	public LecturerRepository lecturerRepository() {
		return new LecturerRepository();
	}
	
	@Bean
	public LectureRepository lectureRepository() {
		return new LectureRepository();
	}

	@Bean
    public LoginService loginService() {
        return new LoginService(lecturerRepository());
    }
	
	@Bean
	public LectureService lectureService() {
		return new LectureService(lectureRepository());
	}
}