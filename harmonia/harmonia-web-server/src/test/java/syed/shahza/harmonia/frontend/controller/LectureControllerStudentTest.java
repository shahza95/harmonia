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
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestLectureDtos;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerStudentTest {
    private LectureControllerStudent lectureController;
    private LectureDto lectureDto;
    private LectureDto activeLectureDto;
    private String password = "somePassword";
    private LocalDate dateToday = LocalDate.now();
    private String startTime = LocalTime.now().toString();
    private String endTime = LocalTime.now().plusHours(1).toString();
    
    @Mock
    private JoinLectureAction mockJoinLectureAction;
    
    @Mock
    private RedirectAttributes mockRedirectAttributes;
    
    @Before
    public void before() {
    	lectureDto = aValidLectureDto().date(null).startTime(null).endTime(null).build();
    	activeLectureDto = aValidLectureDto().date(dateToday).startTime(LocalTime.parse(startTime)).endTime(LocalTime.parse(endTime)).build();
        this.lectureController = new LectureControllerStudent(mockJoinLectureAction);
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForJoin() {
    	assertThat(this.lectureController.getJoinLecturePage().getViewName(), is("joinLecture"));
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLecture() {
    	LectureDto lectureDto = TestLectureDtos.aValidLectureDto().build();
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
    	
    	assertThat(this.lectureController.join(password, mockRedirectAttributes).getViewName(), is("redirect:/student/lecture/active"));
    }
}
