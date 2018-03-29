package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestMoodSummaryDto.aValidMoodSummaryDto;

import org.junit.Before;
import org.junit.Test;

public class MoodSummaryDtoTest {
	private MoodSummaryDto moodSummaryDto;
	
	@Before
	public void before() {
		moodSummaryDto = aValidMoodSummaryDto().build();
	}

	@Test
	public void canRetrieveCorrectEmotionDtoOnceSet() {
		EmotionDto emotionDto = EmotionDto.HAPPY;
		moodSummaryDto.setEmotionDto(emotionDto);
		
		assertThat(moodSummaryDto.getEmotionDto(), is(emotionDto));
	}

    @Test
    public void canRetrieveCorrectSumOnceSet() {
    	Integer randomSumInteger = 10;
    	moodSummaryDto.setLectureDto(randomSumInteger);
    	
    	assertThat(moodSummaryDto.getSum(), is(randomSumInteger));
    }
}