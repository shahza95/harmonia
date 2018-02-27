package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class ActiveLectureControllerTest {
	private ActiveLectureController activeLectureController;
	
	@Before
	public void before() {
		activeLectureController = new ActiveLectureController();
	}
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLecture() {
    	assertThat(this.activeLectureController.displayActiveLecture().getViewName(), is("activeLecture"));
    }
}
