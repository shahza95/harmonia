package syed.shahza.harmonia.backend.dto;

public class TestCommentDtoLists {
	public static CommentDtoList aFilledCommentDtoList(int numberOfComments) {
		CommentDtoList commentDtoList = new CommentDtoList();
		for(int i=0; i<numberOfComments; i++) {
			commentDtoList.addCommentDtoToList(TestCommentDtos.aValidCommentDto().build());
		}
		return commentDtoList;
	}
	
	public static CommentDtoList anEmptyCommentDtoList() {
		return new CommentDtoList();
	}
}
