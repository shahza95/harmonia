package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLectureDtos.aValidLectureDto;
import static syed.shahza.harmonia.backend.dto.TestLectureDtos.anEmptyLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;

@RunWith(MockitoJUnitRunner.class)
public class LectureControllerTest {
    private LectureController lectureController;
    private LectureDto lectureDto;
    
    @Mock
    private LectureCreationAction mockLectureCreationAction;

    @Before
    public void before() {
    	lectureDto = aValidLectureDto().build();
        this.lectureController = new LectureController(mockLectureCreationAction);
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGet() {
        assertThat(this.lectureController.getLectureCreationPage().getViewName(), is("lectureCreation"));
    }
    
    @Test
    public void createRedirectsToViewLecturePageOnlyIfResponseIsDto() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	assertThat(this.lectureController.create(lectureDto).getViewName(), is("viewLecture"));
    }
    
    @Test
    public void successfulCreatePassesDtoAsModelValueToViewLecturePage() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(lectureDto);
    	assertThat(this.lectureController.create(lectureDto).getModelMap().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void createRedirectsBackToCreateLectureOnlyIfResponseEmptyDto() {
    	when(mockLectureCreationAction.create(lectureDto)).thenReturn(anEmptyLectureDto().build());
    	assertThat(this.lectureController.create(lectureDto).getViewName(), is("lectureCreation"));
    }
}
