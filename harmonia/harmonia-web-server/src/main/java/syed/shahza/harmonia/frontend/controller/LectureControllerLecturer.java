package syed.shahza.harmonia.frontend.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;

@Controller
@RequestMapping("/lecturer/lecture")
public class LectureControllerLecturer {
	private final GetLectureAction getLectureAction;
	private final LectureCreationAction lectureCreationAction;

	public LectureControllerLecturer(GetLectureAction getLectureAction, LectureCreationAction lectureCreationAction) {
		this.getLectureAction = getLectureAction;
		this.lectureCreationAction = lectureCreationAction;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getLectureCreationPage() {
		return new ModelAndView("lecturer/lectureCreation");
	}
	
	@RequestMapping(value = "/view/{lectureTitle}", method = RequestMethod.GET)
	public ModelAndView getViewLecturePage(@PathVariable("lectureTitle") String lectureTitle) {
		return new ModelAndView("lecturer/viewLecture", "lectureDto", this.getLectureAction.get(lectureTitle)); 
	}
	
	// handle create lecture request
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute LectureDto lectureDto, @RequestParam("lectureDate") String date, @RequestParam("lectureStartTime") String startTime, @RequestParam("lectureEndTime") String endTime) {
		LectureDto returnedLectureDto = lectureCreationAction.create(getCompleteLectureDto(lectureDto, date, startTime, endTime));
		// if creation failed, refresh page
		if(returnedLectureDto.isEmpty()) {
			return new ModelAndView("lecturer/lectureCreation");
		}
		// if creation succeeded & lecture is active, redirect to comments page
		if(lectureIsActive(returnedLectureDto)){
			return new ModelAndView("redirect:/lecturer/lecture/active/" + returnedLectureDto.getTitle() + "/comments");
		}
		// if creation succeeded & lecture is not active, redirect to lecture details page
		return new ModelAndView("redirect:/lecturer/lecture/view/" + returnedLectureDto.getTitle());
	}
	
	// convert and set jodatime attributes (date & times)
	private LectureDto getCompleteLectureDto(LectureDto lectureDto, String date, String startTime, String endTime) {
		lectureDto.setDate(LocalDate.parse(date));
		lectureDto.setStartTime(LocalTime.parse(startTime));
		lectureDto.setEndTime(LocalTime.parse(endTime));
		
		return lectureDto;
	}
	
	// check if lecture is currently 'active' i.e. is taking place now (between specified start and end time)
	private Boolean lectureIsActive(LectureDto lectureDto) {
		if(LocalDate.now().equals(lectureDto.getDate()) && (LocalTime.now().isAfter(lectureDto.getStartTime()) || LocalTime.now().isEqual(lectureDto.getStartTime())) && LocalTime.now().isBefore(lectureDto.getEndTime())) {
			return true;
		}
		return false;
	}
}
