package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDtoList;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.MoodAdapter;

@CrossOrigin
@RestController
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;
    private final LectureAdapter lectureAdapter; 
    private final CommentAdapter commentAdapter; 
    private final MoodAdapter moodAdapter; 

    public LectureController(LectureService lectureService, LectureAdapter lectureAdapter, CommentAdapter commentAdapter, MoodAdapter moodAdapter) {
        this.lectureService = lectureService;
        this.lectureAdapter = lectureAdapter;
        this.commentAdapter = commentAdapter;
        this.moodAdapter = moodAdapter;
    }
    
    @RequestMapping(value = "/{lectureTitle}", method = RequestMethod.GET)
    public LectureDto getLecture(@PathVariable String lectureTitle) {
    	return this.lectureAdapter.toDto(this.lectureService.getLecture(lectureTitle));
    }

    @RequestMapping(value = "/active/{lectureTitle}/comments", method = RequestMethod.GET)
    public CommentDtoList getAllComments(@PathVariable String lectureTitle) {
        return this.commentAdapter.toDto(this.lectureService.getAllComments(lectureTitle));
    }
    
    @RequestMapping(value = "/active/{lectureTitle}/moods", method = RequestMethod.GET)
    public MoodDtoList getAllMoods(@PathVariable String lectureTitle) {
    	return this.moodAdapter.toDto(this.lectureService.getAllMoods(lectureTitle));
    }
}
