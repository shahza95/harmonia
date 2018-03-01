package syed.shahza.harmonia.backend.core.domain;

public class Comment {
    private String message;

    public Comment() {
        super();
    }

    private Comment(Builder builder) {
        this();
        this.message = builder.message;  
    }

    public String getMessage() {
        return this.message;
    }
  
    public static Builder aComment() {
        return new Builder();
    }

    public static class Builder {
        private String message;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }
}