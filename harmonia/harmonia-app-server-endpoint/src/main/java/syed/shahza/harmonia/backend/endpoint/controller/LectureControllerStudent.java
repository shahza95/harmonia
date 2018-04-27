package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.endpoint.adapter.CommentAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.FeedbackAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.MoodAdapter;
import syed.shahza.harmonia.backend.endpoint.adapter.QuestionAdapter;

//backend server student specific lecture RESTful endpoints
@RestController
@RequestMapping("/student/lecture")
public class LectureControllerStudent {
    private final LectureService lectureService;
    private final LectureAdapter lectureAdapter;
    private final CommentAdapter commentAdapter;
    private final MoodAdapter moodAdapter;
    private final FeedbackAdapter feedbackAdapter;
    private final QuestionAdapter questionAdapter;

    public LectureControllerStudent(LectureService lectureService, LectureAdapter lectureAdapter, CommentAdapter commentAdapter, MoodAdapter moodAdapter, FeedbackAdapter feedbackAdapter, QuestionAdapter questionAdapter) {
        this.lectureService = lectureService;
        this.lectureAdapter = lectureAdapter;
        this.commentAdapter = commentAdapter;
        this.moodAdapter = moodAdapter;
        this.feedbackAdapter = feedbackAdapter;
        this.questionAdapter = questionAdapter;
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
    
    @RequestMapping(value = "/active/{lectureTitle}/mood/{emoji}", method = RequestMethod.DELETE)
    public void removeMoodByEmoji(@PathVariable("lectureTitle") String lectureTitle, @PathVariable("emoji") String emoji) {
    	this.lectureService.removeMood(lectureTitle, emoji);
    }
     
    @RequestMapping(value = "/active/feedback/add", method = RequestMethod.POST)
    public FeedbackDto addFeedback(@RequestBody FeedbackDto feedbackDto) {
    	return this.feedbackAdapter.toDto(this.lectureService.addFeedback(this.feedbackAdapter.toDomain(feedbackDto)));
    }
    
    @RequestMapping(value = "/active/question/add", method = RequestMethod.POST)
    public QuestionDto addQuestion(@RequestBody QuestionDto questionDto) {
    	return this.questionAdapter.toDto(this.lectureService.addQuestion(this.questionAdapter.toDomain(questionDto)));
    }
}
