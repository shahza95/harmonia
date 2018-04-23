package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class FeedbackEntityTest {
	private FeedbackEntity feedbackEntity;
    private LectureEntity lectureEntity;

    @Before
    public void before() {
    	feedbackEntity = TestFeedbackEntity.aFeedbackEntity();
        lectureEntity = TestLectureEntity.aLectureEntity();
    }

    @Test
    public void getAndSetRating() {
    	int rating = 4;
        feedbackEntity.setRating(rating);

        assertThat(feedbackEntity.getRating(), is(rating));
    }
    
    @Test
    public void getAndSetMessage() {
    	String message = "some feedback message";
    	feedbackEntity.setMessage(message);
    	
    	assertThat(feedbackEntity.getMessage(), is(message));
    }

    @Test
    public void getAndSetLecture() {
        feedbackEntity.setLecture(lectureEntity);

        assertThat(feedbackEntity.getLecture(), is(lectureEntity));
    }
}