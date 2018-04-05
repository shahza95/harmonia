package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDtoList;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.dto.QuestionDtoList;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.MoodAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.QuestionAdapter;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;
    private final LectureAdapter lectureAdapter; 
    private final CommentAdapter commentAdapter; 
    private final MoodAdapter moodAdapter; 
    private final QuestionAdapter questionAdapter; 

    public LectureController(LectureService lectureService, LectureAdapter lectureAdapter, CommentAdapter commentAdapter, MoodAdapter moodAdapter, QuestionAdapter questionAdapter) {
        this.lectureService = lectureService;
        this.lectureAdapter = lectureAdapter;
        this.commentAdapter = commentAdapter;
        this.moodAdapter = moodAdapter;
        this.questionAdapter = questionAdapter;
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
    
    @RequestMapping(value = "/active/{lectureTitle}/questions", method = RequestMethod.GET)
    public QuestionDtoList getAllQuestions(@PathVariable String lectureTitle) {
    	return this.questionAdapter.toDto(this.lectureService.getAllQuestions(lectureTitle));
    }
    
    @RequestMapping(value = "/active/questions/{questionId}", method = RequestMethod.GET)
    public QuestionDto getQuestion(@PathVariable String questionId) {
    	return this.questionAdapter.toDto(this.lectureService.getQuestion(questionId));
    }
}
