package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.TestComment;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2CommentRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;

@RunWith(MockitoJUnitRunner.class)
public class JpaCommentRepositoryTest {
	private JpaCommentRepository repository;
	private Comment comment;
	
	@Mock
	private H2CommentRepository mockH2CommentRepository;
	
	@Mock
	private H2LectureRepository mockH2LectureRepository;
	
	@Mock
	private CommentEntityAdapter mockCommentEntityAdapter;
	
	@Before
	public void setUp() {
		comment = TestComment.aValidComment().build();
		repository = new JpaCommentRepository(this.mockH2CommentRepository, this.mockCommentEntityAdapter, this.mockH2LectureRepository);
	}
	
	@Test
	public void addCommentShouldInvokeEntityAdapter() {
		this.repository.addComment(comment);
		
		verify(this.mockCommentEntityAdapter).toEntity(comment);
	}
	
	@Test
	public void addCommentShouldInvokeLecturerRepository() {
		CommentEntity commentEntity = TestCommentEntity.aCommentEntity();
		when(this.mockCommentEntityAdapter.toEntity(comment)).thenReturn(commentEntity);
		this.repository.addComment(comment);
		
		verify(this.mockH2CommentRepository).save(commentEntity);
	}
	
	@Test
	public void getAllCommentsShouldInvokeLecturerRepository() {
		this.repository.getAllComments("lectureTitle");
		
		verify(this.mockH2CommentRepository).findByLectureTitle("lectureTitle");
	}
	
	@Test
	public void getAllCommentsShouldInvokeEntityAdapter() {
		String title = "lectureTitle";
		List<CommentEntity> commentEntityList = new ArrayList<CommentEntity>();
		when(this.mockH2CommentRepository.findByLectureTitle(title)).thenReturn(commentEntityList);
		this.repository.getAllComments(title);
		
		verify(this.mockCommentEntityAdapter).toDomain(commentEntityList);
	}
}
