package syed.shahza.harmonia.backend.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import syed.shahza.harmonia.backend.core.repository.LectureRepository;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.core.service.LoginService;

@Configuration
public class CoreConfiguration {
	@Autowired
	private ApplicationContext appplicationContext;
	
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
		return new LectureService(lectureRepository(),this.appplicationContext.getBean(JmsTemplate.class));
	}
	
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}