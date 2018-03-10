package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.TestCommentDto;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.dto.TestMoodDto;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.SendMoodAction;

@RunWith(MockitoJUnitRunner.class)
public class ActiveLectureControllerStudentTest {
    private ActiveLectureControllerStudent lectureController;
    private LectureDto lectureDto;
    private CommentDto commentDto;
    private String title;
    
    @Mock
    private GetLectureAction mockGetLectureAction;
    
    @Mock
    private AddCommentAction mockAddCommentAction;
    
    @Mock
    private GetAllCommentsAction mockGetAllCommentsAction;
    
    @Mock
    private SendMoodAction mockSendMoodAction;
    
    @Before
    public void before() {
    	lectureDto = aValidLectureDto().build();
    	this.commentDto = TestCommentDto.aValidCommentDto().build();
    	this.title = "title";
        this.lectureController = new ActiveLectureControllerStudent(this.mockGetLectureAction, this.mockGetAllCommentsAction, this.mockAddCommentAction, this.mockSendMoodAction);
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
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getViewName(), is("student/activeLecture"));
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
    
    @Test
    public void addCommentInvokesGetLectureAction() {
    	this.lectureController.addComment(title, commentDto);
    	
    	verify(this.mockGetLectureAction).get(title);
    }
    
    @Test
    public void addCommentInvokesAddCommentAction() {
       	when(this.mockGetLectureAction.get(title)).thenReturn(this.lectureDto);
    	this.lectureController.addComment(title, commentDto);
    	  
    	verify(this.mockAddCommentAction).addComment(commentDto);
    }
    
    @Test
    public void addCommentRedirectsToActiveLecturePageSoItself() {
       	when(this.mockGetLectureAction.get(title)).thenReturn(this.lectureDto);
    	when(this.mockAddCommentAction.addComment(commentDto)).thenReturn(commentDto);
    	
    	assertThat(this.lectureController.addComment(title, commentDto).getViewName(), is("redirect:/student/lecture/active/" + title + "/comments"));
    }
    
    @Test
    public void getActiveLectureMoodPageInvokesGetLectureAction() {
    	this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle());
    	verify(this.mockGetLectureAction).get(lectureDto.getTitle());
    }
    
    @Test
    public void getActiveLectureMoodPageSendsLectureAsModel() {
    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getModelMap().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureMoodPageReturnsCorrectView() {
    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getViewName(), is("student/activeLectureMood"));
    }
    
    @Test
    public void sendMoodInvokesGetLectureAction() {
    	MoodDto moodDto = TestMoodDto.aValidMoodDto().build();
    	this.lectureController.sendMood(lectureDto.getTitle(), moodDto);
    	verify(this.mockGetLectureAction).get(lectureDto.getTitle());
    }
    
    @Test
    public void sendMoodSendsInvokesSendMoodAction() {
    	MoodDto moodDto = TestMoodDto.aValidMoodDto().build();
    	this.lectureController.sendMood(lectureDto.getTitle(), moodDto);
    	
    	verify(this.mockSendMoodAction).sendMood(moodDto);
    }
    
    @Test
    public void sendMoodRedirectsToCorrectView() {
    	MoodDto moodDto = TestMoodDto.aValidMoodDto().build();
    	assertThat(this.lectureController.sendMood(lectureDto.getTitle(), moodDto).getViewName(), is("redirect:/student/lecture/active/" + lectureDto.getTitle() + "/mood"));
    }
}
