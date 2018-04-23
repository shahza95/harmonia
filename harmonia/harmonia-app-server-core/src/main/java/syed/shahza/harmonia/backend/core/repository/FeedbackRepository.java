package syed.shahza.harmonia.backend.core.repository;

import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.Feedbacks;

public interface FeedbackRepository {
	
	Feedback addFeedback(Feedback feedback);
	
	Feedbacks getAllFeedback(String lectureTitle);
}
