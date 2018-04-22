package syed.shahza.harmonia.backend.core.repository;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;

public interface CommentRepository {

	Comments getAllComments(String lectureTitle);

	Comment addComment(Comment comment);
}
