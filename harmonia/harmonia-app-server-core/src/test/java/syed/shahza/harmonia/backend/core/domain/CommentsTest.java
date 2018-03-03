package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;


public class CommentsTest {

    @Test
    public void shouldBeAbleToCreateCommentsAndGetCommentListBack() {
    	List<Comment> listComment = TestComments.aFilledCommentsList(5).getCommentList();
    	Comments comments = Comments.aCommentListBuilder().commentList(listComment).build();

        assertThat(comments.getCommentList(), is(listComment));
    }
}
