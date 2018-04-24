package syed.shahza.harmonia.backend.core.repository.jpa;

import syed.shahza.harmonia.backend.core.domain.Lecture;

//Lecture object: DAO / ENTITY adapter
public class LectureEntityAdapter {

    public LectureEntity toEntity(Lecture lecture) {
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setTitle(lecture.getTitle());
        lectureEntity.setPassword(lecture.getPassword());
        lectureEntity.setDate(lecture.getDate());
        lectureEntity.setStartTime(lecture.getStartTime());
        lectureEntity.setEndTime(lecture.getEndTime());
        lectureEntity.setEnded(lecture.getEnded());
        lectureEntity.setCommentsEnabled(lecture.getCommentsEnabled());
        lectureEntity.setMoodEnabled(lecture.getMoodEnabled());
        lectureEntity.setFeedbackEnabled(lecture.getFeedbackEnabled());
        lectureEntity.setQuestionsEnabled(lecture.getQuestionsEnabled());

        return lectureEntity;
    }

    public Lecture toDomain(LectureEntity lectureEntity) {
        Lecture.Builder lectureBuilder = Lecture.aLecture();

        lectureBuilder.title(lectureEntity.getTitle())
        		.password(lectureEntity.getPassword())
        		.date(lectureEntity.getDate())
                .startTime(lectureEntity.getStartTime())
                .endTime(lectureEntity.getEndTime())
                .ended(lectureEntity.getEnded())
                .commentsEnabled(lectureEntity.getCommentsEnabled())
                .moodEnabled(lectureEntity.getMoodEnabled())
                .feedbackEnabled(lectureEntity.getFeedbackEnabled())
                .questionsEnabled(lectureEntity.getQuestionsEnabled());

        return lectureBuilder.build();
    }

}