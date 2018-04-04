package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;


public class FeedbacksTest {

    @Test
    public void shouldBeAbleToCreateFeedbacksAndGetFeedbackListBack() {
    	List<Feedback> listFeedback = TestFeedbacks.aFilledFeedbacksList(5).getFeedbackList();
    	Feedbacks feedbacks = Feedbacks.aFeedbackListBuilder().feedbackList(listFeedback).build();

        assertThat(feedbacks.getFeedbackList(), is(listFeedback));
    }
}
