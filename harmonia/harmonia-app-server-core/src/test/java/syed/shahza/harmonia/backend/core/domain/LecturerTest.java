package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestLecturer.aValidLecturer;

import org.junit.Test;

public class LecturerTest {
    @Test
    public void canGetAndSetUsername() {
        String randomUsernameString = "anotherUsername";
        assertThat(aValidLecturer().username(randomUsernameString).build().getUsername(), is(randomUsernameString));
    }

    @Test
    public void canGetAndSetPassword() {
        String randomPasswordString = "anotherPassword";
        assertThat(aValidLecturer().password(randomPasswordString).build().getPassword(), is(randomPasswordString));
    }
}
