package syed.shahza.harmonia.backend.dto;

public class CommentDto {
    private String message;

    public CommentDto() {
        super();
    }

    private CommentDto(Builder builder) {
        this();
        this.message = builder.message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Builder aCommentDto() {
        return new Builder();
    }

    public static class Builder {
        private String message;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public CommentDto build() {
            return new CommentDto(this);
        }
    }
}
