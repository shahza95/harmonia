package syed.shahza.harmonia.backend.endpoint.configuration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.core.service.LoginService;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.FeedbackAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LecturerAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.MoodAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.QuestionAdapter;
import syed.shahza.harmonia.backend.endpoint.controller.LectureController;
import syed.shahza.harmonia.backend.endpoint.controller.LectureControllerLecturer;
import syed.shahza.harmonia.backend.endpoint.controller.LectureControllerStudent;
import syed.shahza.harmonia.backend.endpoint.controller.LoginController;

@Configuration
public class EndpointConfiguration {
	
    @Resource(name = "loginService")
    private LoginService loginService;
    
    @Resource(name = "lectureService")
    private LectureService lectureService;

    @Bean
    public LectureAdapter lectureAdapter() {
    	return new LectureAdapter();
    }
    
    @Bean
    public CommentAdapter commentAdapter() {
    	return new CommentAdapter(this.lectureAdapter());
    }
    
    @Bean
    public MoodAdapter moodAdapter() {
    	return new MoodAdapter(this.lectureAdapter());
    }
    
    @Bean
    public FeedbackAdapter feedbackAdapter() {
    	return new FeedbackAdapter(this.lectureAdapter());
    }
    
    @Bean
    public QuestionAdapter questionAdapter() {
    	return new QuestionAdapter(this.lectureAdapter());
    }
    
    @Bean
    public LoginController loginController() {
        return new LoginController(this.loginService, new LecturerAdapter());
    }
    
    @Bean
    public LectureControllerLecturer lectureControllerLecturer() {
    	return new LectureControllerLecturer(this.lectureService, this.lectureAdapter(), this.feedbackAdapter(), this.questionAdapter());
    }
    
    @Bean
    public LectureControllerStudent lectureControllerStudent() {
    	return new LectureControllerStudent(this.lectureService, this.lectureAdapter(), this.commentAdapter(), this.moodAdapter(), this.feedbackAdapter(), this.questionAdapter());
    }
    
    @Bean
    public LectureController lectureController() {
    	return new LectureController(this.lectureService, this.lectureAdapter(), this.commentAdapter(), this.moodAdapter(), this.questionAdapter());
    }
}
