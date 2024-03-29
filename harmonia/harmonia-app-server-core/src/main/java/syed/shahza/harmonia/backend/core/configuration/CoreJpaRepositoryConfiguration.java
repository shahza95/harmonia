package syed.shahza.harmonia.backend.core.configuration;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import syed.shahza.harmonia.backend.core.repository.CommentRepository;
import syed.shahza.harmonia.backend.core.repository.FeedbackRepository;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.repository.MoodRepository;
import syed.shahza.harmonia.backend.core.repository.QuestionRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.CommentEntityAdapter;
import syed.shahza.harmonia.backend.core.repository.jpa.FeedbackEntityAdapter;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaCommentRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaFeedbackRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaLectureRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaLecturerRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaMoodRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaQuestionRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.LectureEntityAdapter;
import syed.shahza.harmonia.backend.core.repository.jpa.MoodEntityAdapter;
import syed.shahza.harmonia.backend.core.repository.jpa.QuestionEntityAdapter;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2CommentRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2FeedbackRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LecturerRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2MoodRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2QuestionRepository;

@Configuration
@EnableJpaRepositories("syed.shahza.harmonia.backend.core.repository.jpa.h2")
@EntityScan("syed.shahza.harmonia.backend.core.repository.jpa")
@EnableTransactionManagement
public class CoreJpaRepositoryConfiguration {

	//Add different engine repositories here and to switch out actual use, change instance passed into Jpa Repository
	
	@Resource(name = "h2LecturerRepository")
    private H2LecturerRepository h2LecturerRepository;
	
	@Resource(name = "h2LectureRepository")
	private H2LectureRepository h2LectureRepository;
	
	@Resource(name = "h2CommentRepository")
	private H2CommentRepository h2CommentRepository;
	
	@Resource(name = "h2MoodRepository")
	private H2MoodRepository h2MoodRepository;
	
	@Resource(name = "h2FeedbackRepository")
	private H2FeedbackRepository h2FeedbackRepository;
	
	@Resource(name = "h2QuestionRepository")
	private H2QuestionRepository h2QuestionRepository;

    @Bean
    public LecturerRepository lecturerRepository() {
        return new JpaLecturerRepository(this.h2LecturerRepository);
    }
    
    @Bean
    public LectureRepository lectureRepository() {
    	return new JpaLectureRepository(this.h2LectureRepository, new LectureEntityAdapter());
    }
    
    @Bean
    public CommentEntityAdapter commentEntityAdapter() {
    	return new CommentEntityAdapter(new LectureEntityAdapter());
    }
    
    @Bean
    public MoodEntityAdapter moodEntityAdapter() {
    	return new MoodEntityAdapter(new LectureEntityAdapter());
    }
    
    @Bean
    public FeedbackEntityAdapter feedbackEntityAdapter() {
    	return new FeedbackEntityAdapter(new LectureEntityAdapter());
    }
    
    @Bean
    public QuestionEntityAdapter questionEntityAdapter() {
    	return new QuestionEntityAdapter(new LectureEntityAdapter());
    }
    
    @Bean
    public CommentRepository commentRepository() {
    	return new JpaCommentRepository(this.h2CommentRepository, commentEntityAdapter(), this.h2LectureRepository);
    }
    
    @Bean
    public MoodRepository moodRepository() {
    	return new JpaMoodRepository(this.h2MoodRepository, moodEntityAdapter(), this.h2LectureRepository);
    }
    
    @Bean
    public FeedbackRepository feedbackRepository() {
    	return new JpaFeedbackRepository(this.h2FeedbackRepository, feedbackEntityAdapter(), this.h2LectureRepository);
    }
    
    @Bean
    public QuestionRepository questionRepository() {
    	return new JpaQuestionRepository(this.h2QuestionRepository, questionEntityAdapter(), this.h2LectureRepository);
    }
}