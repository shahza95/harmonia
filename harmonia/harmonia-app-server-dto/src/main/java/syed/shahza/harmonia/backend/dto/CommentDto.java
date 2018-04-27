package syed.shahza.harmonia.backend.dto;

//POJO for transferable (serializable) Comment object
public class CommentDto {
	private LectureDto lectureDto;
    private String message;

    public CommentDto() {
        super();
    }

    private CommentDto(Builder builder) {
        this();
        this.message = builder.message;
        this.lectureDto = builder.lectureDto;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public LectureDto getLectureDto() {
    	return this.lectureDto;
    }
    
    public void setLectureDto(LectureDto lectureDto) {
    	this.lectureDto = lectureDto;
    }

    public static Builder aCommentDto() {
        return new Builder();
    }

    public static class Builder {
        private String message;
        private LectureDto lectureDto;

        public Builder message(String message) {
            this.message = message;
            return this;
        }
        
        public Builder lectureDto(LectureDto lectureDto) {
        	this.lectureDto = lectureDto;
        	return this;
        }

        public CommentDto build() {
            return new CommentDto(this);
        }
    }
}
