package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;
    private final CommentAdapter commentAdapter;

    public LectureController(LectureService lectureService, CommentAdapter commentAdapter) {
        this.lectureService = lectureService;
        this.commentAdapter = commentAdapter;
    }

    @RequestMapping(value = "/active/comments/{lectureTitle}", method = RequestMethod.GET)
    public CommentDtoList getAllComments(@PathVariable String lectureTitle) {
        return this.commentAdapter.toDto(this.lectureService.getAllComments(lectureTitle));
    }
}
