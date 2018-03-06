package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anEmptyLectureDto;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerLecturerTest {
    private LectureControllerLecturer lectureController;
    private LectureDto lectureDto;
    private String dateTomorrow = LocalDate.now().plusDays(1).toString();
    private String startTime = LocalTime.now().toString();
    private String endTime = LocalTime.now().plusHours(1).toString();
    
    @Mock
    private LectureCreationAction mockLectureCreationAction;
    
    @Mock
    private GetAllCommentsAction mockGetAllCommentsAction;
    
    @Mock
    private RedirectAttributes mockRedirectAttributes;
    
    @Captor
    private ArgumentCaptor<LectureDto> lectureDtoCaptor;

    @Before
    public void before() {
    	lectureDto = aValidLectureDto().date(null).startTime(null).endTime(null).build();
        this.lectureController = new LectureControllerLecturer(this.mockLectureCreationAction, this.mockGetAllCommentsAction);
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForCreate() {
        assertThat(this.lectureController.getLectureCreationPage().getViewName(), is("lectureCreation"));
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForView() {
    	assertThat(this.lectureController.getViewLecturePage(lectureDto).getViewName(), is("viewLecture"));
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLecture() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto).getViewName(), is("activeLecture"));
    }
    
    @Test
    public void createRedirectsToViewLecturePageOnlyIfResponseIsDto() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	assertThat(this.lectureController.create(lectureDto, dateTomorrow, startTime, endTime, mockRedirectAttributes).getViewName(), is("redirect:/lecturer/lecture/view"));
    }
    
    @Test
    public void successfulCreatePassesDtoAsRedirectModelValueToViewOrActiveLecturePage() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	this.lectureController.create(lectureDto, dateTomorrow, startTime, endTime, mockRedirectAttributes);
    	verify(this.mockRedirectAttributes).addFlashAttribute("lectureDto", lectureDto);
    }
    
    @Test
    public void createRedirectsBackToCreateLectureOnlyIfResponseEmptyDto() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(anEmptyLectureDto().build());
    	assertThat(this.lectureController.create(lectureDto, dateTomorrow, startTime, endTime, mockRedirectAttributes).getViewName(), is("lectureCreation"));
    }
    
    @Test
    public void createSetsDateAndTimes() {
    	when(this.mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	this.lectureController.create(lectureDto, dateTomorrow, startTime, endTime, mockRedirectAttributes);
    	
    	lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class);
    	verify(this.mockLectureCreationAction).create(this.lectureDtoCaptor.capture());
    	
    	assertThat(lectureDtoCaptor.getValue().getDate(), is(LocalDate.parse(dateTomorrow)));
    	assertThat(lectureDtoCaptor.getValue().getStartTime(), is(LocalTime.parse(startTime)));
    	assertThat(lectureDtoCaptor.getValue().getEndTime(), is(LocalTime.parse(endTime)));
    }
    
    @Test
    public void createRedirectsToActiveLectureIfLectureIsNow() {
    	when(this.mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	
    	assertThat(this.lectureController.create(lectureDto, LocalDate.now().toString(), startTime, endTime, mockRedirectAttributes).getViewName(), is("redirect:/lecturer/lecture/active/comments"));
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
