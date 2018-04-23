package syed.shahza.harmonia.backend.core.repository.jpa;

import org.apache.commons.lang3.RandomStringUtils;

public class TestFeedbackEntity {
    public static FeedbackEntity aFeedbackEntity() {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setMessage(RandomStringUtils.random(10));
        feedbackEntity.setRating(2);
        feedbackEntity.setLecture(TestLectureEntity.aLectureEntity());
        return feedbackEntity;
    }
}
