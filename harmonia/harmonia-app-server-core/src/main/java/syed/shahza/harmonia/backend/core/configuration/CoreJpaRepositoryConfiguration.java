package syed.shahza.harmonia.backend.core.configuration;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaLecturerRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LecturerRepository;

@Configuration
@EnableJpaRepositories("syed.shahza.harmonia.backend.core.repository.jpa.h2")
@EntityScan("syed.shahza.harmonia.backend.core.repository.jpa")
@EnableTransactionManagement
public class CoreJpaRepositoryConfiguration {

	//Add different engine repositories here and to switch out actual use, change instance passed into Jpa Repository
	
	@Resource(name = "h2LecturerRepository")
    private H2LecturerRepository h2LecturerRepository;

    @Bean
    public LecturerRepository lecturerRepository() {
        return new JpaLecturerRepository(this.h2LecturerRepository);
    }
}