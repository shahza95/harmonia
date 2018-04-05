package syed.shahza.harmonia.backend.endpoint.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestQuestion.aValidQuestion;
import static syed.shahza.harmonia.backend.dto.TestQuestionDto.aValidQuestionDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.dto.QuestionDto;

@RunWith(MockitoJUnitRunner.class)
public class QuestionAdapterTest {
    private QuestionAdapter questionAdapter;
    
    @Mock
    private LectureAdapter mockLectureAdapter;

    @Before
    public void before() {
        this.questionAdapter = new QuestionAdapter(this.mockLectureAdapter);
    }

    @Test
    public void canAdaptQuestionQuestionToDto() {
        assertThat(this.questionAdapter.toDto(aValidQuestion().question("some question").build()).getQuestion(), is("some question"));
    }

    @Test
    public void canAdaptQuestionQuestionToDomain() {
        assertThat(this.questionAdapter.toDomain(aValidQuestionDto().question("some question").build()).getQuestion(), is("some question"));
    }
    
    @Test
    public void canAdaptQuestionAnswerToDto() {
    	assertThat(this.questionAdapter.toDto(aValidQuestion().answer("some answer").build()).getAnswer(), is("some answer"));
    }
    
    @Test
    public void canAdaptQuestionAnswerToDomain() {
    	assertThat(this.questionAdapter.toDomain(aValidQuestionDto().answer("some answer").build()).getAnswer(), is("some answer"));
    }
    
    @Test
    public void toDtoInvokesLectureAdapterToDto() {
    	Question question = aValidQuestion().build();
    	this.questionAdapter.toDto(question);
    	Mockito.verify(this.mockLectureAdapter).toDto(question.getLecture());
    }
    
    @Test
    public void toDomainInvokesLectureAdapterToDomain() {
    	QuestionDto questionDto = aValidQuestionDto().build();
    	this.questionAdapter.toDomain(questionDto);
    	Mockito.verify(this.mockLectureAdapter).toDomain(questionDto.getLectureDto());
    }
}