package syed.shahza.harmonia.backend.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.service.LecturerService;

@Configuration
public class CoreConfiguration {
	
	@Bean
	public LecturerRepository lecturerRepository() {
		return new LecturerRepository();
	}

	@Bean
    public LecturerService lecturerService() {
        return new LecturerService(lecturerRepository());
    }
}