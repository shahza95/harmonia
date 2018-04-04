package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestFeedback.aValidFeedback;

import org.junit.Test;

public class FeedbackTest {
	
	@Test
	public void canGetAndSetLecture() {
		Lecture lecture = TestLecture.aValidLecture().build();
		assertThat(aValidFeedback().lecture(lecture).build().getLecture(), is(lecture));
	}
	
	@Test
	public void canGetAndSerRating() {
		int randomRatingInteger = 2;
		assertThat(aValidFeedback().rating(randomRatingInteger).build().getRating(), is(randomRatingInteger));
	}
	
    @Test
    public void canGetAndSetMessage() {
        String randomMessageString = "another comment message";
        assertThat(aValidFeedback().message(randomMessageString).build().getMessage(), is(randomMessageString));
    }
}
