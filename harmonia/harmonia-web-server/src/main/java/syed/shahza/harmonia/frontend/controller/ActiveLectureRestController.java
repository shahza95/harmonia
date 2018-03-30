package syed.shahza.harmonia.frontend.controller;

import static syed.shahza.harmonia.frontend.controller.ActiveLectureControllerLecturer.getMoodSummaryMap;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllMoodsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;

@RestController
@RequestMapping("**/lecture")
public class ActiveLectureRestController {
	private final GetLectureAction getLectureAction;
	private final GetAllCommentsAction getAllCommentsAction;
	private final GetAllMoodsAction getAllMoodsAction;

	public ActiveLectureRestController(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction, GetAllMoodsAction getAllMoodsAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
		this.getAllMoodsAction = getAllMoodsAction;
	}
	
	@RequestMapping(value = "/active/{lectureTitle}/json", method = RequestMethod.GET)
	public LectureDto getLecture(@PathVariable String lectureTitle) {
		return this.getLectureAction.get(lectureTitle);
	}
	
    @RequestMapping(value = "/active/{lectureTitle}/comments/json", method = RequestMethod.GET)
    public CommentDtoList getAllComments(@PathVariable String lectureTitle) {
        return this.getAllCommentsAction.getAll(lectureTitle);
    }
    
    @RequestMapping(value = "/active/{lectureTitle}/mood/json", method = RequestMethod.GET)
    public Map<String, Integer> getMoodSummary(@PathVariable String lectureTitle) {
    	return getMoodSummaryMap(this.getAllMoodsAction.getAll(lectureTitle));
    }
}
