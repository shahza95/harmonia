package syed.shahza.harmonia.backend.core.configuration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.backend.core.repository.CommentRepository;
import syed.shahza.harmonia.backend.core.repository.FeedbackRepository;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.repository.MoodRepository;
import syed.shahza.harmonia.backend.core.repository.QuestionRepository;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.core.service.LoginService;

@Configuration
public class CoreConfiguration {
	
    @Resource(name = "lecturerRepository")
    private LecturerRepository lecturerRepository;
    
    @Resource(name = "lectureRepository")
    private LectureRepository lectureRepository;
    
    @Resource(name = "commentRepository")
    private CommentRepository commentRepository;
    
    @Resource(name = "moodRepository")
    private MoodRepository moodRepository;
    
    @Resource(name = "feedbackRepository")
    private FeedbackRepository feedbackRepository;
    
    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

	@Bean
    public LoginService loginService() {
        return new LoginService(this.lecturerRepository);
    }
	
	@Bean
	public LectureService lectureService() {
		return new LectureService(this.lectureRepository, this.commentRepository, this.moodRepository, this.feedbackRepository, this.questionRepository);
	}
}