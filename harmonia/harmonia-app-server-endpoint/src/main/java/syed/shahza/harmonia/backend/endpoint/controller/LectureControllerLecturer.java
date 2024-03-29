package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.FeedbackDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.endpoint.adapter.FeedbackAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.QuestionAdapter;

//backend server lecturer specific lecture RESTful endpoints
@RestController
@RequestMapping("/lecturer/lecture")
public class LectureControllerLecturer {
    private final LectureService lectureService;
    private final LectureAdapter lectureAdapter;
    private final FeedbackAdapter feedbackAdapter;
    private final QuestionAdapter questionAdapter;

    public LectureControllerLecturer(LectureService lectureService, LectureAdapter lectureAdapter, FeedbackAdapter feedbackAdapter, QuestionAdapter questionAdapter) {
        this.lectureService = lectureService;
        this.lectureAdapter = lectureAdapter;
        this.feedbackAdapter = feedbackAdapter;
        this.questionAdapter = questionAdapter;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public LectureDto create(@RequestBody LectureDto lectureDto) {
        return this.lectureAdapter.toDto(this.lectureService.create(this.lectureAdapter.toDomain(lectureDto)));
    }
    
    @RequestMapping(value = "/active", method = RequestMethod.PUT)
    public void updateLecture(@RequestBody LectureDto lectureDto) {
    	this.lectureService.update(this.lectureAdapter.toDomain(lectureDto));
    }
    
    @RequestMapping(value = "/active/{lectureTitle}/feedback", method = RequestMethod.GET)
    public FeedbackDtoList getAllFeedback(@PathVariable String lectureTitle) {
    	return this.feedbackAdapter.toDto(this.lectureService.getAllFeedback(lectureTitle));
    }
    
    @RequestMapping(value = "/active/question", method = RequestMethod.PUT)
    public void updateQuestion(@RequestBody QuestionDto questionDto) {
    	this.lectureService.updateQuestion(this.questionAdapter.toDomain(questionDto));
    }
}
