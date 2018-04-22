package syed.shahza.harmonia.backend.core.repository.java;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestLecturer.aValidLecturer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.repository.java.JavaLecturerRepository;

@RunWith(MockitoJUnitRunner.class)
public class JavaLecturerRepositoryTest {
	private JavaLecturerRepository lecturerRepository;
	
    @Test
    public void lecturerRepositoryAuthorisedReturnsTrueIfCorrectCredentials() {
    	this.lecturerRepository = new JavaLecturerRepository();
        Lecturer lecturer = aValidLecturer().username("user").password("pass").build();

        assertThat(lecturerRepository.isAuthenticated(lecturer), is(true));
    }
    
    @Test
    public void lecturerRepositoryAuthorisedReturnsFalseIfIncorrectCredentials() {
    	this.lecturerRepository = new JavaLecturerRepository();
        Lecturer lecturer = aValidLecturer().username("qwerty").password("123").build();
    	
    	assertThat(lecturerRepository.isAuthenticated(lecturer), is(false));
    }
}