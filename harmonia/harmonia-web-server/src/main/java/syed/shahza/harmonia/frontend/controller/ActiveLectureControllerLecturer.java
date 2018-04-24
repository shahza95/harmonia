package syed.shahza.harmonia.frontend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import syed.shahza.harmonia.backend.dto.EmotionDto;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.FeedbackDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.MoodDtoList;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.dto.QuestionDtoList;
import syed.shahza.harmonia.restapi.action.EndLectureAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllFeedbackAction;
import syed.shahza.harmonia.restapi.action.GetAllMoodsAction;
import syed.shahza.harmonia.restapi.action.GetAllQuestionsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.GetQuestionAction;
import syed.shahza.harmonia.restapi.action.ToggleFeaturesAction;
import syed.shahza.harmonia.restapi.action.UpdateQuestionAction;

@Controller
@RequestMapping("/lecturer/lecture")
public class ActiveLectureControllerLecturer {
	private final GetLectureAction getLectureAction;
	private final GetAllCommentsAction getAllCommentsAction;
	private final GetAllMoodsAction getAllMoodsAction;
	private final ToggleFeaturesAction toggleFeaturesAction;
	private final EndLectureAction endLectureAction;
	private final GetAllFeedbackAction getAllFeedbackAction;
	private final GetAllQuestionsAction getAllQuestionsAction;
	private final GetQuestionAction getQuestionAction;
	private final UpdateQuestionAction updateQuestionAction;

	public ActiveLectureControllerLecturer(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction, GetAllMoodsAction getAllMoodsAction, ToggleFeaturesAction toggleFeaturesAction, EndLectureAction endLectureAction, GetAllFeedbackAction getAllFeedbackAction, GetAllQuestionsAction getAllQuestionsAction, GetQuestionAction getQuestionAction, UpdateQuestionAction updateQuestionAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
		this.getAllMoodsAction = getAllMoodsAction;
		this.toggleFeaturesAction = toggleFeaturesAction;
		this.endLectureAction = endLectureAction;
		this.getAllFeedbackAction = getAllFeedbackAction;
		this.getAllQuestionsAction = getAllQuestionsAction;
		this.getQuestionAction = getQuestionAction;
		this.updateQuestionAction = updateQuestionAction;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLecture");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("commentDtoList", this.getAllCommentsAction.getAll(lectureDto.getTitle()));
		return modelAndView;
	}
	
