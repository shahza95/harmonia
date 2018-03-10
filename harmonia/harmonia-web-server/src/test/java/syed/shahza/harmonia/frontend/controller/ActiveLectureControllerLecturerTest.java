package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;

@RunWith(MockitoJUnitRunner.class)
public class ActiveLectureControllerLecturerTest {
    private ActiveLectureControllerLecturer lectureController;
    private LectureDto lectureDto;
    
    @Mock
    private GetLectureAction mockGetLectureAction;
    
    @Mock
    private GetAllCommentsAction mockGetAllCommentsAction;
    
    @Before
    public void before() {
    	lectureDto = aValidLectureDto().build();
        this.lectureController = new ActiveLectureControllerLecturer(this.mockGetLectureAction, this.mockGetAllCommentsAction);
        when(this.mockGetLectureAction.get(lectureDto.getTitle())).thenReturn(lectureDto);
    }
    
    @Test
    public void getActiveLectureInvokesGetLectureAction() {
    	this.lectureController.getActiveLecturePage(lectureDto.getTitle());
    	
    	Mockito.verify(this.mockGetLectureAction).get(lectureDto.getTitle());
    }
        
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLecture() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getViewName(), is("lecturer/activeLecture"));
    }
    
    @Test
    public void getActiveLectureSendsLectureDtoAsModel() {
    	CommentDtoList commentDtoList = TestCommentDtoList.aFilledCommentDtoList(3);
    	when(this.mockGetAllCommentsAction.getAll(lectureDto.getTitle())).thenReturn(commentDtoList);
    
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getModel().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureSendsCommentDtoListAsModel() {
    	CommentDtoList commentDtoList = TestCommentDtoList.aFilledCommentDtoList(3);
    	when(this.mockGetAllCommentsAction.getAll(lectureDto.getTitle())).thenReturn(commentDtoList);
    	
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getModel().get("commentDtoList"), is(commentDtoList));
    }
}
