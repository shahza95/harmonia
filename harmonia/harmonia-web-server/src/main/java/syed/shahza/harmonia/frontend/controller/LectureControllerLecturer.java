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
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;

@Controller
@RequestMapping("/lecturer/lecture")
public class LectureControllerLecturer {
	private final LectureCreationAction lectureCreationAction;
	private final GetAllCommentsAction getAllCommentsAction;

	public LectureControllerLecturer(LectureCreationAction lectureCreationAction, GetAllCommentsAction getAllCommentsAction) {
		this.lectureCreationAction = lectureCreationAction;
		this.getAllCommentsAction = getAllCommentsAction;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getLectureCreationPage() {
		return new ModelAndView("lectureCreation");
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView getViewLecturePage(@ModelAttribute("lectureDto") LectureDto lectureDto) {
		return new ModelAndView("viewLecture", "lectureDto", lectureDto); 
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@ModelAttribute("lectureDto") LectureDto lectureDto) {
		ModelAndView modelAndView = new ModelAndView("activeLecture");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("commentDtoList", this.getAllCommentsAction.getAll(lectureDto));
		return modelAndView;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute LectureDto lectureDto, @RequestParam("lectureDate") String date, @RequestParam("lectureStartTime") String startTime, @RequestParam("lectureEndTime") String endTime, RedirectAttributes redirectAttributes) {
		LectureDto returnedLectureDto = lectureCreationAction.create(getCompleteLectureDto(lectureDto, date, startTime, endTime));
		if(returnedLectureDto.isEmpty()) {
			return new ModelAndView("lectureCreation");
		}
		redirectAttributes.addFlashAttribute("lectureDto", returnedLectureDto);
		if(lectureIsActive(returnedLectureDto)){
			return new ModelAndView("redirect:/lecturer/lecture/active");
		}
		return new ModelAndView("redirect:/lecturer/lecture/view");
	}
	
	private LectureDto getCompleteLectureDto(LectureDto lectureDto, String date, String startTime, String endTime) {
		lectureDto.setDate(LocalDate.parse(date));
		lectureDto.setStartTime(LocalTime.parse(startTime));
		lectureDto.setEndTime(LocalTime.parse(endTime));
		
		return lectureDto;
	}
	
	private Boolean lectureIsActive(LectureDto lectureDto) {
		if(LocalDate.now().equals(lectureDto.getDate()) && (LocalTime.now().isAfter(lectureDto.getStartTime()) || LocalTime.now().isEqual(lectureDto.getStartTime())) && LocalTime.now().isBefore(lectureDto.getEndTime())) {
			return true;
		}
		return false;
	}
}
