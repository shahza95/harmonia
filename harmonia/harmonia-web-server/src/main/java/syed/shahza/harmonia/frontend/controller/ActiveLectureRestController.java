package syed.shahza.harmonia.frontend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;

@RestController
@RequestMapping("**/lecture")
public class ActiveLectureRestController {
	private final GetAllCommentsAction getAllCommentsAction;

	public ActiveLectureRestController(GetAllCommentsAction getAllCommentsAction) {
		this.getAllCommentsAction = getAllCommentsAction;
	}
	
    @RequestMapping(value = "/active/{lectureTitle}/comments/json", method = RequestMethod.GET)
    public CommentDtoList getAllComments(@PathVariable String lectureTitle) {
        return this.getAllCommentsAction.getAll(lectureTitle);
    }
}
