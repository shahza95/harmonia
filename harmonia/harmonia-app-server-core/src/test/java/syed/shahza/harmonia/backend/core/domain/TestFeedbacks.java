package syed.shahza.harmonia.backend.core.domain;

import java.util.ArrayList;
import java.util.List;

public class TestFeedbacks {
	public static Feedbacks aFilledFeedbacksList(int numberOfFeedbacks) {
		List<Feedback> feedbacks = new ArrayList<>();
		for(int i=0; i<numberOfFeedbacks; i++) {
			feedbacks.add(TestFeedback.aValidFeedback().build());
		}
		return Feedbacks.aFeedbackListBuilder().feedbackList(feedbacks).build();
	}
	
	public static Feedbacks anEmptyFeedbacksList() {
		return Feedbacks.aFeedbackListBuilder().feedbackList(null).build();
	}
}
