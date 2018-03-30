package syed.shahza.harmonia.backend.core.repository;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static syed.shahza.harmonia.backend.core.domain.TestLecture.aValidLecture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Emotion;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;
import syed.shahza.harmonia.backend.core.domain.TestComment;
import syed.shahza.harmonia.backend.core.domain.TestLecture;
import syed.shahza.harmonia.backend.core.domain.TestMood;

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
    public void retrieveLectureFromPasswordReturnsLectureObjectIfPasswordValid() {
    	this.lectureRepository.create(lecture);
    	
    	assertThat(lectureRepository.retrieveLectureFromPassword(lecture.getPassword()), is(lecture));
    }
    
    @Test
    public void retrieveLectureFromPasswordReturnsEmptyLectureObjectIfPasswordInvalid() {
    	Lecture lecture = lectureRepository.retrieveLectureFromPassword("passwordForNonExistentLecture");
    	assertTrue(lecture.isEmpty());
    }
    
    @Test
    public void retrieveLectureFromTitleReturnsLectureObjectIfTitleValid() {
    	this.lectureRepository.create(lecture);
    	
    	assertThat(lectureRepository.retrieveLectureFromTitle(lecture.getTitle()), is(lecture));
    }
    
    @Test
    public void retrieveLectureFromTitleReturnsEmptyLectureObjectIfTitleInvalid() {
    	Lecture lecture = lectureRepository.retrieveLectureFromTitle("titleForNonExistentLecture");
    	assertTrue(lecture.isEmpty());
    }
    
    @Test
    public void addCommentShouldReturnTheComment() {
    	assertThat(this.lectureRepository.addComment(comment), is(comment));
    }
    
    @Test
    public void getAllCommentsShouldReturnAllCommentsForParticularLecture() {
    	this.lectureRepository.addComment(comment);
    	this.lectureRepository.addComment(TestComment.aValidComment().message("no").lecture(aValidLecture().title("otherTitle").build()).build());
    	
    	assertThat(this.lectureRepository.getAllComments("otherTitle").getCommentList().size(), is(1));
    }
    
    @Test
    public void getAllCommentsShouldReturnCommentsObject() {
    	this.lectureRepository.addComment(comment);
    	
    	assertThat(this.lectureRepository.getAllComments(lecture.getTitle()), instanceOf(Comments.class));
    }
    
    @Test
    public void addMoodShouldReturnTheMood() {
    	Mood mood = TestMood.aValidMood().build();
    	assertThat(this.lectureRepository.addMood(mood), is(mood));
    }
    
    @Test
    public void getAllMoodsShouldReturnAllMoodsForParticularLecture() {
    	Mood mood = TestMood.aValidMood().build();
    	this.lectureRepository.addMood(mood);
    	this.lectureRepository.addMood(TestMood.aValidMood().emoji(":D").lecture(aValidLecture().title("otherTitle").build()).build());
    	
    	assertThat(this.lectureRepository.getAllMoods("otherTitle").getMoodList().size(), is(1));
    }
    
    @Test
    public void getAllMoodsShouldReturnMoodsObject() {
    	Mood mood = TestMood.aValidMood().build();
    	this.lectureRepository.addMood(mood);
    	
    	assertThat(this.lectureRepository.getAllMoods(lecture.getTitle()), instanceOf(Moods.class));
    }
    
    @Test
    public void removeMoodShouldRemoveForCorrectLectureAndEmoji() {
    	//first ensure the mood for the lecture exists
    	String lectureTitle = "titleForLectureWithMood";
    	Lecture lecture = TestLecture.aValidLecture().title(lectureTitle).build();
    	String emoji = ":S";
    	Mood mood = TestMood.aValidMood().emotion(Emotion.CONFUSED).emoji(emoji).lecture(lecture).build();
    	
    	this.lectureRepository.addMood(mood);
    	
    	//now remove
    	assertThat(this.lectureRepository.removeMood(lectureTitle, emoji), is(true));
    }
}