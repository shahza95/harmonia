package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestCommentDto.aValidCommentDto;

import org.junit.Before;
import org.junit.Test;

public class CommentDtoTest {
	private CommentDto commentDto;
	
	@Before
	public void before() {
		commentDto = aValidCommentDto().build();
	}

	@Test
    public void canRetrieveCorrectMessageOnceSet() {
        String randomMessageString = "another message";
        commentDto.setMessage(randomMessageString);

        assertThat(commentDto.getMessage(), is(randomMessageString));
    }

    @Test
    public void canRetrieveCorrectLectureDtoOnceSet() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	commentDto.setLectureDto(lectureDto);
    	
    	assertThat(commentDto.getLectureDto(), is(lectureDto));
    }
}