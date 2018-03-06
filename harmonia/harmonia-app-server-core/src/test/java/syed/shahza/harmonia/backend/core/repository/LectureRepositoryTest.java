package syed.shahza.harmonia.backend.core.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static syed.shahza.harmonia.backend.core.domain.TestLecture.aValidLecture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.TestComment;

@RunWith(MockitoJUnitRunner.class)
public class LectureRepositoryTest {
	private LectureRepository lectureRepository;
	private Lecture lecture;
	private Comment comment;
	
	@Before
	public void before() {
		this.lectureRepository = new LectureRepository();
		this.lecture = aValidLecture().build();
		this.comment = TestComment.aValidComment().build();
	}
	
    @Test
    public void createReturnsAddedLecture() {
        assertThat(this.lectureRepository.create(lecture), is(lecture));
    }
    
    @Test
    public void joinReturnsLectureObjectIfPasswordValid() {
    	this.lectureRepository.create(lecture);
    	
    	assertThat(lectureRepository.retrieveLectureFromPassword(lecture.getPassword()), is(lecture));
    }
    
    @Test
    public void joinReturnsEmptyLectureObjectIfPasswordInvalid() {
    	Lecture lecture = lectureRepository.retrieveLectureFromPassword("passwordForNonExistentLecture");
    	assertTrue(lecture.isEmpty());
    }
    
    @Test
    public void addCommentShouldReturnTheComment() {
    	assertThat(this.lectureRepository.addComment("someTitle", comment), is(comment));
    }
    
    @Test
    public void getAllCommentsShouldReturnAllCommentsForParticularLecture() {
    	this.lectureRepository.addComment(lecture.getTitle(), comment);
    	this.lectureRepository.addComment(lecture.getTitle(), TestComment.aValidComment().message("no").build());
    	
    	assertThat(this.lectureRepository.getAllComments(lecture.getTitle()).getCommentList().size(), is(1));
    }
    
    @Test
    public void getAllCommentsShouldReturnCommentsObject() {
    	this.lectureRepository.addComment(lecture.getTitle(), comment);
    	
    	assertThat(this.lectureRepository.getAllComments(lecture.getTitle()), instanceOf(Comments.class));
    }
}