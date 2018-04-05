package syed.shahza.harmonia.backend.endpoint.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestQuestions.aFilledQuestionsList;
import static syed.shahza.harmonia.backend.core.domain.TestQuestion.aValidQuestion;
import static syed.shahza.harmonia.backend.dto.TestQuestionDtoList.aFilledQuestionDtoList;
import static syed.shahza.harmonia.backend.dto.TestQuestionDto.aValidQuestionDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Questions;
import syed.shahza.harmonia.backend.core.domain.Question;
import syed.shahza.harmonia.backend.dto.QuestionDtoList;
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
    
    @Test
    public void canAdaptQuestionsToQuestionDtoList() {
    	Questions questions = aFilledQuestionsList(2);
    	QuestionDtoList questionDtoList = this.questionAdapter.toDto(questions);
        assertThat(questionDtoList.getQuestionDtoList().size(), is(questions.getQuestionList().size()));
    }
    
    @Test
    public void canAdaptQuestionDtoListToQuestions() {
    	QuestionDtoList questionDtoList = aFilledQuestionDtoList(3);
    	Questions questions = this.questionAdapter.toDomain(questionDtoList);
    	assertThat(questions.getQuestionList().size(), is(questionDtoList.getQuestionDtoList().size()));
    }
}