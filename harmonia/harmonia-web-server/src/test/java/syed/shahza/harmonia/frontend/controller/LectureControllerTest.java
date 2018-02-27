package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLectureDtos.aValidLectureDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDtos.anEmptyLectureDto;

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

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerTest {
    private LectureController lectureController;
    private LectureDto lectureDto;
    private String date = "2018-2-26";
    private String startTime = "15:00";
    private String endTime = "16:00";
    
    @Mock
    private LectureCreationAction mockLectureCreationAction;
    
    @Mock
    private RedirectAttributes mockRedirectAttributes;
    
    @Captor
    private ArgumentCaptor<LectureDto> lectureDtoCaptor;

    @Before
    public void before() {
    	lectureDto = aValidLectureDto().date(null).startTime(null).endTime(null).build();
        this.lectureController = new LectureController(mockLectureCreationAction);
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
    public void createRedirectsToViewLecturePageOnlyIfResponseIsDto() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	assertThat(this.lectureController.create(lectureDto, date, startTime, endTime, mockRedirectAttributes).getViewName(), is("redirect:/lecture/view"));
    }
    
    @Test
    public void successfulCreatePassesDtoAsRedirectModelValueToViewLecturePage() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	this.lectureController.create(lectureDto, date, startTime, endTime, mockRedirectAttributes);
    	verify(this.mockRedirectAttributes).addFlashAttribute("lectureDto", lectureDto);
    }
    
    @Test
    public void createRedirectsBackToCreateLectureOnlyIfResponseEmptyDto() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(anEmptyLectureDto().build());
    	assertThat(this.lectureController.create(lectureDto, date, startTime, endTime, mockRedirectAttributes).getViewName(), is("lectureCreation"));
    }
    
    @Test
    public void createSetsDateAndTimes() {
    	when(this.mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	this.lectureController.create(lectureDto, date, startTime, endTime, mockRedirectAttributes);
    	
    	lectureDtoCaptor = ArgumentCaptor.forClass(LectureDto.class);
    	verify(this.mockLectureCreationAction).create(this.lectureDtoCaptor.capture());
    	
    	assertThat(lectureDtoCaptor.getValue().getDate(), is(LocalDate.parse(date)));
    	assertThat(lectureDtoCaptor.getValue().getStartTime(), is(LocalTime.parse(startTime)));
    	assertThat(lectureDtoCaptor.getValue().getEndTime(), is(LocalTime.parse(endTime)));
    }
}
