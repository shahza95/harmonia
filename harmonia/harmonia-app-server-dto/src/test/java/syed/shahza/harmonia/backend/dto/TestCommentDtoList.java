package syed.shahza.harmonia.backend.dto;

public class TestCommentDtoList {
	public static CommentDtoList aFilledCommentDtoList(int numberOfComments) {
		CommentDtoList commentDtoList = new CommentDtoList();
		for(int i=0; i<numberOfComments; i++) {
			commentDtoList.addCommentDtoToList(TestCommentDto.aValidCommentDto().build());
		}
		return commentDtoList;
	}
	
	public static CommentDtoList anEmptyCommentDtoList() {
		return new CommentDtoList();
	}
}
