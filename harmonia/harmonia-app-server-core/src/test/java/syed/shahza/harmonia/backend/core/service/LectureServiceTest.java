package syed.shahza.harmonia.backend.core.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLecture.aValidLecture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.TestComment;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;

@RunWith(MockitoJUnitRunner.class)
public class LectureServiceTest {
	private LectureService lectureService;
	private Lecture lecture;
	private Comment comment;
	
	@Mock
	private LectureRepository mockLectureRepository;
	
	@Before
	public void before() {
		lecture = aValidLecture().build();
		comment = TestComment.aValidComment().build();
		this.lectureService = new LectureService(this.mockLectureRepository);
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
    public void getLectureInvokesLectureRepository() {
    	String title = "title";
    	lectureService.getLecture(title);
    	
    	verify(this.mockLectureRepository).retrieveLectureFromTitle(title);
    }
    
    @Test
    public void getLectureReturnsLectureObject() {
    	String title = "title";
    	when(this.mockLectureRepository.retrieveLectureFromTitle(title)).thenReturn(lecture);
    	
    	assertThat(lectureService.getLecture(title), instanceOf(Lecture.class));
    }
    
    @Test
    public void addCommentInvokesLectureRepository() {
    	this.lectureService.addComment(comment);
    	
    	verify(this.mockLectureRepository).addComment(comment);
    }
    
    @Test
    public void addCommentReturnsCommentObject() {
    	when(this.mockLectureRepository.addComment(comment)).thenReturn(comment);
    	
    	assertThat(this.lectureService.addComment(comment), instanceOf(Comment.class));
    }

    @Test
    public void getAllCommentsInvokesLectureRepository() {
    	this.lectureService.getAllComments("someTitle");
    	
    	verify(this.mockLectureRepository).getAllComments("someTitle");
    }
}