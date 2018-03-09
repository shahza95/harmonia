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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;

@Controller
@RequestMapping("/student/lecture")
public class LectureControllerStudent {
	private final GetLectureAction getLectureAction;
	private final JoinLectureAction joinLectureAction;
	private final AddCommentAction addCommentAction;

	public LectureControllerStudent(GetLectureAction getLectureAction, JoinLectureAction joinLectureAction, AddCommentAction addCommentAction) {
		this.getLectureAction = getLectureAction;
		this.joinLectureAction = joinLectureAction;
		this.addCommentAction = addCommentAction;
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView getJoinLecturePage() {
		return new ModelAndView("joinLecture"); 
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView join(@RequestParam("password") String password, RedirectAttributes redirectAttributes) {
		LectureDto lectureDto = this.joinLectureAction.join(password);
		if(!lectureDto.isEmpty() && lectureIsActive(lectureDto)) {
			redirectAttributes.addFlashAttribute("lectureDto", lectureDto);
			return new ModelAndView("redirect:/student/lecture/active/" + lectureDto.getTitle() + "/comments"); 			
		}
		return new ModelAndView("joinLecture");
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.POST)
	public ModelAndView addComment(@PathVariable("lectureTitle") String lectureTitle, @ModelAttribute CommentDto commentDto, RedirectAttributes redirectAttributes) {
		commentDto.setLectureDto(this.getLectureAction.get(lectureTitle));
//		CommentDto returnedCommentDto = 
		this.addCommentAction.addComment(commentDto);
		redirectAttributes.addFlashAttribute("lectureDto", commentDto.getLectureDto());
//		if(returnedCommentDto != null) {
		return new ModelAndView("redirect:/student/lecture/active/" + lectureTitle +"/comments"); 
//		}
		//?????????
//		return new ModelAndView();
	}
	
	private Boolean lectureIsActive(LectureDto lectureDto) {
		if(LocalDate.now().equals(lectureDto.getDate()) && (LocalTime.now().isAfter(lectureDto.getStartTime()) || LocalTime.now().isEqual(lectureDto.getStartTime())) && LocalTime.now().isBefore(lectureDto.getEndTime())) {
			return true;
		}
		return false;
	}
}
