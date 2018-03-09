package syed.shahza.harmonia.frontend.controller;

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

	public LectureController(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("activeLecture");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("commentDtoList", this.getAllCommentsAction.getAll(lectureDto.getTitle()));
		return modelAndView;
	}
}
