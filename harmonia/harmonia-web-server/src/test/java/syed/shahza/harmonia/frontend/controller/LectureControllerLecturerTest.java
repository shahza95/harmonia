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

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerLecturerTest {
    private LectureControllerLecturer lectureController;
    private LectureDto lectureDto;
    private String dateTomorrow = LocalDate.now().plusDays(1).toString();
    private String startTime = LocalTime.now().toString();
    private String endTime = LocalTime.now().plusHours(1).toString();
    
    @Mock
    private GetLectureAction mockGetLectureAction;
    
    @Mock
    private LectureCreationAction mockLectureCreationAction;
    
    @Captor
    private ArgumentCaptor<LectureDto> lectureDtoCaptor;

    @Before
    public void before() {
    	lectureDto = aValidLectureDto().date(null).startTime(null).endTime(null).build();
        this.lectureController = new LectureControllerLecturer(this.mockGetLectureAction, this.mockLectureCreationAction);
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForCreate() {
        assertThat(this.lectureController.getLectureCreationPage().getViewName(), is("lecturer/lectureCreation"));
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForView() {
    	assertThat(this.lectureController.getViewLecturePage("title").getViewName(), is("lecturer/viewLecture"));
    }
    
    @Test
    public void createRedirectsToViewLecturePageOnlyIfResponseIsDto() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	assertThat(this.lectureController.create(lectureDto, dateTomorrow, startTime, endTime).getViewName(), is("redirect:/lecturer/lecture/view/" + lectureDto.getTitle()));
    }
      
    @Test
    public void createRedirectsBackToCreateLectureOnlyIfResponseEmptyDto() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(anEmptyLectureDto().build());
    	assertThat(this.lectureController.create(lectureDto, dateTomorrow, startTime, endTime).getViewName(), is("lecturer/lectureCreation"));
    }
    
    @Test
    public void createSetsDateAndTimes() {
    	when(this.mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	this.lectureController.create(lectureDto, dateTomorrow, startTime, endTime);
    	
    	lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class);
    	verify(this.mockLectureCreationAction).create(this.lectureDtoCaptor.capture());
    	
    	assertThat(lectureDtoCaptor.getValue().getDate(), is(LocalDate.parse(dateTomorrow)));
    	assertThat(lectureDtoCaptor.getValue().getStartTime(), is(LocalTime.parse(startTime)));
    	assertThat(lectureDtoCaptor.getValue().getEndTime(), is(LocalTime.parse(endTime)));
    }
    
    @Test
    public void createRedirectsToActiveLectureIfLectureIsNow() {
    	when(this.mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	System.out.println(startTime.toString());
    	System.out.println(endTime.toString());
    	
    	assertThat(this.lectureController.create(lectureDto, LocalDate.now().toString(), startTime, endTime).getViewName(), is("redirect:/lecturer/lecture/active/" + lectureDto.getTitle() + "/comments"));
    }
}
