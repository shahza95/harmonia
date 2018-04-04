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
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.MoodDtoList;
import syed.shahza.harmonia.restapi.action.EndLectureAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllMoodsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.ToggleFeaturesAction;

@Controller
@RequestMapping("/lecturer/lecture")
public class ActiveLectureControllerLecturer {
	private final GetLectureAction getLectureAction;
	private final GetAllCommentsAction getAllCommentsAction;
	private final GetAllMoodsAction getAllMoodsAction;
	private final ToggleFeaturesAction toggleFeaturesAction;
	private final EndLectureAction endLectureAction;

	public ActiveLectureControllerLecturer(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction, GetAllMoodsAction getAllMoodsAction, ToggleFeaturesAction toggleFeaturesAction, EndLectureAction endLectureAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
		this.getAllMoodsAction = getAllMoodsAction;
		this.toggleFeaturesAction = toggleFeaturesAction;
		this.endLectureAction = endLectureAction;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLecture");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("commentDtoList", this.getAllCommentsAction.getAll(lectureDto.getTitle()));
		return modelAndView;
	}
	
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
	
	@RequestMapping(value = "/active/{lectureTitle}/end", method = RequestMethod.GET)
	public ModelAndView endLecture(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		this.endLectureAction.endLecture(lectureDto);
		return new ModelAndView("redirect:/lecturer/lecture/active/" + lectureTitle + "/feedback");
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/feedback", method = RequestMethod.GET)
	public ModelAndView getActiveLectureFeedbackPage(@PathVariable("lectureTitle") String lectureTitle) {
		return new ModelAndView("lecturer/activeLectureFeedback", "lectureDto", this.getLectureAction.get(lectureTitle));
	}
	
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
}
