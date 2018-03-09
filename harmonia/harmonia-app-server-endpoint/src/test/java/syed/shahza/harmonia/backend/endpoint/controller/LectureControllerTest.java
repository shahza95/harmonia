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
import syed.shahza.harmonia.backend.core.domain.TestComments;
import syed.shahza.harmonia.backend.core.domain.TestLecture;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerTest {
    private LectureController lectureController;
    
    @Mock
    private CommentAdapter mockCommentAdapter;
    
    @Mock
    private LectureAdapter mockLectureAdapter;

    @Mock
    private LectureService mockLectureService;

    @Before
    public void before() {
        this.lectureController = new LectureController(this.mockLectureService, this.mockLectureAdapter, this.mockCommentAdapter);
    }
    
    @Test
    public void getAllCommentsInvokesServiceWithLectureTitleString() {
    	String title = "someTitle";
        this.lectureController.getAllComments(title);

        verify(this.mockLectureService).getAllComments(title);
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
    	String title = "someTitle";
    	this.lectureController.getLecture(title);
    	
    	verify(this.mockLectureService).getLecture(title);
    }
    
    @Test
    public void getLectureInvokesAdapterToDtoForReturn() {
    	Lecture lecture = TestLecture.aValidLecture().build();
    	String title = "title";
    	when(mockLectureService.getLecture(title)).thenReturn(lecture);
    	this.lectureController.getLecture(title);
    	
    	verify(this.mockLectureAdapter).toDto(lecture);
    }
}