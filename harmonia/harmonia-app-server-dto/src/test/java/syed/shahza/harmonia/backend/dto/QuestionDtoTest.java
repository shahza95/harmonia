package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestQuestionDto.aValidQuestionDto;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class QuestionDtoTest {
	private QuestionDto questionDto;
	
	@Before
	public void before() {
		questionDto = aValidQuestionDto().build();
	}
	
	@Test
	public void canRetrieveCorrectIdOnceSet() {
		String randomIdString = UUID.randomUUID().toString();
		questionDto.setId(randomIdString);
		
		assertThat(questionDto.getId(), is(randomIdString));
	}
	
	@Test
	public void automaticallyGeneratesId() {
		QuestionDto questionDto = new QuestionDto();
		
		assertThat(questionDto.getId(), instanceOf(String.class));
	}
	
	@Test
	public void automaticallyGeneratesIdViaBuilder() {
		QuestionDto questionDto = QuestionDto.aQuestionDto().build();
		
		assertThat(questionDto.getId(), instanceOf(String.class));
	}

	@Test
    public void canRetrieveCorrectQuestionOnceSet() {
        String randomQuestionString = "another question";
        questionDto.setQuestion(randomQuestionString);

        assertThat(questionDto.getQuestion(), is(randomQuestionString));
    }
	
	@Test
	public void canRetrieveCorrectAnswerOnceSet() {
		String randomAnswerString = "another answer";
		questionDto.setAnswer(randomAnswerString);
		
		assertThat(questionDto.getAnswer(), is(randomAnswerString));
	}

    @Test
    public void canRetrieveCorrectLectureDtoOnceSet() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	questionDto.setLectureDto(lectureDto);
    	
    	assertThat(questionDto.getLectureDto(), is(lectureDto));
    }
}