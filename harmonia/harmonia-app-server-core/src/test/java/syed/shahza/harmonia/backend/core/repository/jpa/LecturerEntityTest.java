package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;


public class LecturerEntityTest {
    private LecturerEntity lecturerEntity;
    private String randomString;

    @Before
    public void before() {
        lecturerEntity = TestLecturerEntity.aLecturerEntity();
        randomString = RandomStringUtils.random(10);
    }

    @Test
    public void getAndSetUsername() {
        lecturerEntity.setUsername(randomString);

        assertThat(lecturerEntity.getUsername(), is(randomString));
    }

    @Test
    public void getAndSetPassword() {
        lecturerEntity.setPassword(randomString);

        assertThat(lecturerEntity.getPassword(), is(randomString));
    }
}