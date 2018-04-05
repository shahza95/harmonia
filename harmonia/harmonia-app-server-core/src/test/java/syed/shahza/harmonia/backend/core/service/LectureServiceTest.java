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
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.Feedbacks;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Moods;
import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.Questions;
import syed.shahza.harmonia.backend.core.domain.TestComment;
import syed.shahza.harmonia.backend.core.domain.TestComments;
import syed.shahza.harmonia.backend.core.domain.TestFeedback;
import syed.shahza.harmonia.backend.core.domain.TestFeedbacks;
import syed.shahza.harmonia.backend.core.domain.TestMood;
import syed.shahza.harmonia.backend.core.domain.TestMoods;
import syed.shahza.harmonia.backend.core.domain.TestQuestion;
import syed.shahza.harmonia.backend.core.domain.TestQuestions;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;

@RunWith(MockitoJUnitRunner.class)
public class LectureServiceTest {
	private LectureService lectureService;
	private Lecture lecture;
	private Comment comment;
	private Feedback feedback;
	private Question question;
	
	@Mock
	private LectureRepository mockLectureRepository;
	
	@Before
	public void before() {
		this.lecture = aValidLecture().build();
		this.comment = TestComment.aValidComment().build();
		this.feedback = TestFeedback.aValidFeedback().build();
		this.question = TestQuestion.aValidQuestion().build();
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
    
    @Test
    public void getAllCommentsReturnsCommentsObject() {
    	when(this.mockLectureRepository.getAllComments("title")).thenReturn(TestComments.aFilledCommentsList(1));
    	
    	assertThat(this.lectureService.getAllComments("title"), instanceOf(Comments.class));
    }
    
    @Test
    public void addMoodInvokesLectureRepository() {
    	Mood mood = TestMood.aValidMood().build();
    	this.lectureService.addMood(mood);
    	
    	verify(this.mockLectureRepository).addMood(mood);
    }
    
    @Test
    public void addMoodReturnsMoodObject() {
    	Mood mood = TestMood.aValidMood().build();
    	when(this.mockLectureRepository.addMood(mood)).thenReturn(mood);
    	
    	assertThat(this.lectureService.addMood(mood), instanceOf(Mood.class));
    }
    
    @Test
    public void getAllMoodsInvokesLectureRepository() {
    	this.lectureService.getAllMoods("someTitle");
    	
    	verify(this.mockLectureRepository).getAllMoods("someTitle");
    }
    
    @Test
    public void getAllMoodsReturnsMoodsObject() {
    	when(this.mockLectureRepository.getAllMoods("title")).thenReturn(TestMoods.aFilledMoodsList(1));
    	
    	assertThat(this.lectureService.getAllMoods("title"), instanceOf(Moods.class));
    }
    
    @Test
    public void removeMoodInvokesLectureRepository() {
    	String lectureTitle = "title";
    	String emoji = ":)";
    	this.lectureService.removeMood(lectureTitle, emoji);
    	
    	verify(this.mockLectureRepository).removeMood(lectureTitle, emoji);
    }
    
    @Test
    public void updateInvokesLectureRepository() {
    	this.lectureService.update(this.lecture);
    	verify(this.mockLectureRepository).update(this.lecture);
    }
    
    @Test
    public void addFeedbackInvokesLectureRepository() {
    	this.lectureService.addFeedback(feedback);
    	
    	verify(this.mockLectureRepository).addFeedback(feedback);
    }
    
    @Test
    public void addFeedbackReturnsFeedbackObject() {
    	when(this.mockLectureRepository.addFeedback(feedback)).thenReturn(feedback);
    	
    	assertThat(this.lectureService.addFeedback(feedback), instanceOf(Feedback.class));
    }
    
    @Test
    public void getAllFeedbackInvokesLectureRepository() {
    	this.lectureService.getAllFeedback("someTitle");
    	
    	verify(this.mockLectureRepository).getAllFeedback("someTitle");
    }
    
    @Test
    public void getAllFeedbackReturnsFeedbacksObject() {
    	when(this.mockLectureRepository.getAllFeedback("title")).thenReturn(TestFeedbacks.aFilledFeedbacksList(1));
    	
    	assertThat(this.lectureService.getAllFeedback("title"), instanceOf(Feedbacks.class));
    }   
    
    @Test
    public void addQuestionInvokesLectureRepository() {
    	this.lectureService.addQuestion(this.question);
    	
    	verify(this.mockLectureRepository).addQuestion(this.question);
    }
    
    @Test
    public void addQuestionReturnsQuestionObject() {
    	when(this.mockLectureRepository.addQuestion(this.question)).thenReturn(this.question);
    	
    	assertThat(this.lectureService.addQuestion(this.question), instanceOf(Question.class));
    }

    @Test
    public void getAllQuestionsInvokesLectureRepository() {
    	this.lectureService.getAllQuestions("someTitle");
    	
    	verify(this.mockLectureRepository).getAllQuestions("someTitle");
    }
    
    @Test
    public void getAllQuestionsReturnsQuestionsObject() {
    	when(this.mockLectureRepository.getAllQuestions("title")).thenReturn(TestQuestions.aFilledQuestionsList(1));
    	
    	assertThat(this.lectureService.getAllQuestions("title"), instanceOf(Questions.class));
    }   
    
    @Test
    public void getQuestionInvokesLectureRepository() {
    	String id = "id";
    	lectureService.getQuestion(id);
    	
    	verify(this.mockLectureRepository).getQuestion(id);
    }
    
    @Test
    public void getQuestionReturnsQuestionObject() {
    	String id = "id";
    	when(this.mockLectureRepository.getQuestion(id)).thenReturn(question);
    	
    	assertThat(lectureService.getQuestion(id), instanceOf(Question.class));
    }
}