package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestMood.aValidMood;

import org.junit.Test;

public class MoodTest {
    @Test
    public void canGetAndSetEmoji() {
        String randomEmojiString = "&#x1F642;";
        assertThat(aValidMood().emoji(randomEmojiString).build().getEmoji(), is(randomEmojiString));
    }
    @Test
    public void canGetAndSetEmotion() {
    	Emotion randomEmotion = Emotion.HAPPY;
    	assertThat(aValidMood().emotion(randomEmotion).build().getEmotion(), is(randomEmotion));
    }
    
    @Test
    public void canGetAndSetLecture() {
    	Lecture lecture = TestLecture.aValidLecture().build();
    	assertThat(aValidMood().lecture(lecture).build().getLecture(), is(lecture));
    }
}
