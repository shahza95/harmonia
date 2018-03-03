package syed.shahza.harmonia.backend.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.dto.TestLecturerDto.aValidLecturerDto;

import org.junit.Test;

import syed.shahza.harmonia.backend.dto.LecturerDto;

public class LecturerDtoTest {
    @Test
    public void canRetrieveCorrectUsernameOnceSet() {
    	LecturerDto lecturerDto = aValidLecturerDto().build();
        String randomUsernameString = "anotherUsername";
        lecturerDto.setUsername(randomUsernameString);

        assertThat(lecturerDto.getUsername(), is(randomUsernameString));
    }

    @Test
    public void canRetrievePasswordOnceSet() {
    	LecturerDto lecturerDto = aValidLecturerDto().build();
        String randomPasswordString = "anotherPassword";
        lecturerDto.setPassword(randomPasswordString);

        assertThat(lecturerDto.getPassword(), is(randomPasswordString));
    }
}