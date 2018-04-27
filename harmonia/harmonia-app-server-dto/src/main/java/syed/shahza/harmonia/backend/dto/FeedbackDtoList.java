package syed.shahza.harmonia.backend.dto;

import java.util.ArrayList;
import java.util.List;

//Custom high level object of multiple FeedbackDtos
public class FeedbackDtoList {
    private List<FeedbackDto> feedbackDtoList = new ArrayList<>();
    
    public FeedbackDtoList() {
    	super();
    }
    
    public FeedbackDtoList(List<FeedbackDto> feedbackDtoList) {
    	this.feedbackDtoList = feedbackDtoList;
    }

    public List<FeedbackDto> getFeedbackDtoList() {
        return new ArrayList<>(feedbackDtoList);
    }

    public void addFeedbackDtoToList(FeedbackDto feedbackDto) {
        this.feedbackDtoList.add(feedbackDto);
    }
}
