package syed.shahza.harmonia.backend.core.configuration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.backend.core.repository.LectureRepository;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.core.service.LoginService;

@Configuration
public class CoreConfiguration {
	
    @Resource(name = "lecturerRepository")
    private LecturerRepository lecturerRepository;
	
	@Bean
	public LectureRepository lectureRepository() {
		return new LectureRepository();
	}

	@Bean
    public LoginService loginService() {
        return new LoginService(this.lecturerRepository);
    }
	
	@Bean
	public LectureService lectureService() {
		return new LectureService(lectureRepository());
	}
}