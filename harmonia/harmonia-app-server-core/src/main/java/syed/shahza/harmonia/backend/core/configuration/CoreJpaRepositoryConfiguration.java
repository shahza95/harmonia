package syed.shahza.harmonia.backend.core.configuration;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import syed.shahza.harmonia.backend.core.repository.CommentRepository;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.CommentEntityAdapter;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaCommentRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaLectureRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.JpaLecturerRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.LectureEntityAdapter;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2CommentRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LecturerRepository;

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
    public CommentRepository commentRepository() {
    	return new JpaCommentRepository(this.h2CommentRepository, commentEntityAdapter(), this.h2LectureRepository);
    }
}