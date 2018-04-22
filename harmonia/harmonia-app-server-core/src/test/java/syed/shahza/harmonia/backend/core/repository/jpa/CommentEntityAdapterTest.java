package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.TestComment;

@RunWith(MockitoJUnitRunner.class)
public class CommentEntityAdapterTest {
    private CommentEntityAdapter adapter;
    private Comment.Builder commentBuilder;

    @Mock
    private LectureEntityAdapter mockLectureEntityAdapter;
    

    @Before
    public void Before() {
        this.adapter = new CommentEntityAdapter(this.mockLectureEntityAdapter);
        this.commentBuilder = TestComment.aValidComment();
    }

    @Test
    public void adaptsMessageFromDomainToEntityCorrectlyAndBackAgain() {
    	String message = "some comment";
        CommentEntity commentEntity = adapter.toEntity(commentBuilder.message(message).build());

        assertThat(adapter.toDomain(commentEntity).getMessage(), is(message));
    }

    @Test
    public void invokesLectureAdapterFromDomainToEntity() {
        this.adapter.toEntity(commentBuilder.build());
        verify(this.mockLectureEntityAdapter).toEntity(commentBuilder.build().getLecture());
    }
    
    @Test
    public void invokesLectureAdapterFromEntityToDomain() {
    	CommentEntity commentEntity = TestCommentEntity.aCommentEntity();
    	this.adapter.toDomain(commentEntity);
    	verify(this.mockLectureEntityAdapter).toDomain(commentEntity.getLecture());
    }
    
    @Test
    public void canAdaptListOfCommentEntitiesToCommentsObject(){
    	CommentEntity commentEntity = TestCommentEntity.aCommentEntity();
    	CommentEntity commentEntity2 = TestCommentEntity.aCommentEntity();
    	List<CommentEntity> commentEntityList = new ArrayList<CommentEntity>();
    	commentEntityList.add(commentEntity);
    	commentEntityList.add(commentEntity2);
    	assertThat(this.adapter.toDomain(commentEntityList).getCommentList().size(), is(2));
    }
}
