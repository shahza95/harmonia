package syed.shahza.harmonia.backend.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.backend.core.service.LecturerService;

@Configuration
public class CoreConfiguration {
    @Bean
    public LecturerService lecturerService() {
        return new LecturerService();
    }
}