package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.core.domain.Comment.aComment;
import static syed.shahza.harmonia.backend.dto.CommentDto.aCommentDto;
import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.dto.CommentDto;


public class CommentAdapter {
	private final LectureAdapter lectureAdapter;
	
	public CommentAdapter(LectureAdapter lectureAdapter) {
		this.lectureAdapter = lectureAdapter;
	}

    public CommentDto toDto(Comment comment) {
        return aCommentDto().message(comment.getMessage()).lectureDto(this.lectureAdapter.toDto(comment.getLecture())).build();
    }
    
    public Comment toDomain(CommentDto commentDto) {
    	return aComment().message(commentDto.getMessage()).lecture(this.lectureAdapter.toDomain(commentDto.getLectureDto())).build();
    }
}