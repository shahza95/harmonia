package syed.shahza.harmonia.backend.core.repository.jpa;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class TestLectureEntity {
    public static LectureEntity aLectureEntity() {
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setTitle(RandomStringUtils.random(10));
        lectureEntity.setPassword(RandomStringUtils.random(10));
        lectureEntity.setDate(new LocalDate());
        lectureEntity.setStartTime(new LocalTime());
        lectureEntity.setEndTime(new LocalTime().plusHours(1));
        lectureEntity.setEnded(false);
        lectureEntity.setCommentsEnabled(true);
        lectureEntity.setMoodEnabled(true);
        lectureEntity.setFeedbackEnabled(true);
        lectureEntity.setQuestionsEnabled(true);
        return lectureEntity;
    }
}
