package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.core.domain.TestQuestion;

@RunWith(MockitoJUnitRunner.class)
public class QuestionEntityAdapterTest {
    private QuestionEntityAdapter adapter;
    private Question.Builder questionBuilder;

    @Mock
    private LectureEntityAdapter mockLectureEntityAdapter;
    

    @Before
    public void Before() {
        this.adapter = new QuestionEntityAdapter(this.mockLectureEntityAdapter);
        this.questionBuilder = TestQuestion.aValidQuestion();
    }

    @Test
    public void adaptsUuidFromDomainToEntityCorrectlyAndBackAgain() {
    	String uuid = UUID.randomUUID().toString();
        QuestionEntity questionEntity = adapter.toEntity(questionBuilder.id(uuid).build());

        assertThat(adapter.toDomain(questionEntity).getId(), is(uuid));
    }
    
    @Test
    public void adaptsQuestionFromDomainToEntityCorrectlyAndBackAgain() {
    	String question = "some question";
    	QuestionEntity questionEntity = adapter.toEntity(questionBuilder.question(question).build());
    	
    	assertThat(adapter.toDomain(questionEntity).getQuestion(), is(question));
    }
    
    @Test
    public void adaptsAnswerFromDomainToEntityCorrectlyAndBackAgain() {
    	String answer = "some answer";
    	QuestionEntity questionEntity = adapter.toEntity(questionBuilder.answer(answer).build());
    	
    	assertThat(adapter.toDomain(questionEntity).getAnswer(), is(answer));
    }

    @Test
    public void invokesLectureAdapterFromDomainToEntity() {
        this.adapter.toEntity(questionBuilder.build());
        verify(this.mockLectureEntityAdapter).toEntity(questionBuilder.build().getLecture());
    }
    
    @Test
    public void invokesLectureAdapterFromEntityToDomain() {
    	QuestionEntity questionEntity = TestQuestionEntity.aQuestionEntity();
    	this.adapter.toDomain(questionEntity);
    	verify(this.mockLectureEntityAdapter).toDomain(questionEntity.getLecture());
    }
    
    @Test
    public void canAdaptListOfQuestionEntitiesToQuestionsObject(){
    	QuestionEntity questionEntity = TestQuestionEntity.aQuestionEntity();
    	QuestionEntity questionEntity2 = TestQuestionEntity.aQuestionEntity();
    	List<QuestionEntity> questionEntityList = new ArrayList<QuestionEntity>();
    	questionEntityList.add(questionEntity);
    	questionEntityList.add(questionEntity2);
    	assertThat(this.adapter.toDomain(questionEntityList).getQuestionList().size(), is(2));
    }
}
