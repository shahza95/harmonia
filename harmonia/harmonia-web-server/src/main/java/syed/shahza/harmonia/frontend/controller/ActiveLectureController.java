package syed.shahza.harmonia.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.LectureDto;

@Controller
@RequestMapping("/lecture/active")
public class ActiveLectureController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView displayActiveLecture(@ModelAttribute("lectureDto") LectureDto lectureDto) {
		return new ModelAndView("activeLecture", "lectureDto", lectureDto); 
	}
}