package syed.shahza.harmonia.backend.endpoint.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestComment.aValidComment;
import static syed.shahza.harmonia.backend.core.domain.TestComments.aFilledCommentsList;
import static syed.shahza.harmonia.backend.dto.TestCommentDto.aValidCommentDto;
import static syed.shahza.harmonia.backend.dto.TestCommentDtoList.aFilledCommentDtoList;

import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.dto.CommentDtoList;

public class CommentAdapterTest {
    private CommentAdapter commentAdapter;

    @Before
    public void before() {
        this.commentAdapter = new CommentAdapter();
    }

    @Test
    public void canAdaptCommentMessageToDto() {
        assertThat(this.commentAdapter.toDto(aValidComment().message("someMessage").build()).getMessage(), is("someMessage"));
    }

    @Test
    public void canAdaptCommentMessageToDomain() {
        assertThat(this.commentAdapter.toDomain(aValidCommentDto().message("someMessage").build()).getMessage(), is("someMessage"));
    }

    @Test
    public void canAdaptCommentsToCommentDtoList() {
    	Comments comments = aFilledCommentsList(2);
    	CommentDtoList commentDtoList = this.commentAdapter.toDto(comments);
        assertThat(commentDtoList.getCommentDtoList().size(), is(comments.getCommentList().size()));
    }
    
    @Test
    public void canAdaptCommentDtoListToComments() {
    	CommentDtoList commentDtoList = aFilledCommentDtoList(3);
    	Comments comments = this.commentAdapter.toDomain(commentDtoList);
    	assertThat(comments.getCommentList().size(), is(commentDtoList.getCommentDtoList().size()));
    }
}