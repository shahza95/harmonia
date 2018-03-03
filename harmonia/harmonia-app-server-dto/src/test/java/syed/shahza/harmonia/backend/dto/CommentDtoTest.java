package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestCommentDto.aValidCommentDto;

import org.junit.Test;

public class CommentDtoTest {
    @Test
    public void canRetrieveCorrectMessageOnceSet() {
    	CommentDto commentDto = aValidCommentDto().build();
        String randomMessageString = "another message";
        commentDto.setMessage(randomMessageString);

        assertThat(commentDto.getMessage(), is(randomMessageString));
    }
}