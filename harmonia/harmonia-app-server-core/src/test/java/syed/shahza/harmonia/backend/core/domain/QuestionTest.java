package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestQuestion.aValidQuestion;

import org.junit.Test;

public class QuestionTest {
    @Test
    public void canGetAndSetQuestion() {
        String randomQuestionString = "another question";
        assertThat(aValidQuestion().question(randomQuestionString).build().getQuestion(), is(randomQuestionString));
    }
    
    @Test
    public void canGetAndSetAnswer() {
    	String randomAnswerString = "another answer";
    	assertThat(aValidQuestion().answer(randomAnswerString).build().getAnswer(), is(randomAnswerString));
    }
    
    @Test
    public void canGetAndSetLecture() {
    	Lecture lecture = TestLecture.aValidLecture().build();
    	assertThat(aValidQuestion().lecture(lecture).build().getLecture(), is(lecture));
    }
}
