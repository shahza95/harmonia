package syed.shahza.harmonia.backend.core.configuration;

import org.springframework.context.annotation.Bean;

import syed.shahza.harmonia.backend.core.repository.java.JavaLecturerRepository;

public class CoreTransientRepositoryConfiguration {
	@Bean
	public JavaLecturerRepository lecturerRepository() {
		return new JavaLecturerRepository();
	}
}
