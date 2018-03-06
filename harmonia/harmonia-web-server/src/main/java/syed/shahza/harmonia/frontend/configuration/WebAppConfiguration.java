package syed.shahza.harmonia.frontend.configuration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import syed.shahza.harmonia.frontend.controller.LectureControllerLecturer;
import syed.shahza.harmonia.frontend.controller.LectureControllerStudent;
import syed.shahza.harmonia.frontend.controller.LoginController;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;
import syed.shahza.harmonia.restapi.action.LoginAction;
import syed.shahza.harmonia.restapi.configuration.RestApiConfiguration;

@Configuration
@Import(RestApiConfiguration.class)
public class WebAppConfiguration {
	
    @Resource(name = "loginAction")
    private LoginAction loginAction;
    
    @Resource(name = "lectureCreationAction")
    private LectureCreationAction lectureCreationAction;
    
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
    	return new LectureControllerLecturer(lectureCreationAction, getAllCommentsAction);
    }
    
    @Bean
    public LectureControllerStudent lectureControllerStudent() {
    	return new LectureControllerStudent(joinLectureAction, addCommentAction, getAllCommentsAction);
    }
}
