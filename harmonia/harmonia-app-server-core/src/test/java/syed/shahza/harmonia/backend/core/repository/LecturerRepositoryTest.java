package syed.shahza.harmonia.backend.core.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestLecturers.aValidLecturer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecturer;

@RunWith(MockitoJUnitRunner.class)
public class LecturerRepositoryTest {
	private LecturerRepository lecturerRepository;
	
    @Test
    public void lecturerRepositoryAuthorisedReturnsTrueIfCorrectCredentials() {
    	this.lecturerRepository = new LecturerRepository();
        Lecturer lecturer = aValidLecturer().username("user").password("pass").build();

        assertThat(lecturerRepository.authorised(lecturer), is(true));
    }
    
    @Test
    public void lecturerRepositoryAuthorisedReturnsFalseIfIncorrectCredentials() {
    	this.lecturerRepository = new LecturerRepository();
        Lecturer lecturer = aValidLecturer().username("qwerty").password("123").build();
    	
    	assertThat(lecturerRepository.authorised(lecturer), is(false));
    }
}