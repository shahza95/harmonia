package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestMoodDto.aValidMoodDto;

import org.junit.Before;
import org.junit.Test;

public class MoodDtoTest {
	private MoodDto moodDto;
	
	@Before
	public void before() {
		moodDto = aValidMoodDto().build();
	}

	@Test
    public void canRetrieveCorrectEmojiOnceSet() {
        String randomEmojiString = "&#x1F642;";
        moodDto.setEmoji(randomEmojiString);

        assertThat(moodDto.getEmoji(), is(randomEmojiString));
    }
	
	@Test
	public void canRetrieveCorrectEmotionDtoOnceSet() {
		EmotionDto emotionDto = EmotionDto.CONFUSED;
		moodDto.setEmotionDto(emotionDto);
		
		assertThat(moodDto.getEmotionDto(), is(emotionDto));
	}

    @Test
    public void canRetrieveCorrectLectureDtoOnceSet() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	moodDto.setLectureDto(lectureDto);
    	
    	assertThat(moodDto.getLectureDto(), is(lectureDto));
    }
}