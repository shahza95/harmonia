package syed.shahza.harmonia.frontend.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;

@Controller
@RequestMapping("**/lecture")
public class LectureController {
	private final GetAllCommentsAction getAllCommentsAction;
	private LectureDto lectureDto;

	public LectureController(GetAllCommentsAction getAllCommentsAction) {
		this.getAllCommentsAction = getAllCommentsAction;
	}
	
	@RequestMapping(value = "/active/comments", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@ModelAttribute("lectureDto") LectureDto lectureDto) {
		this.lectureDto = lectureDto;
		ModelAndView modelAndView = new ModelAndView("activeLecture");
		modelAndView.addObject("lectureDto", this.lectureDto);
		modelAndView.addObject("commentDtoList", this.getAllCommentsAction.getAll(this.lectureDto.getTitle()));
		return modelAndView;
	}
	
	//not working- because of **/lecture mapping??
	@JmsListener(destination = "lecture", containerFactory = "myFactory")
	public ModelAndView updateComments() {
		ModelAndView modelAndView = new ModelAndView("activeLecture");
		modelAndView.addObject("lectureDto", this.lectureDto);
		modelAndView.addObject("commentDtoList", this.getAllCommentsAction.getAll(this.lectureDto.getTitle()));
		return modelAndView;
	}
}
