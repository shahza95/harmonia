package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anActiveLectureDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.anEmptyLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerStudentTest {
    private LectureControllerStudent lectureController;
    private LectureDto lectureDto;
    private LectureDto activeLectureDto;
    private String password = "somePassword";
    
    @Mock
    private JoinLectureAction mockJoinLectureAction;
    
    @Before
    public void before() {
    	this.lectureDto = aValidLectureDto().date(null).startTime(null).endTime(null).build();
    	this.activeLectureDto = anActiveLectureDto().build();
        this.lectureController = new LectureControllerStudent(this.mockJoinLectureAction);
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForJoin() {
    	assertThat(this.lectureController.getJoinLecturePage().getViewName(), is("student/joinLecture"));
    }
    
    @Test
    public void joinReturnsJoinLecturePageIfPasswordInvalidThereforeReturnedDtoIsEmpty() {
    	when(this.mockJoinLectureAction.join(password)).thenReturn(anEmptyLectureDto().build());
    	
    	assertThat(this.lectureController.join(password).getViewName(), is("student/joinLecture"));
    }
    
    @Test
    public void joinRedirectsToJoinLecturePageIfPasswordValidThereforeReturnedDtoNotEmptyButLectureIsNotNow() {
    	when(this.mockJoinLectureAction.join(password)).thenReturn(lectureDto);
    	
    	assertThat(this.lectureController.join(password).getViewName(), is("student/joinLecture"));
    }
    
    @Test
    public void joinRedirectsToActiveLecturePageIfPasswordValidThereforeReturnedDtoNotEmptyAndLectureIsNow() {
    	when(this.mockJoinLectureAction.join(password)).thenReturn(activeLectureDto);
    	
    	assertThat(this.lectureController.join(password).getViewName(), is("redirect:/student/lecture/active/" + activeLectureDto.getTitle() +"/comments"));
    }
}
