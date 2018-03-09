package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;

@RestController
@RequestMapping("/student/lecture")
public class LectureControllerStudent {
    private final LectureService lectureService;
    private final LectureAdapter lectureAdapter;
    private final CommentAdapter commentAdapter;

    public LectureControllerStudent(LectureService lectureService, LectureAdapter lectureAdapter, CommentAdapter commentAdapter) {
        this.lectureService = lectureService;
        this.lectureAdapter = lectureAdapter;
        this.commentAdapter = commentAdapter;
    }
    
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public LectureDto join(@RequestBody String password) {
    	return this.lectureAdapter.toDto(this.lectureService.join(password));
    }
    
    @RequestMapping(value = "/active/comments/add", method = RequestMethod.POST)
    public CommentDto addComment(@RequestBody CommentDto commentDto) {
    	System.out.println("Endpoint ----> " + commentDto.getMessage());
    	return this.commentAdapter.toDto(this.lectureService.addComment(this.commentAdapter.toDomain(commentDto)));
    }
}