	// handle comments enable/disable button click
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.POST) 
	public ModelAndView toggleCommenting(@PathVariable("lectureTitle") String lectureTitle, @RequestParam(defaultValue="Disable") String commentsToggle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		if(commentsToggle.equals("Disable")) {
			this.toggleFeaturesAction.disableCommenting(lectureDto);
		} else {
			this.toggleFeaturesAction.enableCommenting(lectureDto);
		}
		return getActiveLecturePage(lectureTitle);
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/mood", method = RequestMethod.GET)
	public ModelAndView getActiveLectureMoodPage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLectureMood");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("moodMap", getMoodSummaryMap(this.getAllMoodsAction.getAll(lectureDto.getTitle())));
		return modelAndView;
	}
	
	// handle mood enable/disable button click
	@RequestMapping(value = "/active/{lectureTitle}/mood", method = RequestMethod.POST) 
	public ModelAndView toggleMood(@PathVariable("lectureTitle") String lectureTitle, @RequestParam(defaultValue="Disable") String moodToggle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		if(moodToggle.equals("Disable")) {
			this.toggleFeaturesAction.disableMood(lectureDto);
		} else {
			this.toggleFeaturesAction.enableMood(lectureDto);
		}
		return getActiveLectureMoodPage(lectureTitle);
	}
	
	// handle end lecture button click- redirect to end of lecture feedback summary page
	@RequestMapping(value = "/active/{lectureTitle}/end", method = RequestMethod.GET)
	public ModelAndView endLecture(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		this.endLectureAction.endLecture(lectureDto);
		return new ModelAndView("redirect:/lecturer/lecture/active/" + lectureTitle + "/feedback");
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/feedback", method = RequestMethod.GET)
	public ModelAndView getActiveLectureFeedbackPage(@PathVariable("lectureTitle") String lectureTitle) {
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLectureFeedback");
		modelAndView.addObject("lectureDto", this.getLectureAction.get(lectureTitle));
		FeedbackDtoList feedbackDtoList = this.getAllFeedbackAction.getAll(lectureTitle);
		modelAndView.addObject("feedbackDtoList", feedbackDtoList);
		modelAndView.addObject("averageRating", getLectureAverageRating(feedbackDtoList));
		return modelAndView;
	}
	
	// handle end of lecture feedback enable/disable button click
	@RequestMapping(value = "/active/{lectureTitle}/feedback", method = RequestMethod.POST) 
	public ModelAndView toggleFeedback(@PathVariable("lectureTitle") String lectureTitle, @RequestParam(defaultValue="Disable") String feedbackToggle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		if(feedbackToggle.equals("Disable")) {
			this.toggleFeaturesAction.disableFeedback(lectureDto);
		} else {
			this.toggleFeaturesAction.enableFeedback(lectureDto);
		}
		return getActiveLectureFeedbackPage(lectureTitle);
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/questions", method = RequestMethod.GET)
	public ModelAndView getActiveLectureQuestionsPage(@PathVariable("lectureTitle") String lectureTitle) {
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLectureQuestions");
		modelAndView.addObject("lectureDto", this.getLectureAction.get(lectureTitle));
		QuestionDtoList questionDtoList = this.getAllQuestionsAction.getAll(lectureTitle);
		modelAndView.addObject("questionDtoList", questionDtoList);
		return modelAndView;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/{questionId}", method = RequestMethod.GET)
	public ModelAndView getActiveLectureQuestionThreadPage(@PathVariable("lectureTitle") String lectureTitle, @PathVariable String questionId) {
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLectureQuestionThread");
		modelAndView.addObject("lectureDto", this.getLectureAction.get(lectureTitle)); 
		modelAndView.addObject("questionDto", this.getQuestionAction.get(questionId)); 
		return modelAndView;
	}
	
	// handle answer question button click- update question with received answer
	@RequestMapping(value = "/active/{lectureTitle}/{questionId}", method = RequestMethod.POST)
	public ModelAndView answerQuestion(@PathVariable("lectureTitle") String lectureTitle, @PathVariable String questionId, @RequestParam String answer) {
		QuestionDto questionDto = this.getQuestionAction.get(questionId); 
		questionDto.setAnswer(answer);
		this.updateQuestionAction.update(questionDto);
		return getActiveLectureQuestionThreadPage(lectureTitle, questionId);
	}
	
	// handle questions enable/disable button click
	@RequestMapping(value = "/active/{lectureTitle}/questions", method = RequestMethod.POST) 
	public ModelAndView toggleQuestions(@PathVariable("lectureTitle") String lectureTitle, @RequestParam(defaultValue="Disable") String questionsToggle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		if(questionsToggle.equals("Disable")) {
			this.toggleFeaturesAction.disableQuestions(lectureDto);
		} else {
			this.toggleFeaturesAction.enableQuestions(lectureDto);
		}
		return getActiveLectureQuestionsPage(lectureTitle);
	}
	
	// construct a map of moods, calculating totals for each emotion, ready to feed to MoodChart javascript
	protected static Map<String, Integer> getMoodSummaryMap(MoodDtoList moodDtoList) {
		Map<String, Integer> moodMap = new HashMap<String, Integer>();
		for(EmotionDto emotionDto: EmotionDto.values()){
			Integer moodCount = 0;
			for(MoodDto moodDto: moodDtoList.getMoodDtoList()) {
				if(moodDto.getEmotionDto() == emotionDto) {
					moodCount += 1;
				}
			}
			moodMap.put(emotionDto.toString(), moodCount);
		}
		return moodMap;
	}
	
	// calculate average rating from end of lecture feedback ratings
	protected static double getLectureAverageRating(FeedbackDtoList feedbackDtoList) {
		if(feedbackDtoList.getFeedbackDtoList().isEmpty()) {
			return 0.0;
		}
		return feedbackDtoList.getFeedbackDtoList().stream().mapToDouble(FeedbackDto::getRating).average().getAsDouble();
	}
}
