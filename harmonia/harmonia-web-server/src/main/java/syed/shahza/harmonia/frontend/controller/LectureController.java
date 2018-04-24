package syed.shahza.harmonia.frontend.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;

@Controller
@RequestMapping("/home")
public class LectureController {
	private final JoinLectureAction joinLectureAction;

	public LectureController(JoinLectureAction joinLectureAction) {
		this.joinLectureAction = joinLectureAction;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getJoinLecturePage() {
		return new ModelAndView("shared/home"); 
	}
	
	// handle student join lecture request
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView join(@RequestParam("password") String password) {
		LectureDto lectureDto = this.joinLectureAction.join(password);
		// if password correct & lecture is active, redirect to comments (student version)
		if(!lectureDto.isEmpty() && lectureIsActive(lectureDto)) {
			return new ModelAndView("redirect:/student/lecture/active/" + lectureDto.getTitle() + "/comments"); 			
		}
		// else refresh page
		return new ModelAndView("shared/home");
	}
	
	// handle Lecturer Login button click: redirect to login page
	@RequestMapping(params="login", method = RequestMethod.GET)
	public ModelAndView lecturerLogin() {
		return new ModelAndView("redirect:/lecturer/login");
	}
	
	// check if lecture is currently active i.e. now is on specified date and between specified start and end time
	private Boolean lectureIsActive(LectureDto lectureDto) {
		if(LocalDate.now().equals(lectureDto.getDate()) && (LocalTime.now().isAfter(lectureDto.getStartTime()) || LocalTime.now().isEqual(lectureDto.getStartTime())) && LocalTime.now().isBefore(lectureDto.getEndTime())) {
			return true;
		}
		return false;
	}
}
