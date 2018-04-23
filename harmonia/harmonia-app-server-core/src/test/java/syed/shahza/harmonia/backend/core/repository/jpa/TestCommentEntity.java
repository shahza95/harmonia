package syed.shahza.harmonia.backend.core.repository.jpa;

import org.apache.commons.lang3.RandomStringUtils;

public class TestCommentEntity {
    public static CommentEntity aCommentEntity() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMessage(RandomStringUtils.random(10));
        commentEntity.setLecture(TestLectureEntity.aLectureEntity());
        return commentEntity;
    }
}
