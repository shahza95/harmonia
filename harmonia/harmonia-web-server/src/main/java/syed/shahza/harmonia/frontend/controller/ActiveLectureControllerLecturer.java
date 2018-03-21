package syed.shahza.harmonia.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllMoodsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;

@Controller
@RequestMapping("/lecturer/lecture")
public class ActiveLectureControllerLecturer {
	private final GetLectureAction getLectureAction;
	private final GetAllCommentsAction getAllCommentsAction;
	private final GetAllMoodsAction getAllMoodsAction;

	public ActiveLectureControllerLecturer(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction, GetAllMoodsAction getAllMoodsAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
		this.getAllMoodsAction = getAllMoodsAction;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLecture");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("commentDtoList", this.getAllCommentsAction.getAll(lectureDto.getTitle()));
		return modelAndView;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/mood", method = RequestMethod.GET)
	public ModelAndView getActiveLectureMoodPage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLectureMood");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("moodDtoList", this.getAllMoodsAction.getAll(lectureDto.getTitle()));
		return modelAndView;
	}
}
