package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.core.domain.Feedback.aFeedback;
import static syed.shahza.harmonia.backend.core.domain.Feedbacks.aFeedbackListBuilder;
import static syed.shahza.harmonia.backend.dto.FeedbackDto.aFeedbackDto;

import java.util.ArrayList;
import java.util.List;

import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.Feedbacks;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.FeedbackDtoList;


public class FeedbackAdapter {
	private final LectureAdapter lectureAdapter;
	
	public FeedbackAdapter(LectureAdapter lectureAdapter) {
		this.lectureAdapter = lectureAdapter;
	}

    public FeedbackDto toDto(Feedback feedback) {
        return aFeedbackDto().lectureDto(this.lectureAdapter.toDto(feedback.getLecture())).rating(feedback.getRating()).message(feedback.getMessage()).build();
    }
    
    public Feedback toDomain(FeedbackDto feedbackDto) {
    	return aFeedback().lecture(this.lectureAdapter.toDomain(feedbackDto.getLectureDto())).rating(feedbackDto.getRating()).message(feedbackDto.getMessage()).build();
    }
    
    public FeedbackDtoList toDto(Feedbacks feedbacks) {
    	FeedbackDtoList feedbackDtoList = new FeedbackDtoList();
    	for(Feedback feedback: feedbacks.getFeedbackList()) {
    		feedbackDtoList.addFeedbackDtoToList(toDto(feedback));
    	}
    	return feedbackDtoList;
    }
    
    public Feedbacks toDomain(FeedbackDtoList feedbackDtoList) {
    	List<Feedback> feedbackList = new ArrayList<Feedback>();
    	for(FeedbackDto feedbackDto: feedbackDtoList.getFeedbackDtoList()) {
    		feedbackList.add(toDomain(feedbackDto));
    	}
    	return aFeedbackListBuilder().feedbackList(feedbackList).build();
    }
}