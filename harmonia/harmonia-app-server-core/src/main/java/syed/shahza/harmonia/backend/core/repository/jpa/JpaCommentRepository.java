package syed.shahza.harmonia.backend.core.repository.jpa;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.core.repository.CommentRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2CommentRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LectureRepository;

public class JpaCommentRepository implements CommentRepository {
	private final H2CommentRepository commentRepository;
	private final H2LectureRepository lectureRepository;
	private final CommentEntityAdapter commentEntityAdapter;
	
    public JpaCommentRepository(H2CommentRepository commentRepository, CommentEntityAdapter commentEntityAdapter, H2LectureRepository lectureRepository) {
        this.commentRepository = commentRepository;
        this.commentEntityAdapter = commentEntityAdapter;
        this.lectureRepository = lectureRepository;
    }

	@Override
	public Comments getAllComments(String lectureTitle) {
		return this.commentEntityAdapter.toDomain(this.commentRepository.findByLectureTitle(lectureTitle));
	}

	@Override
	public Comment addComment(Comment comment) {
		CommentEntity commentEntity = this.commentEntityAdapter.toEntity(comment);
		commentEntity.setLecture(this.lectureRepository.findByTitle(comment.getLecture().getTitle()));
		this.commentRepository.save(commentEntity);
		return comment;
	}
}
