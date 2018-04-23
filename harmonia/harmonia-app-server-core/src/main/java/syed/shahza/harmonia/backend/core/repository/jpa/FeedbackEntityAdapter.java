package syed.shahza.harmonia.backend.core.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.Feedbacks;

public class FeedbackEntityAdapter {
	private final LectureEntityAdapter lectureAdapter;

	public FeedbackEntityAdapter(LectureEntityAdapter lectureEntityAdapter) {
		this.lectureAdapter = lectureEntityAdapter;
	};

	public FeedbackEntity toEntity(Feedback feedback) {
		FeedbackEntity feedbackEntity = new FeedbackEntity();
		feedbackEntity.setRating(feedback.getRating());
		feedbackEntity.setMessage(feedback.getMessage());
		feedbackEntity.setLecture(this.lectureAdapter.toEntity(feedback.getLecture()));
		return feedbackEntity;
	}

	public Feedback toDomain(FeedbackEntity feedbackEntity) {
		Feedback.Builder feedbackBuilder = Feedback.aFeedback();

		feedbackBuilder.rating(feedbackEntity.getRating())
				.message(feedbackEntity.getMessage())
				.lecture(this.lectureAdapter.toDomain(feedbackEntity.getLecture()));

		return feedbackBuilder.build();
	}

	public Feedbacks toDomain(List<FeedbackEntity> feedbackEntities) {
		List<Feedback> feedbackList = new ArrayList<Feedback>();
		for (FeedbackEntity feedbackEntity : feedbackEntities) {
			feedbackList.add(toDomain(feedbackEntity));
		}
		return Feedbacks.aFeedbackListBuilder().feedbackList(feedbackList).build();
	}
}