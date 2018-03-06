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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestCommentDto;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerStudentTest {
    private LectureControllerStudent lectureController;
    private LectureDto lectureDto;
    private LectureDto activeLectureDto;
    private String password = "somePassword";
    
    @Mock
    private JoinLectureAction mockJoinLectureAction;
    
    @Mock
    private AddCommentAction mockAddCommentAction;
    
    @Mock
    private GetAllCommentsAction mockGetAllCommentsAction;
    
    @Mock
    private RedirectAttributes mockRedirectAttributes;
    
    @Before
    public void before() {
    	this.lectureDto = aValidLectureDto().date(null).startTime(null).endTime(null).build();
    	this.activeLectureDto = anActiveLectureDto().build();
        this.lectureController = new LectureControllerStudent(this.mockJoinLectureAction, this.mockAddCommentAction, this.mockGetAllCommentsAction);
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForJoin() {
    	assertThat(this.lectureController.getJoinLecturePage().getViewName(), is("joinLecture"));
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLecture() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto).getViewName(), is("activeLecture"));
    }

    @Test
    public void joinReturnsJoinLecturePageIfPasswordInvalidThereforeReturnedDtoIsEmpty() {
    	when(this.mockJoinLectureAction.join(password)).thenReturn(anEmptyLectureDto().build());
    	
    	assertThat(this.lectureController.join(password, mockRedirectAttributes).getViewName(), is("joinLecture"));
    }
    
    @Test
    public void successfulJoinToNowLecturePassesDtoAsRedirectModelValueToActiveLecturePage() {
       	when(this.mockJoinLectureAction.join(password)).thenReturn(activeLectureDto);
    	this.lectureController.join(password, mockRedirectAttributes);
    	
    	verify(this.mockRedirectAttributes).addFlashAttribute("lectureDto", activeLectureDto);
    }
    
    @Test
    public void joinRedirectsToJoinLecturePageIfPasswordValidThereforeReturnedDtoNotEmptyButLectureIsNotNow() {
    	when(this.mockJoinLectureAction.join(password)).thenReturn(lectureDto);
    	
    	assertThat(this.lectureController.join(password, mockRedirectAttributes).getViewName(), is("joinLecture"));
    }
    
    @Test
    public void joinRedirectsToActiveLecturePageIfPasswordValidThereforeReturnedDtoNotEmptyAndLectureIsNow() {
    	when(this.mockJoinLectureAction.join(password)).thenReturn(activeLectureDto);
    	
    	assertThat(this.lectureController.join(password, mockRedirectAttributes).getViewName(), is("redirect:/student/lecture/active/comments"));
    }
    
    @Test
    public void addCommentInvokesAddCommentAction() {
       	CommentDto commentDto = TestCommentDto.aValidCommentDto().build();
    	this.lectureController.addComment(commentDto, this.mockRedirectAttributes);
    	
    	verify(this.mockAddCommentAction).addComment(commentDto);
  	
    }
    
    @Test
    public void addCommentRedirectsToActiveLecturePageSoItself() {
    	CommentDto commentDto = TestCommentDto.aValidCommentDto().build();
    	when(this.mockAddCommentAction.addComment(commentDto)).thenReturn(commentDto);
    	
    	assertThat(this.lectureController.addComment(commentDto, this.mockRedirectAttributes).getViewName(), is("redirect:/student/lecture/active/comments"));
    }
    
    @Test
    public void getActiveLectureSendsLectureDtoAsModel() {
    	CommentDtoList commentDtoList = TestCommentDtoList.aFilledCommentDtoList(3);
    	when(this.mockGetAllCommentsAction.getAll(lectureDto.getTitle())).thenReturn(commentDtoList);
    
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto).getModel().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureSendsCommentDtoListAsModel() {
    	CommentDtoList commentDtoList = TestCommentDtoList.aFilledCommentDtoList(3);
    	when(this.mockGetAllCommentsAction.getAll(lectureDto.getTitle())).thenReturn(commentDtoList);
    	
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto).getModel().get("commentDtoList"), is(commentDtoList));
    }
}
