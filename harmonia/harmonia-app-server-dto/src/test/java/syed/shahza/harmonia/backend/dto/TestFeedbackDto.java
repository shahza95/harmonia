package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.FeedbackDto.aFeedbackDto;
import syed.shahza.harmonia.backend.dto.FeedbackDto.Builder;

public class TestFeedbackDto {
    public static Builder aValidFeedbackDto() {
        return aFeedbackDto().lectureDto(TestLectureDto.aValidLectureDto().build()).rating(3).message("some message here");
    }
}
