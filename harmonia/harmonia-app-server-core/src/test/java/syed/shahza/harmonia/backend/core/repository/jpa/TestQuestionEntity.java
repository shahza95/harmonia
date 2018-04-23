package syed.shahza.harmonia.backend.core.repository.jpa;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class TestQuestionEntity {
    public static QuestionEntity aQuestionEntity() {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setUuid(UUID.randomUUID().toString());
        questionEntity.setQuestion(RandomStringUtils.random(10));
        questionEntity.setAnswer(RandomStringUtils.random(10));
        questionEntity.setLecture(TestLectureEntity.aLectureEntity());
        return questionEntity;
    }
}
