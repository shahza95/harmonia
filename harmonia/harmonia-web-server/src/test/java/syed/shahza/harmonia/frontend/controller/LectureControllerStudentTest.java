package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anActiveLectureDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anEmptyLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestCommentDto;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerStudentTest {
    private LectureControllerStudent lectureController;
    private LectureDto lectureDto;
    private LectureDto activeLectureDto;
    private String password = "somePassword";
	private CommentDto commentDto;
   	private String title;
    
    @Mock
    private GetLectureAction mockGetLectureAction;
    
    @Mock
    private JoinLectureAction mockJoinLectureAction;
    
    @Mock
    private AddCommentAction mockAddCommentAction;
    
    @Before
    public void before() {
    	this.lectureDto = aValidLectureDto().date(null).startTime(null).endTime(null).build();
    	this.activeLectureDto = anActiveLectureDto().build();
    	this.commentDto = TestCommentDto.aValidCommentDto().build();
       	this.title = "title";
        this.lectureController = new LectureControllerStudent(this.mockGetLectureAction, this.mockJoinLectureAction, this.mockAddCommentAction);
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForJoin() {
    	assertThat(this.lectureController.getJoinLecturePage().getViewName(), is("joinLecture"));
    }
    
    @Test
    public void joinReturnsJoinLecturePageIfPasswordInvalidThereforeReturnedDtoIsEmpty() {
    	when(this.mockJoinLectureAction.join(password)).thenReturn(anEmptyLectureDto().build());
    	
    	assertThat(this.lectureController.join(password).getViewName(), is("joinLecture"));
    }
    
    @Test
    public void joinRedirectsToJoinLecturePageIfPasswordValidThereforeReturnedDtoNotEmptyButLectureIsNotNow() {
    	when(this.mockJoinLectureAction.join(password)).thenReturn(lectureDto);
    	
    	assertThat(this.lectureController.join(password).getViewName(), is("joinLecture"));
    }
    
    @Test
    public void joinRedirectsToActiveLecturePageIfPasswordValidThereforeReturnedDtoNotEmptyAndLectureIsNow() {
    	when(this.mockJoinLectureAction.join(password)).thenReturn(activeLectureDto);
    	
    	assertThat(this.lectureController.join(password).getViewName(), is("redirect:/student/lecture/active/" + activeLectureDto.getTitle() +"/comments"));
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
}
