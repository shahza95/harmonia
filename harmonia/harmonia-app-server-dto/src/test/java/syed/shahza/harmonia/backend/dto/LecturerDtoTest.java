package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLecturerDtos.aValidLecturerDto;

import org.junit.Test;

import syed.shahza.harmonia.backend.dto.LecturerDto;

public class LecturerDtoTest {
    @Test
    public void canRetrieveCorrectUsernameOnceSet() {
    	LecturerDto lecturerDto = aValidLecturerDto().build();
        String randomDataString = "anotherUsername";
        lecturerDto.setUsername(randomDataString);

        assertThat(lecturerDto.getUsername(), is(randomDataString));
    }

    @Test
    public void canRetrievePasswordOnceSet() {
    	LecturerDto lecturerDto = aValidLecturerDto().build();
        String randomDataString = "anotherPassword";
        lecturerDto.setPassword(randomDataString);

        assertThat(lecturerDto.getPassword(), is(randomDataString));
    }
}