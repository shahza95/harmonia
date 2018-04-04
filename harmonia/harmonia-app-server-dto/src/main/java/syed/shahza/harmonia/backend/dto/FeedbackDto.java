package syed.shahza.harmonia.backend.dto;

public class FeedbackDto {
	private LectureDto lectureDto;
    private Integer rating;
    private String message;

    public FeedbackDto() {
        super();
    }

    private FeedbackDto(Builder builder) {
        this();
        this.lectureDto = builder.lectureDto;
        this.rating = builder.rating;
        this.message = builder.message;
    }
    
    public LectureDto getLectureDto() {
    	return this.lectureDto;
    }
    
    public void setLectureDto(LectureDto lectureDto) {
    	this.lectureDto = lectureDto;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public String getMessage() {
    	return this.message;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }

    public static Builder aFeedbackDto() {
        return new Builder();
    }

    public static class Builder {
    	private LectureDto lectureDto;
        private Integer rating;
        private String message;
        
        public Builder lectureDto(LectureDto lectureDto) {
        	this.lectureDto = lectureDto;
        	return this;
        }

        public Builder rating(Integer rating) {
            this.rating = rating;
            return this;
        }
        
        public Builder message(String message) {
        	this.message = message;
        	return this;
        }

        public FeedbackDto build() {
            return new FeedbackDto(this);
        }
    }
}
