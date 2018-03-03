package syed.shahza.harmonia.backend.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Comments {
    private List<Comment> commentList;

    private Comments(Builder builder) {
        this.commentList = builder.commentList;
    }
    
    public Comments(List<Comment> comments) {
    	this.commentList = comments;
    }

    public List<Comment> getCommentList() {
        return this.commentList;
    }

    public static Builder aCommentListBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Comment> commentList;

        private Builder() {
            this.commentList = new ArrayList<>();
        }

        private Builder(List<Comment> comments) {
            this.commentList = comments;
        }

        public Builder commentList(List<Comment> commentList) {
            this.commentList.addAll(commentList);
            return this;
        }

        public Comments build() {
            return new Comments(this);
        }
    }
}
