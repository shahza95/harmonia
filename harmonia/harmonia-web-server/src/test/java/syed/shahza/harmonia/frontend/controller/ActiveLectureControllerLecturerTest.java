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
import syed.shahza.harmonia.backend.dto.MoodDtoList;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.dto.TestMoodDtoList;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllMoodsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;

@RunWith(MockitoJUnitRunner.class)
public class ActiveLectureControllerLecturerTest {
    private ActiveLectureControllerLecturer lectureController;
    private LectureDto lectureDto;
    
    @Mock
    private GetLectureAction mockGetLectureAction;
    
    @Mock
    private GetAllCommentsAction mockGetAllCommentsAction;
    
    @Mock
    private GetAllMoodsAction mockGetAllMoodsAction;
    
    @Before
    public void before() {
    	lectureDto = aValidLectureDto().build();
        this.lectureController = new ActiveLectureControllerLecturer(this.mockGetLectureAction, this.mockGetAllCommentsAction, this.mockGetAllMoodsAction);
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
    
//    @Test
//    public void getActiveLectureMoodInvokesGetLectureAction() {
//    	this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle());
//    	
//    	Mockito.verify(this.mockGetLectureAction).get(lectureDto.getTitle());
//    }
//    
//    @Test
//    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLectureMood() {
//    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
//    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getViewName(), is("lecturer/activeLectureMood"));
//    }
    
    @Test
    public void getActiveLectureMoodSendsLectureDtoAsModel() {
    	MoodDtoList moodDtoList = TestMoodDtoList.aFilledMoodDtoList(3);
    	when(this.mockGetAllMoodsAction.getAll(lectureDto.getTitle())).thenReturn(moodDtoList);
    	
    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getModel().get("lectureDto"), is(lectureDto));
    }
    
//    @Test
//    public void getActiveLectureMoodSendsMoodDtoListAsModel() {
//    	MoodDtoList moodDtoList = TestMoodDtoList.aFilledMoodDtoList(3);
//    	when(this.mockGetAllMoodsAction.getAll(lectureDto.getTitle())).thenReturn(moodDtoList);
//    	
//    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getModel().get("moodDtoList"), is(moodDtoList));
//    }
}
