package syed.shahza.harmonia.backend.core.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;

public class CommentEntityAdapter {
	private final LectureEntityAdapter lectureAdapter;
	
	public CommentEntityAdapter(LectureEntityAdapter lectureEntityAdapter) {
		this.lectureAdapter = lectureEntityAdapter;
	};

    public CommentEntity toEntity(Comment comment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMessage(comment.getMessage());
        commentEntity.setLecture(this.lectureAdapter.toEntity(comment.getLecture()));
        return commentEntity;
    }

    public Comment toDomain(CommentEntity commentEntity) {
        Comment.Builder commentBuilder = Comment.aComment();

        commentBuilder.message(commentEntity.getMessage())
        		.lecture(this.lectureAdapter.toDomain(commentEntity.getLecture()));

        return commentBuilder.build();
    }
    
    public Comments toDomain(List<CommentEntity> commentEntities) {
//    	if(!commentEntities.isEmpty()) {
	    	List<Comment> commentList = new ArrayList<Comment>();
	    	for(CommentEntity commentEntity : commentEntities) {
	    		commentList.add(toDomain(commentEntity));
	    	}
	    	return Comments.aCommentListBuilder().commentList(commentList).build();
//    	}
//    	return Comments.aCommentListBuilder().build();
    }
}