package syed.shahza.harmonia.backend.core.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLecture.aValidLecture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.TestComment;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;

@RunWith(MockitoJUnitRunner.class)
public class LectureServiceTest {
	private LectureService lectureService;
	private Lecture lecture;
	private String lectureTitle;
	private Comment comment;
	
	@Mock
	private LectureRepository mockLectureRepository;
	
	@Mock
	private JmsTemplate mockJmsTemplate;
	
	@Before
	public void before() {
		lecture = aValidLecture().build();
		lectureTitle = lecture.getTitle();
		comment = TestComment.aValidComment().build();
		this.lectureService = new LectureService(this.mockLectureRepository, this.mockJmsTemplate);
	}
	
    @Test
    public void createInvokesLectureRepository() {
    	lectureService.create(lecture);
    	verify(this.mockLectureRepository).create(lecture);
    }
	
    @Test
    public void createReturnsCorrectObjectIfSuccess() {
        when(this.mockLectureRepository.create(lecture)).thenReturn(lecture);

        assertThat(lectureService.create(lecture), is(lecture));
    }
    
    @Test
    public void joinInvokesLectureRepository() {
    	String password = "password";
		lectureService.join(password);
		
    	verify(this.mockLectureRepository).retrieveLectureFromPassword(password);
    }
    
    @Test
    public void joinReturnsLectureObject() {
    	String password = "password";
    	when(this.mockLectureRepository.retrieveLectureFromPassword(password)).thenReturn(lecture);
    	
    	assertThat(lectureService.join(password), instanceOf(Lecture.class));
    }
    
    @Test
    public void addCommentInvokesLectureRepository() {
    	this.lectureService.addComment(lectureTitle, comment);
    	
    	verify(this.mockLectureRepository).addComment(lectureTitle, comment);
    }
    
    @Test
    public void addCommentReturnsCommentObject() {
    	when(this.mockLectureRepository.addComment(lectureTitle, comment)).thenReturn(comment);
    	
    	assertThat(this.lectureService.addComment(lectureTitle, comment), instanceOf(Comment.class));
    }
    
    @Test
    public void successfulAddCommentInvokesJmsTemplateWithCorrectParameters() {
    	when(this.mockLectureRepository.addComment(lectureTitle, comment)).thenReturn(comment);
    	this.lectureService.addComment(lectureTitle, comment);
    	
    	verify(this.mockJmsTemplate).convertAndSend("lecture", comment);
    }
    
    @Test
    public void unsuccessfulAddCommentDoesNotInvokeJmsTemplate() {
    	when(this.mockLectureRepository.addComment(lectureTitle, comment)).thenReturn(null);
    	this.lectureService.addComment(lectureTitle, comment);
    	
    	verify(this.mockJmsTemplate, never()).convertAndSend("lecture", comment);
    }
}