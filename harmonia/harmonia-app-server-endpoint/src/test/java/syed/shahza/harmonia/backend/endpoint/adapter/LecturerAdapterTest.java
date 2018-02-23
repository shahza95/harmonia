package syed.shahza.harmonia.backend.endpoint.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import static syed.shahza.harmonia.backend.core.domain.TestLecturers.aValidLecturer;
import static syed.shahza.harmonia.backend.dto.TestLecturerDtos.aValidLecturerDto;

public class LecturerAdapterTest {
    private LecturerAdapter lecturerAdapter;

    @Before
    public void before() {
        this.lecturerAdapter = new LecturerAdapter();
    }

    @Test
    public void lecturerAdapterCanAdaptUsernameToDto() {
        assertThat(this.lecturerAdapter.toDto(aValidLecturer().username("someUsername").build()).getUsername(), is("someUsername"));
    }

    @Test
    public void lecturerAdapterCanAdaptPasswordToDto() {
        assertThat(this.lecturerAdapter.toDto(aValidLecturer().password("somePassword").build()).getPassword(), is("somePassword"));
    }

    @Test
    public void lecturerAdapterCanAdaptUsernameToDomain() {
        assertThat(this.lecturerAdapter.toDomain(aValidLecturerDto().username("someUsername").build()).getUsername(), is("someUsername"));
    }

    @Test
    public void lecturerAdapterCanAdaptPasswordToDomain() {
        assertThat(this.lecturerAdapter.toDomain(aValidLecturerDto().password("somePassword").build()).getPassword(), is("somePassword"));
    }
}