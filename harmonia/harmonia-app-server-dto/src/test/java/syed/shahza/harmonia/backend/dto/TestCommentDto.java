package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.CommentDto.aCommentDto;
import syed.shahza.harmonia.backend.dto.CommentDto.Builder;

public class TestCommentDto {
    public static Builder aValidCommentDto() {
        return aCommentDto().message("some message here").lectureDto(TestLectureDto.aValidLectureDto().build());
    }
}
