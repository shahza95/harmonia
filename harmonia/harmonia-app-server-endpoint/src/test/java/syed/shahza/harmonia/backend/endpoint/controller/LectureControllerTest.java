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
import syed.shahza.harmonia.backend.core.domain.TestComments;
import syed.shahza.harmonia.backend.core.domain.TestLecture;
import syed.shahza.harmonia.backend.core.domain.TestMoods;
import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.MoodAdapter;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerTest {
    private LectureController lectureController;
    private String lectureTitle;
    
    @Mock
    private MoodAdapter mockMoodAdapter;
    
    @Mock
    private CommentAdapter mockCommentAdapter;
    
    @Mock
    private LectureAdapter mockLectureAdapter;

    @Mock
    private LectureService mockLectureService;

    @Before
    public void before() {
    	this.lectureTitle = "title";
        this.lectureController = new LectureController(this.mockLectureService, this.mockLectureAdapter, this.mockCommentAdapter, this.mockMoodAdapter);
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
}