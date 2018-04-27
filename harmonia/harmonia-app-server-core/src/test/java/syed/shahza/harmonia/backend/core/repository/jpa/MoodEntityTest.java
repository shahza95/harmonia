package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.core.domain.Emotion;


public class MoodEntityTest {
	private MoodEntity moodEntity;
    private LectureEntity lectureEntity;

    @Before
    public void before() {
    	moodEntity = TestMoodEntity.aMoodEntity();
        lectureEntity = TestLectureEntity.aLectureEntity();
    }

    @Test
    public void getAndSetEmoji() {
    	String emoji = ":D";
        moodEntity.setEmoji(emoji);

        assertThat(moodEntity.getEmoji(), is(emoji));
    }
    
    @Test
    public void getAndSetEmotion() {
    	Emotion emotion = Emotion.CONFUSED;
    	moodEntity.setEmotion(emotion);
    	
    	assertThat(moodEntity.getEmotion(), is(emotion));
    }

    @Test
    public void getAndSetLecture() {
        moodEntity.setLecture(lectureEntity);

        assertThat(moodEntity.getLecture(), is(lectureEntity));
    }
}
