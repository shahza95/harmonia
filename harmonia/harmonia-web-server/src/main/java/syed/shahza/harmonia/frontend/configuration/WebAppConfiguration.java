package syed.shahza.harmonia.frontend.configuration;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import syed.shahza.harmonia.frontend.controller.ActiveLectureControllerLecturer;
import syed.shahza.harmonia.frontend.controller.ActiveLectureControllerStudent;
import syed.shahza.harmonia.frontend.controller.ActiveLectureRestController;
import syed.shahza.harmonia.frontend.controller.LectureControllerLecturer;
import syed.shahza.harmonia.frontend.controller.LectureControllerStudent;
import syed.shahza.harmonia.frontend.controller.LoginController;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.AddFeedbackAction;
import syed.shahza.harmonia.restapi.action.EndLectureAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllFeedbackAction;
import syed.shahza.harmonia.restapi.action.GetAllMoodsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;
import syed.shahza.harmonia.restapi.action.LoginAction;
import syed.shahza.harmonia.restapi.action.RemoveMoodAction;
import syed.shahza.harmonia.restapi.action.SendMoodAction;
import syed.shahza.harmonia.restapi.action.ToggleFeaturesAction;
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
    
    @Resource(name = "getAllMoodsAction")
    private GetAllMoodsAction getAllMoodsAction;
    
    @Resource(name = "sendMoodAction")
    private SendMoodAction sendMoodAction;
    
    @Resource(name = "removeMoodAction")
    private RemoveMoodAction removeMoodAction;
    
    @Resource(name = "toggleFeaturesAction")
    private ToggleFeaturesAction toggleFeaturesAction;
    
    @Resource(name = "endLectureAction")
    private EndLectureAction endLectureAction;
    
    @Resource(name = "addFeedbackAction")
    private AddFeedbackAction addFeedbackAction;
    
    @Resource(name = "getAllFeedbackAction")
    private GetAllFeedbackAction getAllFeedbackAction;

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
    	return new LectureControllerStudent(joinLectureAction);
    }
    
    @Bean
    public ActiveLectureControllerLecturer activeLectureControllerLecturer() {
    	return new ActiveLectureControllerLecturer(getLectureAction, getAllCommentsAction, getAllMoodsAction, toggleFeaturesAction, endLectureAction, getAllFeedbackAction);
    }
    
    @Bean
    public ActiveLectureControllerStudent activeLectureControllerStudent() {
    	return new ActiveLectureControllerStudent(getLectureAction, getAllCommentsAction, addCommentAction, sendMoodAction, removeMoodAction, addFeedbackAction);
    }

    @Bean
    public ActiveLectureRestController activeLectureRestController() {
    	return new ActiveLectureRestController(getLectureAction, getAllCommentsAction, getAllMoodsAction);
    }
}
