package syed.shahza.harmonia.frontend.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;

@Controller
@RequestMapping("**/lecture")
public class LectureController {
	private final GetLectureAction getLectureAction;
	private final GetAllCommentsAction getAllCommentsAction;
	private LectureDto lectureDto;

	public LectureController(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@PathVariable("lectureTitle") String lectureTitle) {
		this.lectureDto = this.getLectureAction.get(lectureTitle);
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
