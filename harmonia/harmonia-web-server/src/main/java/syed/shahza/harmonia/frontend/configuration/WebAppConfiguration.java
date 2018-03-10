package syed.shahza.harmonia.frontend.configuration;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import syed.shahza.harmonia.frontend.controller.ActiveLectureControllerLecturer;
import syed.shahza.harmonia.frontend.controller.ActiveLectureControllerStudent;
import syed.shahza.harmonia.frontend.controller.LectureControllerLecturer;
import syed.shahza.harmonia.frontend.controller.LectureControllerStudent;
import syed.shahza.harmonia.frontend.controller.LoginController;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;
import syed.shahza.harmonia.restapi.action.LoginAction;
import syed.shahza.harmonia.restapi.configuration.RestApiConfiguration;

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
    public ActiveLectureControllerLecturer activeLectureControllerLecturer() {
    	return new ActiveLectureControllerLecturer(getLectureAction, getAllCommentsAction);
    }
    
    @Bean
    public ActiveLectureControllerStudent activeLectureControllerStudent() {
    	return new ActiveLectureControllerStudent(getLectureAction, getAllCommentsAction);
    }
}
