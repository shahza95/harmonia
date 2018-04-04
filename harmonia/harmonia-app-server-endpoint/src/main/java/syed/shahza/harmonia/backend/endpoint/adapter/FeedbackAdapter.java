package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.core.domain.Feedback.aFeedback;
import static syed.shahza.harmonia.backend.dto.FeedbackDto.aFeedbackDto;
import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.dto.FeedbackDto;


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
}