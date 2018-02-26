package syed.shahza.harmonia.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;

@Controller
@RequestMapping("/lecture")
public class LectureController {
	private final LectureCreationAction lectureCreationAction;

	public LectureController(LectureCreationAction lectureCreationAction) {
		this.lectureCreationAction = lectureCreationAction;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getLectureCreationPage() {
		return new ModelAndView("lectureCreation");
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(LectureDto lectureDto) {
		LectureDto returnedLectureDto = lectureCreationAction.create(lectureDto);
		if(returnedLectureDto.isEmpty()) {
			return new ModelAndView("lectureCreation");
		}
		return new ModelAndView("viewLecture", "lectureDto", returnedLectureDto);
	}
}
