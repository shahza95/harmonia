package syed.shahza.harmonia.backend.dto;

import static syed.shahza.harmonia.backend.dto.LecturerDto.aLecturerDto;
import syed.shahza.harmonia.backend.dto.LecturerDto.Builder;

public class TestLecturerDtos {
    public static Builder aValidLecturerDto() {
        return aLecturerDto().username("username").password("password");
    }
}
