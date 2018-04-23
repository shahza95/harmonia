package syed.shahza.harmonia.backend.core.repository.jpa;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;


public class QuestionEntityTest {
	private QuestionEntity questionEntity;
    private LectureEntity lectureEntity;

    @Before
    public void before() {
    	questionEntity = TestQuestionEntity.aQuestionEntity();
        lectureEntity = TestLectureEntity.aLectureEntity();
    }

    @Test
    public void getAndSetUuid() {
    	String uuid = UUID.randomUUID().toString();
        questionEntity.setUuid(uuid);

        assertThat(questionEntity.getUuid(), is(uuid));
    }
    
    @Test
    public void getAndSetQuestion() {
    	String question = "question";
    	questionEntity.setQuestion(question);
    	
    	assertThat(questionEntity.getQuestion(), is(question));
    }
    
    @Test
    public void getAndSetAnswer() {
    	String answer = "answer";
    	questionEntity.setAnswer(answer);
    	
    	assertThat(questionEntity.getAnswer(), is(answer));
    }

    @Test
    public void getAndSetLecture() {
        questionEntity.setLecture(lectureEntity);

        assertThat(questionEntity.getLecture(), is(lectureEntity));
    }
}
