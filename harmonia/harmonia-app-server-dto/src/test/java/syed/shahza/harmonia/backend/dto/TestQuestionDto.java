package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.QuestionDto.aQuestionDto;
import syed.shahza.harmonia.backend.dto.QuestionDto.Builder;

public class TestQuestionDto {
    public static Builder aValidQuestionDto() {
        return aQuestionDto().question("Some question here?").answer("Some answer here.").lectureDto(TestLectureDto.aValidLectureDto().build());
    }
}
