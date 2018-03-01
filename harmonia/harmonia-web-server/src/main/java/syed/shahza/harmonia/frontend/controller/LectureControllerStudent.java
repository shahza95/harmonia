package syed.shahza.harmonia.frontend.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;

@Controller
@RequestMapping("/student/lecture")
public class LectureControllerStudent {
	private final JoinLectureAction joinLectureAction;

	public LectureControllerStudent(JoinLectureAction joinLectureAction) {
		this.joinLectureAction = joinLectureAction;
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView getJoinLecturePage() {
		return new ModelAndView("joinLecture"); 
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@ModelAttribute("lectureDto") LectureDto lectureDto) {
		//return student version-TO DO
		return new ModelAndView("activeLecture", "lectureDto", lectureDto); 
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView join(@RequestParam("password") String password, RedirectAttributes redirectAttributes) {
		LectureDto returnedLectureDto = this.joinLectureAction.join(password);
		if(!returnedLectureDto.isEmpty() && lectureIsActive(returnedLectureDto)) {
			redirectAttributes.addFlashAttribute("lectureDto", returnedLectureDto);
			return new ModelAndView("redirect:/student/lecture/active"); 			
		}
		return new ModelAndView("joinLecture");
	}
	
	private Boolean lectureIsActive(LectureDto lectureDto) {
		if(LocalDate.now().equals(lectureDto.getDate()) && (LocalTime.now().isAfter(lectureDto.getStartTime()) || LocalTime.now().isEqual(lectureDto.getStartTime())) && LocalTime.now().isBefore(lectureDto.getEndTime())) {
			return true;
		}
		return false;
	}
}
