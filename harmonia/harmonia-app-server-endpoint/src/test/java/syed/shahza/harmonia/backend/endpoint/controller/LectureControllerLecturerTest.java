package syed.shahza.harmonia.backend.endpoint.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLecture.aValidLecture;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Feedbacks;
import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.TestFeedbacks;
import syed.shahza.harmonia.backend.core.domain.TestQuestion;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.dto.TestQuestionDto;
import syed.shahza.harmonia.backend.endpoint.adapter.FeedbackAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.QuestionAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerLecturerTest {
    private LectureControllerLecturer lectureController;
    private LectureDto lectureDto;
    private Lecture lecture;

    @Mock
    private LectureService mockLectureService;
    
    @Mock
    private LectureAdapter mockLectureAdapter;
    
    @Mock
    private FeedbackAdapter mockFeedbackAdapter;
    
    @Mock
    private QuestionAdapter mockQuestionAdapter;

    @Before
    public void before() {
        this.lectureController = new LectureControllerLecturer(this.mockLectureService, this.mockLectureAdapter, this.mockFeedbackAdapter, this.mockQuestionAdapter);
        lectureDto = aValidLectureDto().build();
        lecture = aValidLecture().build();
    }

    @Test
    public void createInvokesAdapterToDomain() {
    	this.lectureController.create(lectureDto);
    	verify(this.mockLectureAdapter).toDomain(lectureDto);
    }

    @Test
    public void createInvokesServiceWithLectureDomainObject() {
    	when(mockLectureAdapter.toDomain(lectureDto)).thenReturn(lecture);
        this.lectureController.create(lectureDto);

        verify(this.mockLectureService).create(lecture);
    }
    
    @Test
    public void createInvokesAdapterToDtoForReturn() {
    	when(mockLectureAdapter.toDomain(lectureDto)).thenReturn(lecture);
    	when(mockLectureService.create(lecture)).thenReturn(lecture);
    	this.lectureController.create(lectureDto);
    	
    	verify(this.mockLectureAdapter).toDto(lecture);
    }

    @Test
    public void createReturnsLectureDto() {
    	when(mockLectureAdapter.toDomain(lectureDto)).thenReturn(lecture);
    	when(mockLectureService.create(lecture)).thenReturn(lecture);
    	when(mockLectureAdapter.toDto(lecture)).thenReturn(lectureDto);
        
        assertThat(this.lectureController.create(lectureDto), is(lectureDto));
    }
    
    @Test
    public void toggleCommentsInvokesAdapterToDomain() {
    	this.lectureController.updateLecture(lectureDto);
    	verify(this.mockLectureAdapter).toDomain(lectureDto);
    }

    @Test
    public void toggleCommentsInvokesServiceWithLectureDomainObject() {
    	when(mockLectureAdapter.toDomain(lectureDto)).thenReturn(lecture);
        this.lectureController.updateLecture(lectureDto);

        verify(this.mockLectureService).update(lecture);
    }
    
    @Test
    public void getAllFeedbackInvokesServiceWithLectureTitle() {
    	this.lectureController.getAllFeedback(lectureDto.getTitle());
    	
    	verify(this.mockLectureService).getAllFeedback(lectureDto.getTitle());
    }
    
    @Test
    public void getAllFeedbackInvokesAdapterToDto() {
    	Feedbacks feedbacks = TestFeedbacks.aFilledFeedbacksList(2);
    	when(mockLectureService.getAllFeedback(lectureDto.getTitle())).thenReturn(feedbacks);

    	this.lectureController.getAllFeedback(lectureDto.getTitle());
    	verify(this.mockFeedbackAdapter).toDto(feedbacks);
    }
    
    @Test
    public void updateLectureInvokesAdapterToDomain() {
    	this.lectureController.updateLecture(this.lectureDto);
    	
    	verify(this.mockLectureAdapter).toDomain(this.lectureDto);
    }
    
    @Test
    public void updateLectureInvokesLectureServiceUpdate() {
    	when(mockLectureAdapter.toDomain(lectureDto)).thenReturn(this.lecture);
    	this.lectureController.updateLecture(this.lectureDto);
    	
    	verify(this.mockLectureService).update(this.lecture);
    }
    
    @Test
    public void updateQuestionInvokesAdapterToDomain() {
    	QuestionDto questionDto = TestQuestionDto.aValidQuestionDto().build();
    	this.lectureController.updateQuestion(questionDto);
    	
    	verify(this.mockQuestionAdapter).toDomain(questionDto);
    }
    
    @Test
    public void updateQuestionInvokesLectureServiceUpdateQuestion() {
    	QuestionDto questionDto = TestQuestionDto.aValidQuestionDto().build();
    	Question question = TestQuestion.aValidQuestion().build();
    	when(mockQuestionAdapter.toDomain(questionDto)).thenReturn(question);
    	this.lectureController.updateQuestion(questionDto);
    	
    	verify(this.mockLectureService).updateQuestion(question);
    }
}