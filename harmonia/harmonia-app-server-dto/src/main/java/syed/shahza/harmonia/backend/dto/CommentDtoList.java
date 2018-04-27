package syed.shahza.harmonia.backend.dto;

import java.util.ArrayList;
import java.util.List;

//Custom high level object of multiple CommentDtos
public class CommentDtoList {
    private List<CommentDto> commentDtoList = new ArrayList<>();
    
    public CommentDtoList() {
    	super();
    }
    
    public CommentDtoList(List<CommentDto> commentDtoList) {
    	this.commentDtoList = commentDtoList;
    }

    public List<CommentDto> getCommentDtoList() {
        return new ArrayList<>(commentDtoList);
    }

    public void addCommentDtoToList(CommentDto commentDto) {
        this.commentDtoList.add(commentDto);
    }
}
