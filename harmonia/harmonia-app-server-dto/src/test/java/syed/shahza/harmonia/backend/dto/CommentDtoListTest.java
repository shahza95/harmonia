package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CommentDtoListTest {
	private CommentDtoList commentDtoList;
	private CommentDto commentDto;
	
	@Before
	public void before() {
		commentDto = TestCommentDto.aValidCommentDto().build();
        commentDtoList = TestCommentDtoList.aFilledCommentDtoList(5);
	}
	
    @Test
    public void shouldBeAbleToCreateACommentDtoListAndGetIt() {
        List<CommentDto> listCommentDto = commentDtoList.getCommentDtoList();
        
        assertThat(new CommentDtoList(listCommentDto).getCommentDtoList(), is(listCommentDto));
    }
    
    @Test
    public void shouldBeAbleToAddACommentDtoToEmptyList() {
    	CommentDtoList commentDtoList = TestCommentDtoList.anEmptyCommentDtoList();
    	commentDtoList.addCommentDtoToList(commentDto);
    	
    	assertThat(commentDtoList.getCommentDtoList().get(0), is(commentDto));
    }
    
    @Test
    public void shouldBeAbleToAddACommentDtoToExistingList() {
        commentDtoList.addCommentDtoToList(commentDto);

        assertThat(commentDtoList.getCommentDtoList().get(5), is(commentDto));
    }
}
