package syed.shahza.harmonia.frontend.controller;

import static syed.shahza.harmonia.frontend.controller.ActiveLectureControllerLecturer.getMoodSummaryMap;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.QuestionDtoList;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllMoodsAction;
import syed.shahza.harmonia.restapi.action.GetAllQuestionsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;

// frontend rest endpoints- exposing json format results from backend
// main use is for retrieving any data that needs continual upating i.e. comments, mood & questions
@RestController
@RequestMapping("**/lecture")
public class ActiveLectureRestController {
	private final GetLectureAction getLectureAction;
	private final GetAllCommentsAction getAllCommentsAction;
	private final GetAllMoodsAction getAllMoodsAction;
	private final GetAllQuestionsAction getAllQuestionsAction;

	public ActiveLectureRestController(GetLectureAction getLectureAction, GetAllCommentsAction getAllCommentsAction, GetAllMoodsAction getAllMoodsAction, GetAllQuestionsAction getAllQuestionsAction) {
		this.getLectureAction = getLectureAction;
		this.getAllCommentsAction = getAllCommentsAction;
		this.getAllMoodsAction = getAllMoodsAction;
		this.getAllQuestionsAction = getAllQuestionsAction;
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
    
    @RequestMapping(value = "/active/{lectureTitle}/questions/json", method = RequestMethod.GET)
    public QuestionDtoList getAllQuestions(@PathVariable String lectureTitle) {
    	return this.getAllQuestionsAction.getAll(lectureTitle);
    }
}
