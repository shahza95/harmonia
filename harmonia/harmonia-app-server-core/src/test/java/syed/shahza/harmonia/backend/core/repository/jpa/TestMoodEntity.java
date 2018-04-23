package syed.shahza.harmonia.backend.core.repository.jpa;

import org.apache.commons.lang3.RandomStringUtils;

import syed.shahza.harmonia.backend.core.domain.Emotion;

public class TestMoodEntity {
    public static MoodEntity aMoodEntity() {
        MoodEntity moodEntity = new MoodEntity();
        moodEntity.setEmoji(RandomStringUtils.random(10));
        moodEntity.setEmotion(Emotion.CONFUSED);
        moodEntity.setLecture(TestLectureEntity.aLectureEntity());
        return moodEntity;
    }
}
