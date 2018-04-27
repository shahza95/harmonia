package syed.shahza.harmonia.backend.core.repository;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;

//data access layer Comment interface
public interface CommentRepository {

	Comments getAllComments(String lectureTitle);

	Comment addComment(Comment comment);
}
