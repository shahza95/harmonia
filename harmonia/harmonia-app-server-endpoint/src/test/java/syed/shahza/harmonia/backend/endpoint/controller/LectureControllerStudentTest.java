package syed.shahza.harmonia.backend.endpoint.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLecture.aValidLecture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.Mood;
import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.TestComment;
import syed.shahza.harmonia.backend.core.domain.TestFeedback;
import syed.shahza.harmonia.backend.core.domain.TestMood;
import syed.shahza.harmonia.backend.core.domain.TestQuestion;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.dto.TestCommentDto;
import syed.shahza.harmonia.backend.dto.TestFeedbackDto;
import syed.shahza.harmonia.backend.dto.TestMoodDto;
import syed.shahza.harmonia.backend.dto.TestQuestionDto;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.FeedbackAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.MoodAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.QuestionAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerStudentTest {
    private LectureControllerStudent lectureController;
    private Lecture lecture;
    private CommentDto commentDto;
    private Comment comment;
    private MoodDto moodDto;
    private Mood mood;
    private Feedback feedback;
    private FeedbackDto feedbackDto;
    private Question question;
    private QuestionDto questionDto;
    
    @Mock
    private LectureService mockLectureService;
    
    @Mock
    private LectureAdapter mockLectureAdapter;
    
    @Mock
    private CommentAdapter mockCommentAdapter;
    
    @Mock
    private MoodAdapter mockMoodAdapter;
    
    @Mock
    private FeedbackAdapter mockFeedbackAdapter;
    
    @Mock
    private QuestionAdapter mockQuestionAdapter;

    @Before
    public void before() {
        this.lectureController = new LectureControllerStudent(this.mockLectureService, this.mockLectureAdapter, this.mockCommentAdapter, this.mockMoodAdapter, this.mockFeedbackAdapter, this.mockQuestionAdapter);
        this.lecture = aValidLecture().build();
        this.commentDto = TestCommentDto.aValidCommentDto().build();
        this.comment = TestComment.aValidComment().build();
        this.moodDto = TestMoodDto.aValidMoodDto().build();
        this.mood = TestMood.aValidMood().build();
        this.feedback = TestFeedback.aValidFeedback().build();
        this.feedbackDto = TestFeedbackDto.aValidFeedbackDto().build();
        this.question = TestQuestion.aValidQuestion().build();
        this.questionDto = TestQuestionDto.aValidQuestionDto().build();
    }
    
    @Test
    public void joinInvokesServiceWithPasswordString() {
    	String password = "somePassword";
        this.lectureController.join(password);

        verify(this.mockLectureService).join(password);
    }
    
    @Test
    public void joinInvokesAdapterToDtoForReturn() {
    	String password = "somePassword";
    	when(mockLectureService.join(password)).thenReturn(lecture);
    	this.lectureController.join(password);
    	
    	verify(this.mockLectureAdapter).toDto(lecture);
    }
    
    @Test
    public void addCommentInvokesAdapterToDomain() {
    	CommentDto commentDto = TestCommentDto.aValidCommentDto().build();
    	this.lectureController.addComment(commentDto);
    	
    	verify(this.mockCommentAdapter).toDomain(commentDto);
    }
    
    @Test
    public void addCommentInvokesLectureServiceWithComment() {
    	when(this.mockCommentAdapter.toDomain(commentDto)).thenReturn(comment);
    	this.lectureController.addComment(commentDto);
    	
    	verify(this.mockLectureService).addComment(comment);
    }
    
    @Test
    public void addCommentInvokesAdapterToDto() {
    	when(this.mockCommentAdapter.toDomain(commentDto)).thenReturn(comment);
    	when(this.mockLectureService.addComment(comment)).thenReturn(comment);
    	this.lectureController.addComment(commentDto);
    	
    	verify(this.mockCommentAdapter).toDto(comment);
    }
    
    @Test
    public void addMoodInvokesAdapterToDomain() {
    	MoodDto moodDto = TestMoodDto.aValidMoodDto().build();
    	this.lectureController.addMood(moodDto);
    	
    	verify(this.mockMoodAdapter).toDomain(moodDto);
    }
    
    @Test
    public void addMoodInvokesLectureServiceWithMood() {
    	when(this.mockMoodAdapter.toDomain(moodDto)).thenReturn(mood);
    	this.lectureController.addMood(moodDto);
    	
    	verify(this.mockLectureService).addMood(mood);
    }
    
    @Test
    public void addMoodInvokesAdapterToDto() {
    	when(this.mockMoodAdapter.toDomain(moodDto)).thenReturn(mood);
    	when(this.mockLectureService.addMood(mood)).thenReturn(mood);
    	this.lectureController.addMood(moodDto);
    	
    	verify(this.mockMoodAdapter).toDto(mood);
    }
    
    @Test
    public void removeMoodInvokesServiceWithLectureTitleStringAndEmojiString() {
    	String emoji = ":D";
    	String lectureTitle = "title";
        this.lectureController.removeMoodByEmoji(lectureTitle, emoji);

        verify(this.mockLectureService).removeMood(lectureTitle, emoji);
    }
    
    @Test
    public void addFeedbackInvokesAdapterToDomain() {
    	FeedbackDto feedbackDto = TestFeedbackDto.aValidFeedbackDto().build();
    	this.lectureController.addFeedback(feedbackDto);
    	
    	verify(this.mockFeedbackAdapter).toDomain(feedbackDto);
    }
    
    @Test
    public void addFeedbackInvokesLectureServiceWithFeedback() {
    	when(this.mockFeedbackAdapter.toDomain(this.feedbackDto)).thenReturn(this.feedback);
    	this.lectureController.addFeedback(this.feedbackDto);
    	
    	verify(this.mockLectureService).addFeedback(this.feedback);
    }
    
    @Test
    public void addFeedbackInvokesAdapterToDto() {
    	when(this.mockFeedbackAdapter.toDomain(this.feedbackDto)).thenReturn(this.feedback);
    	when(this.mockLectureService.addFeedback(this.feedback)).thenReturn(this.feedback);
    	this.lectureController.addFeedback(this.feedbackDto);
    	
    	verify(this.mockFeedbackAdapter).toDto(this.feedback);
    }
    
    @Test
    public void addQuestionInvokesAdapterToDomain() {
    	QuestionDto questionDto = TestQuestionDto.aValidQuestionDto().build();
    	this.lectureController.addQuestion(questionDto);
    	
    	verify(this.mockQuestionAdapter).toDomain(questionDto);
    }
    
    @Test
    public void addQuestionInvokesLectureServiceWithQuestion() {
    	when(this.mockQuestionAdapter.toDomain(this.questionDto)).thenReturn(this.question);
    	this.lectureController.addQuestion(this.questionDto);
    	
    	verify(this.mockLectureService).addQuestion(this.question);
    }
    
    @Test
    public void addQuestionInvokesAdapterToDto() {
    	when(this.mockQuestionAdapter.toDomain(this.questionDto)).thenReturn(this.question);
    	when(this.mockLectureService.addQuestion(this.question)).thenReturn(this.question);
    	this.lectureController.addQuestion(this.questionDto);
    	
    	verify(this.mockQuestionAdapter).toDto(this.question);
    }
}