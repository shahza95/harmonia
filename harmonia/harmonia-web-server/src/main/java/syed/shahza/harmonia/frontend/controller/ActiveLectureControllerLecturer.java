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

	public ActiveLectureControllerLecturer(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction, GetAllMoodsAction getAllMoodsAction, ToggleFeaturesAction toggleFeaturesAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
		this.getAllMoodsAction = getAllMoodsAction;
		this.toggleFeaturesAction = toggleFeaturesAction;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.GET)
	public ModelAndView getActiveLecturePage(@PathVariable("lectureTitle") String lectureTitle, @RequestParam(defaultValue="Disable") String toggle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLecture");
		modelAndView.addObject("lectureDto", lectureDto);
		modelAndView.addObject("toggleValue", toggle);
		modelAndView.addObject("commentDtoList", this.getAllCommentsAction.getAll(lectureDto.getTitle()));
		return modelAndView;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.POST) 
	public ModelAndView toggleCommenting(@PathVariable("lectureTitle") String lectureTitle, @RequestParam("toggle") String toggle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		if(toggle.equals("Disable")) {
			this.toggleFeaturesAction.disableCommenting(lectureDto);
			toggle = "Enable";
		} else {
			this.toggleFeaturesAction.enableCommenting(lectureDto);
			toggle = "Disable";
		}
		return getActiveLecturePage(lectureTitle, toggle);
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/mood", method = RequestMethod.GET)
	public ModelAndView getActiveLectureMoodPage(@PathVariable("lectureTitle") String lectureTitle) {
		LectureDto lectureDto = this.getLectureAction.get(lectureTitle);
		ModelAndView modelAndView = new ModelAndView("lecturer/activeLectureMood");
		modelAndView.addObject("lectureDto", lectureDto);

		Map<String, Integer> moodMap = getMoodSummaryMap(this.getAllMoodsAction.getAll(lectureDto.getTitle()));
		modelAndView.addObject("moodMap", moodMap);
		
		return modelAndView;
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
