package syed.shahza.harmonia.backend.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.backend.core.repository.LectureRepository;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.core.service.LecturerService;

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
    public LecturerService lecturerService() {
        return new LecturerService(lecturerRepository());
    }
	
	@Bean
	public LectureService lectureService() {
		return new LectureService(lectureRepository());
	}
}