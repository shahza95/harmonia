package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;


public class CommentEntityTest {
	private CommentEntity commentEntity;
    private LectureEntity lectureEntity;
    private String randomString;

    @Before
    public void before() {
    	commentEntity = TestCommentEntity.aCommentEntity();
        lectureEntity = TestLectureEntity.aLectureEntity();
        randomString = RandomStringUtils.random(10);
    }

    @Test
    public void getAndSetMessage() {
        commentEntity.setMessage(randomString);

        assertThat(commentEntity.getMessage(), is(randomString));
    }

    @Test
    public void getAndSetLecture() {
        commentEntity.setLecture(lectureEntity);

        assertThat(commentEntity.getLecture(), is(lectureEntity));
    }
}
