package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.TestLectureDtos;


public class ActiveLectureControllerTest {
	private ActiveLectureController activeLectureController;
	
	@Before
	public void before() {
		activeLectureController = new ActiveLectureController();
	}
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLecture() {
    	LectureDto lectureDto = TestLectureDtos.aValidLectureDto().build();
    	assertThat(this.activeLectureController.displayActiveLecture(lectureDto).getViewName(), is("activeLecture"));
    }
}
