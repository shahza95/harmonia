package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.core.domain.Comment.aComment;
import static syed.shahza.harmonia.backend.core.domain.Comments.aCommentListBuilder;
import static syed.shahza.harmonia.backend.dto.CommentDto.aCommentDto;

import java.util.ArrayList;
import java.util.List;

import syed.shahza.harmonia.backend.core.domain.Comment;
import syed.shahza.harmonia.backend.core.domain.Comments;
import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.CommentDtoList;


public class CommentAdapter {

    public CommentDto toDto(Comment comment) {
        return aCommentDto().message(comment.getMessage()).build();
    }
    
    public Comment toDomain(CommentDto commentDto) {
    	return aComment().message(commentDto.getMessage()).build();
    }
    
    public CommentDtoList toDto(Comments comments) {
    	CommentDtoList commentDtoList = new CommentDtoList();
    	for(Comment comment: comments.getCommentList()) {
    		commentDtoList.addCommentDtoToList(toDto(comment));
    	}
    	return commentDtoList;
    }
    
    public Comments toDomain(CommentDtoList commentDtoList) {
    	List<Comment> commentList = new ArrayList<Comment>();
    	for(CommentDto commentDto: commentDtoList.getCommentDtoList()) {
    		commentList.add(toDomain(commentDto));
    	}
    	return aCommentListBuilder().commentList(commentList).build();
    }
}