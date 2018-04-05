package syed.shahza.harmonia.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.EmotionDto;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.AddFeedbackAction;
import syed.shahza.harmonia.restapi.action.AddQuestionAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllQuestionsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.RemoveMoodAction;
import syed.shahza.harmonia.restapi.action.SendMoodAction;

@Controller
@SessionAttributes("currentEmoji")
@RequestMapping("/student/lecture")
public class ActiveLectureControllerStudent {
	private final GetLectureAction getLectureAction;
	private final GetAllCommentsAction getAllCommentsAction;
	private final AddCommentAction addCommentAction;
	private final SendMoodAction sendMoodAction;
	private final RemoveMoodAction removeMoodAction;
	private final AddFeedbackAction addFeedbackAction;
	private final AddQuestionAction addQuestionAction;
	private final GetAllQuestionsAction getAllQuestionsAction;

	public ActiveLectureControllerStudent(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction,  AddCommentAction addCommentAction, SendMoodAction sendMoodAction, RemoveMoodAction removeMoodAction, AddFeedbackAction addFeedbackAction, AddQuestionAction addQuestionAction, GetAllQuestionsAction getAllQuestionsAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
		this.addCommentAction = addCommentAction;
		this.sendMoodAction = sendMoodAction;
		this.removeMoodAction = removeMoodAction;
		this.addFeedbackAction = addFeedbackAction;
		this.addQuestionAction = addQuestionAction;
		this.getAllQuestionsAction = getAllQuestionsAction;
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
	public ModelAndView sendMood(@PathVariable("lectureTitle") String lectureTitle, @RequestParam("mood") String mood, @RequestParam(required = false) String currentEmoji, RedirectAttributes redirectAttributes) {
		MoodDto moodDto = constructMoodDto(mood, lectureTitle);
		if(!currentEmoji.isEmpty()) {
			this.removeMoodAction.removeMoodByEmoji(lectureTitle, currentEmoji);			
		}
		this.sendMoodAction.sendMood(moodDto);
	    redirectAttributes.addFlashAttribute("currentEmoji", moodDto.getEmoji());
		return new ModelAndView("redirect:/student/lecture/active/" + lectureTitle +"/mood"); 
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/feedback", method = RequestMethod.GET)
	public ModelAndView getActiveLectureFeedbackPage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		return new ModelAndView("student/activeLectureFeedback", "lectureDto", lectureDto);
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/feedback", method = RequestMethod.POST)
	public ModelAndView addFeedback(@PathVariable("lectureTitle") String lectureTitle, @ModelAttribute FeedbackDto feedbackDto) {
		feedbackDto.setLectureDto(this.getLectureAction.get(lectureTitle));
		this.addFeedbackAction.addFeedback(feedbackDto);
		return new ModelAndView("redirect:/student/lecture/join"); 
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/questions", method = RequestMethod.GET)
	public ModelAndView getActiveLectureQuestionsPage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("student/activeLectureQuestions");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("questionDtoList", this.getAllQuestionsAction.getAll(lectureDto.getTitle()));
		return modelAndView;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/questions", method = RequestMethod.POST)
	public ModelAndView addQuestion(@PathVariable("lectureTitle") String lectureTitle, @ModelAttribute QuestionDto questionDto) {
		questionDto.setLectureDto(this.getLectureAction.get(lectureTitle));
		this.addQuestionAction.addQuestion(questionDto);
		return new ModelAndView("redirect:/student/lecture/active/" + lectureTitle +"/questions"); 
	}

	private MoodDto constructMoodDto(String mood, String lectureTitle) {
		String[] moodParts = mood.split(" ");
		EmotionDto emotionDto = EmotionDto.valueOf(moodParts[0]);
		return MoodDto.aMoodDto().emotionDto(emotionDto).emoji(moodParts[1]).lectureDto(this.getLectureAction.get(lectureTitle)).build();
	}
}