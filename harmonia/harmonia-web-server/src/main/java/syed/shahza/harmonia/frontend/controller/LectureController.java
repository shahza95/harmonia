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
import syed.shahza.harmonia.restapi.action.LectureCreationAction;

@Controller
@RequestMapping("/lecture")
public class LectureController {
	private final LectureCreationAction lectureCreationAction;
	private final JoinLectureAction joinLectureAction;

	public LectureController(LectureCreationAction lectureCreationAction, JoinLectureAction joinLectureAction) {
		this.lectureCreationAction = lectureCreationAction;
		this.joinLectureAction = joinLectureAction;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getLectureCreationPage() {
		return new ModelAndView("lectureCreation");
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView getViewLecturePage(@ModelAttribute("lectureDto") LectureDto lectureDto) {
		return new ModelAndView("viewLecture", "lectureDto", lectureDto); 
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView getJoinLecturePage() {
		return new ModelAndView("joinLecture"); 
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute LectureDto lectureDto, @RequestParam("lectureDate") String date, @RequestParam("lectureStartTime") String startTime, @RequestParam("lectureEndTime") String endTime, RedirectAttributes redirectAttributes) {
		LectureDto returnedLectureDto = lectureCreationAction.create(getCompleteLectureDto(lectureDto, date, startTime, endTime));
		if(returnedLectureDto.isEmpty()) {
			return new ModelAndView("lectureCreation");
		}
		if(lectureIsActive(returnedLectureDto.getDate(), returnedLectureDto.getStartTime(), returnedLectureDto.getEndTime())){
			return new ModelAndView("redirect:/lecture/active");
		}
		redirectAttributes.addFlashAttribute("lectureDto", returnedLectureDto);
		return new ModelAndView("redirect:/lecture/view");
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView join(@RequestParam("password") String password, RedirectAttributes redirectAttributes) {
		LectureDto returnedLectureDto = this.joinLectureAction.join(password);
		if(returnedLectureDto.isEmpty()) {
			return new ModelAndView("joinLecture");
		}
		redirectAttributes.addFlashAttribute("lectureDto", returnedLectureDto);
		return new ModelAndView("redirect:/lecture/active"); 			
	}
	
	private LectureDto getCompleteLectureDto(LectureDto lectureDto, String date, String startTime, String endTime) {
		lectureDto.setDate(LocalDate.parse(date));
		lectureDto.setStartTime(LocalTime.parse(startTime));
		lectureDto.setEndTime(LocalTime.parse(endTime));
		
		return lectureDto;
	}
	
	private Boolean lectureIsActive(LocalDate date, LocalTime startTime, LocalTime endTime) {
		if(LocalDate.now().equals(date) && LocalTime.now().isAfter(startTime) && LocalTime.now().isBefore(endTime)) {
			return true;
		}
		return false;
	}
}
