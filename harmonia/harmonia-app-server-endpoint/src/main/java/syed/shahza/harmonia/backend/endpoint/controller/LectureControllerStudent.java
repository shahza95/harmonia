package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.MoodAdapter;

@RestController
@RequestMapping("/student/lecture")
public class LectureControllerStudent {
    private final LectureService lectureService;
    private final LectureAdapter lectureAdapter;
    private final CommentAdapter commentAdapter;
    private final MoodAdapter moodAdapter;

    public LectureControllerStudent(LectureService lectureService, LectureAdapter lectureAdapter, CommentAdapter commentAdapter, MoodAdapter moodAdapter) {
        this.lectureService = lectureService;
        this.lectureAdapter = lectureAdapter;
        this.commentAdapter = commentAdapter;
        this.moodAdapter = moodAdapter;
    }
    
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public LectureDto join(@RequestBody String password) {
    	return this.lectureAdapter.toDto(this.lectureService.join(password));
    }
    
    @RequestMapping(value = "/active/comments/add", method = RequestMethod.POST)
    public CommentDto addComment(@RequestBody CommentDto commentDto) {
    	return this.commentAdapter.toDto(this.lectureService.addComment(this.commentAdapter.toDomain(commentDto)));
    }
    
    @RequestMapping(value = "/active/mood", method = RequestMethod.POST)
    public MoodDto addMood(@RequestBody MoodDto moodDto) {
    	return this.moodAdapter.toDto(this.lectureService.addMood(this.moodAdapter.toDomain(moodDto)));
    }
}
