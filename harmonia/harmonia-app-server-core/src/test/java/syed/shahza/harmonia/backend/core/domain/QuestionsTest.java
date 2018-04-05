package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;


public class QuestionsTest {

    @Test
    public void shouldBeAbleToCreateQuestionsAndGetQuestionListBack() {
    	List<Question> listQuestion = TestQuestions.aFilledQuestionsList(5).getQuestionList();
    	Questions questions = Questions.aQuestionListBuilder().questionList(listQuestion).build();

        assertThat(questions.getQuestionList(), is(listQuestion));
    }
}
