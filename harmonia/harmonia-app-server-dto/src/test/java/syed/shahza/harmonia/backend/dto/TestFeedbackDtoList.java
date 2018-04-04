package syed.shahza.harmonia.backend.dto;

public class TestFeedbackDtoList {
	
	public static FeedbackDtoList aFilledFeedbackDtoList(int numberOfFeedbacks) {
		FeedbackDtoList feedbackDtoList = new FeedbackDtoList();
		for(int i=0; i<numberOfFeedbacks; i++) {
			feedbackDtoList.addFeedbackDtoToList(TestFeedbackDto.aValidFeedbackDto().build());
		}
		return feedbackDtoList;
	}
	
	public static FeedbackDtoList anEmptyFeedbackDtoList() {
		return new FeedbackDtoList();
	}
}
