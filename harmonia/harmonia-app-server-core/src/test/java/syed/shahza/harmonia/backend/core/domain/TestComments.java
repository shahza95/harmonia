package syed.shahza.harmonia.backend.core.domain;

import java.util.ArrayList;
import java.util.List;

public class TestComments {
	public static Comments aFilledCommentsList(int numberOfComments) {
		List<Comment> comments = new ArrayList<>();
		for(int i=0; i<numberOfComments; i++) {
			comments.add(TestComment.aValidComment().build());
		}
		return Comments.aCommentListBuilder().commentList(comments).build();
	}
	
	public static Comments anEmptyCommentsList() {
		return Comments.aCommentListBuilder().commentList(null).build();
	}
}
