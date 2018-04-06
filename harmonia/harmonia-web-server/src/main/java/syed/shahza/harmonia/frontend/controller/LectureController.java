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
	
	@RequestMapping(params="join", method = RequestMethod.POST)
	public ModelAndView join(@RequestParam("password") String password) {
		LectureDto lectureDto = this.joinLectureAction.join(password);
		if(!lectureDto.isEmpty() && lectureIsActive(lectureDto)) {
			return new ModelAndView("redirect:/student/lecture/active/" + lectureDto.getTitle() + "/comments"); 			
		}
		return new ModelAndView("shared/home");
	}
	
	@RequestMapping(params="login", method = RequestMethod.POST)
	public ModelAndView lecturerLogin() {
		return new ModelAndView("redirect:/lecturer/login");
	}
	
	private Boolean lectureIsActive(LectureDto lectureDto) {
		if(LocalDate.now().equals(lectureDto.getDate()) && (LocalTime.now().isAfter(lectureDto.getStartTime()) || LocalTime.now().isEqual(lectureDto.getStartTime())) && LocalTime.now().isBefore(lectureDto.getEndTime())) {
			return true;
		}
		return false;
	}
}
