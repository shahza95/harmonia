package syed.shahza.harmonia.backend.endpoint.controller;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.Moods;
import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.Questions;
import syed.shahza.harmonia.backend.core.domain.TestComments;
import syed.shahza.harmonia.backend.core.domain.TestLecture;
import syed.shahza.harmonia.backend.core.domain.TestMoods;
import syed.shahza.harmonia.backend.core.domain.TestQuestion;
import syed.shahza.harmonia.backend.core.domain.TestQuestions;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.MoodAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.QuestionAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerTest {
    private LectureController lectureController;
    private String lectureTitle;
    
    @Mock
    private MoodAdapter mockMoodAdapter;
    
    @Mock
    private CommentAdapter mockCommentAdapter;
    
    @Mock
    private QuestionAdapter mockQuestionAdapter;
    
    @Mock
    private LectureAdapter mockLectureAdapter;

    @Mock
    private LectureService mockLectureService;

    @Before
    public void before() {
    	this.lectureTitle = "title";
        this.lectureController = new LectureController(this.mockLectureService, this.mockLectureAdapter, this.mockCommentAdapter, this.mockMoodAdapter, this.mockQuestionAdapter);
    }
    
    @Test
    public void getAllCommentsInvokesServiceWithLectureTitleString() {
        this.lectureController.getAllComments(this.lectureTitle);

        verify(this.mockLectureService).getAllComments(this.lectureTitle);
    }
    
    @Test
    public void getAllCommentsInvokesAdapterToDtoForReturn() {
    	LectureDto lectureDto = TestLectureDto.anActiveLectureDto().build();
    	Comments comments = TestComments.aFilledCommentsList(3);
    	when(mockLectureService.getAllComments(lectureDto.getTitle())).thenReturn(comments);
    	this.lectureController.getAllComments(lectureDto.getTitle());
    	
    	verify(this.mockCommentAdapter).toDto(comments);
    }
    
    @Test
    public void getLectureInvokesServiceWithLectureTitleString() {
    	this.lectureController.getLecture(this.lectureTitle);
    	
    	verify(this.mockLectureService).getLecture(this.lectureTitle);
    }
    
    @Test
    public void getLectureInvokesAdapterToDtoForReturn() {
    	Lecture lecture = TestLecture.aValidLecture().build();
    	when(mockLectureService.getLecture(this.lectureTitle)).thenReturn(lecture);
    	this.lectureController.getLecture(this.lectureTitle);
    	
    	verify(this.mockLectureAdapter).toDto(lecture);
    }
    
    @Test
    public void getAllMoodsInvokesServiceWithLectureTitleString() {
        this.lectureController.getAllMoods(this.lectureTitle);

        verify(this.mockLectureService).getAllMoods(this.lectureTitle);
    }
    
    @Test
    public void getAllMoodsInvokesAdapterToDtoForReturn() {
    	LectureDto lectureDto = TestLectureDto.anActiveLectureDto().build();
    	Moods moods = TestMoods.aFilledMoodsList(3);
    	when(mockLectureService.getAllMoods(lectureDto.getTitle())).thenReturn(moods);
    	this.lectureController.getAllMoods(lectureDto.getTitle());
    	
    	verify(this.mockMoodAdapter).toDto(moods);
    }

    @Test
    public void getAllQuestionsInvokesServiceWithLectureTitleString() {
    	this.lectureController.getAllQuestions(this.lectureTitle);
    	
    	verify(this.mockLectureService).getAllQuestions(this.lectureTitle);
    }
    
    @Test
    public void getAllQuestionsInvokesAdapterToDtoForReturn() {
    	LectureDto lectureDto = TestLectureDto.anActiveLectureDto().build();
    	Questions questions = TestQuestions.aFilledQuestionsList(3);
    	when(mockLectureService.getAllQuestions(lectureDto.getTitle())).thenReturn(questions);
    	this.lectureController.getAllQuestions(lectureDto.getTitle());
    	
    	verify(this.mockQuestionAdapter).toDto(questions);
    }
    
    
    @Test
    public void getQuestionInvokesServiceWithQuestionIdString() {
    	String id = "id";
    	this.lectureController.getQuestion(id);
    	
    	verify(this.mockLectureService).getQuestion(id);
    }
    
    @Test
    public void getQuestionInvokesAdapterToDtoForReturn() {
    	Question question = TestQuestion.aValidQuestion().build();
    	when(mockLectureService.getQuestion(question.getId())).thenReturn(question);
    	this.lectureController.getQuestion(question.getId());
    	
    	verify(this.mockQuestionAdapter).toDto(question);
    }
}