package syed.shahza.harmonia.backend.endpoint.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestComment.aValidComment;
import static syed.shahza.harmonia.backend.dto.TestCommentDto.aValidCommentDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.dto.CommentDto;

@RunWith(MockitoJUnitRunner.class)
public class CommentAdapterTest {
    private CommentAdapter commentAdapter;
    
    @Mock
    private LectureAdapter mockLectureAdapter;

    @Before
    public void before() {
        this.commentAdapter = new CommentAdapter(this.mockLectureAdapter);
//        when(this.mockLectureAdapter.toDto(any())).thenReturn(TestLectureDto.aValidLectureDto().build());
//        when(this.mockLectureAdapter.toDomain(any())).thenReturn(TestLecture.aValidLecture().build());
    }

    @Test
    public void canAdaptMessageToDto() {
    	String message = "another kind of message";
        assertThat(this.commentAdapter.toDto(aValidComment().message(message).build()).getMessage(), is(message));
    }

    @Test
    public void canAdaptMessageToDomain() {
    	String message = "another kind of message";
        assertThat(this.commentAdapter.toDomain(aValidCommentDto().message(message).build()).getMessage(), is(message));
    }
    
    @Test
    public void toDtoInvokesLectureAdapterToDto() {
    	Comment comment = aValidComment().build();
    	this.commentAdapter.toDto(comment);
    	Mockito.verify(this.mockLectureAdapter).toDto(comment.getLecture());
    }
    
    @Test
    public void toDomainInvokesLectureAdapterToDomain() {
    	CommentDto commentDto = aValidCommentDto().build();
    	this.commentAdapter.toDomain(commentDto);
    	Mockito.verify(this.mockLectureAdapter).toDomain(commentDto.getLectureDto());
    }
}