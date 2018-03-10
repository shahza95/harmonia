package syed.shahza.harmonia.backend.dto;

public class MoodDto {
	private String emoji;
	private LectureDto lectureDto;

    public MoodDto() {
        super();
    }

    private MoodDto(Builder builder) {
        this();
        this.emoji = builder.emoji;
        this.lectureDto = builder.lectureDto;
    }

    public String getEmoji() {
        return this.emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
    
    public LectureDto getLectureDto() {
    	return this.lectureDto;
    }
    
    public void setLectureDto(LectureDto lectureDto) {
    	this.lectureDto = lectureDto;
    }

    public static Builder aMoodDto() {
        return new Builder();
    }

    public static class Builder {
        private String emoji;
        private LectureDto lectureDto;

        public Builder emoji(String emoji) {
            this.emoji = emoji;
            return this;
        }
        
        public Builder lectureDto(LectureDto lectureDto) {
        	this.lectureDto = lectureDto;
        	return this;
        }

        public MoodDto build() {
            return new MoodDto(this);
        }
    }
}
