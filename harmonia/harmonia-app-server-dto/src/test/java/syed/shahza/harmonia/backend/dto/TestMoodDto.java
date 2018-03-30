package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.MoodDto.aMoodDto;
import syed.shahza.harmonia.backend.dto.MoodDto.Builder;

public class TestMoodDto {
    public static Builder aValidMoodDto() {
        return aMoodDto().emoji("&#x1F642;").emotionDto(EmotionDto.HAPPY).lectureDto(TestLectureDto.aValidLectureDto().build());
    }
}
