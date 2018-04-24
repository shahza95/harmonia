package syed.shahza.harmonia.backend.core.repository.jpa;

import syed.shahza.harmonia.backend.core.domain.Feedback;
import syed.shahza.harmonia.backend.core.domain.Feedbacks;
import syed.shahza.harmonia.backend.core.repository.FeedbackRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2FeedbackRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;

//data access layer, Feedback related requests, for any database implementation (currently set to the only option: H2)
public class JpaFeedbackRepository implements FeedbackRepository {
	private final H2FeedbackRepository feedbackRepository;
	private final H2LectureRepository lectureRepository;
	private final FeedbackEntityAdapter feedbackEntityAdapter;
	
    public JpaFeedbackRepository(H2FeedbackRepository feedbackRepository, FeedbackEntityAdapter feedbackEntityAdapter, H2LectureRepository lectureRepository) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackEntityAdapter = feedbackEntityAdapter;
        this.lectureRepository = lectureRepository;
    }

	@Override
	public Feedbacks getAllFeedback(String lectureTitle) {
		return this.feedbackEntityAdapter.toDomain(this.feedbackRepository.findByLectureTitle(lectureTitle));
	}

	@Override
	public Feedback addFeedback(Feedback feedback) {
		FeedbackEntity feedbackEntity = this.feedbackEntityAdapter.toEntity(feedback);
		feedbackEntity.setLecture(this.lectureRepository.findByTitle(feedback.getLecture().getTitle()));
		this.feedbackRepository.save(feedbackEntity);
		return feedback;
	}
}
