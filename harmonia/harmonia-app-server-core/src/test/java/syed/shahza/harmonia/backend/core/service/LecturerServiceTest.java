package syed.shahza.harmonia.backend.core.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestLecturers.aValidLecturer;

import org.junit.Test;

import syed.shahza.harmonia.backend.core.domain.Lecturer;

public class LecturerServiceTest {
    @Test
    public void lecturerServiceLoginReturnsABoolean() {
        LecturerService lecturerService = new LecturerService();
        Lecturer lecturer = aValidLecturer().build();

        assertThat(lecturerService.login(lecturer), is(instanceOf(Boolean.class)));
    }
}