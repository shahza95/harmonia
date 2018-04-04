package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestFeedbackDto.aValidFeedbackDto;

import org.junit.Before;
import org.junit.Test;

public class FeedbackDtoTest {
	private FeedbackDto feedbackDto;
	
	@Before
	public void before() {
		feedbackDto = aValidFeedbackDto().build();
	}
	
	@Test
	public void canRetrieveCorrectLectureDtoOnceSet() {
		LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
		feedbackDto.setLectureDto(lectureDto);
		
		assertThat(feedbackDto.getLectureDto(), is(lectureDto));
	}
	
	@Test
	public void canRetrieveCorrectRatingOnceSet() {
		int randomRatingInteger = 1;
		feedbackDto.setRating(randomRatingInteger);
		
		assertThat(feedbackDto.getRating(), is(randomRatingInteger));
	}

	@Test
    public void canRetrieveCorrectMessageOnceSet() {
        String randomMessageString = "another message";
        feedbackDto.setMessage(randomMessageString);

        assertThat(feedbackDto.getMessage(), is(randomMessageString));
    }
}