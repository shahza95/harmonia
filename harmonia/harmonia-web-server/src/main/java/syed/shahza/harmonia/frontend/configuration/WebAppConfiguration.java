package syed.shahza.harmonia.frontend.configuration;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import syed.shahza.harmonia.frontend.controller.LectureController;
import syed.shahza.harmonia.frontend.controller.LectureControllerLecturer;
import syed.shahza.harmonia.frontend.controller.LectureControllerStudent;
import syed.shahza.harmonia.frontend.controller.LecturerJmsReceiverController;
import syed.shahza.harmonia.frontend.controller.LoginController;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;
import syed.shahza.harmonia.restapi.action.LoginAction;
import syed.shahza.harmonia.restapi.configuration.RestApiConfiguration;

//@EnableJms
@Configuration
@Import(RestApiConfiguration.class)
public class WebAppConfiguration {
	@Autowired
	private ApplicationContext appplicationContext;
	
    @Resource(name = "loginAction")
    private LoginAction loginAction;
    
    @Resource(name = "lectureCreationAction")
    private LectureCreationAction lectureCreationAction;
    
    @Resource(name = "getLectureAction")
    private GetLectureAction getLectureAction;
    
    @Resource(name = "joinLectureAction")
    private JoinLectureAction joinLectureAction;
    
    @Resource(name = "addCommentAction")
    private AddCommentAction addCommentAction;

    @Resource(name = "getAllCommentsAction")
    private GetAllCommentsAction getAllCommentsAction;

    @Bean
    public LoginController loginController() {
        return new LoginController(loginAction);
    }
    
    @Bean
    public LectureControllerLecturer lectureControllerLecturer() {
    	return new LectureControllerLecturer(getLectureAction, lectureCreationAction);
    }
    
    @Bean
    public LectureControllerStudent lectureControllerStudent() {
    	return new LectureControllerStudent(getLectureAction, joinLectureAction, addCommentAction);
    }
    
    @Bean
    public LectureController lectureController() {
    	return new LectureController(getLectureAction, getAllCommentsAction);
    }
    
    @Bean
    public LecturerJmsReceiverController lecturerJmsReceiverController() {
    	return new LecturerJmsReceiverController();
    }
//    
//    @Bean
//    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
//                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }
//    
//    @Bean
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }
}
