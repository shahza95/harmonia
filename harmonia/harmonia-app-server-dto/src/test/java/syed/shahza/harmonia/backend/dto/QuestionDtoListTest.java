package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class QuestionDtoListTest {
	private QuestionDtoList questionDtoList;
	private QuestionDto questionDto;
	
	@Before
	public void before() {
		questionDto = TestQuestionDto.aValidQuestionDto().build();
        questionDtoList = TestQuestionDtoList.aFilledQuestionDtoList(5);
	}
	
    @Test
    public void shouldBeAbleToCreateAQuestionDtoListAndGetIt() {
        List<QuestionDto> listQuestionDto = questionDtoList.getQuestionDtoList();
        
        assertThat(new QuestionDtoList(listQuestionDto).getQuestionDtoList(), is(listQuestionDto));
    }
    
    @Test
    public void shouldBeAbleToAddAQuestionDtoToEmptyList() {
    	QuestionDtoList questionDtoList = TestQuestionDtoList.anEmptyQuestionDtoList();
    	questionDtoList.addQuestionDtoToList(questionDto);
    	
    	assertThat(questionDtoList.getQuestionDtoList().get(0), is(questionDto));
    }
    
    @Test
    public void shouldBeAbleToAddAQuestionDtoToExistingList() {
        questionDtoList.addQuestionDtoToList(questionDto);

        assertThat(questionDtoList.getQuestionDtoList().get(5), is(questionDto));
    }
}
