package syed.shahza.harmonia.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.SendMoodAction;

@Controller
@RequestMapping("/student/lecture")
public class ActiveLectureControllerStudent {
	private final GetLectureAction getLectureAction;
	private final GetAllCommentsAction getAllCommentsAction;
	private final AddCommentAction addCommentAction;
	private final SendMoodAction sendMoodAction;

	public ActiveLectureControllerStudent(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction,  AddCommentAction addCommentAction, SendMoodAction sendMoodAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
		this.addCommentAction = addCommentAction;
		this.sendMoodAction = sendMoodAction;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("student/activeLecture");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("commentDtoList", this.getAllCommentsAction.getAll(lectureDto.getTitle()));
		return modelAndView;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.POST)
	public ModelAndView addComment(@PathVariable("lectureTitle") String lectureTitle, @ModelAttribute CommentDto commentDto) {
		commentDto.setLectureDto(this.getLectureAction.get(lectureTitle));
		this.addCommentAction.addComment(commentDto);
		return new ModelAndView("redirect:/student/lecture/active/" + lectureTitle +"/comments"); 
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/mood", method = RequestMethod.GET)
	public ModelAndView getActiveLectureMoodPage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		return new ModelAndView("student/activeLectureMood", "lectureDto", lectureDto);
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/mood", method = RequestMethod.POST)
	public ModelAndView sendMood(@PathVariable("lectureTitle") String lectureTitle, @ModelAttribute MoodDto moodDto) {
		moodDto.setLectureDto(this.getLectureAction.get(lectureTitle));
		this.sendMoodAction.sendMood(moodDto);
		return new ModelAndView("redirect:/student/lecture/active/" + lectureTitle +"/mood"); 
	}
}
