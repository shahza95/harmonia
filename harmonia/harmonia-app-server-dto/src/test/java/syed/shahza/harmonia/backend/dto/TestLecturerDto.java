package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.LecturerDto.aLecturerDto;
import syed.shahza.harmonia.backend.dto.LecturerDto.Builder;

public class TestLecturerDto {
    public static Builder aValidLecturerDto() {
        return aLecturerDto().username("user").password("pass");
    }
}
