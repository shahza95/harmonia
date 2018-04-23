package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.domain.TestLecturer;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LecturerRepository;

@RunWith(MockitoJUnitRunner.class)
public class JpaLecturerRepositoryTest {
	private JpaLecturerRepository repository;
	
	@Mock
	private H2LecturerRepository mockH2LecturerRepository;
	
	@Before
	public void setUp() {
		repository = new JpaLecturerRepository(this.mockH2LecturerRepository);
	}
	
	@Test
	public void isAuthenticatedShouldInvokeLecturerRepository() {
		Lecturer lecturer = TestLecturer.aValidLecturer().build();
		this.repository.isAuthenticated(lecturer);
		
		verify(this.mockH2LecturerRepository).findByUsernameAndPassword(lecturer.getUsername(), lecturer.getPassword());
	}
	
	@Test
	public void isAuthenticatedShouldReturnTrueIfLecturerExists() {
		Lecturer lecturer = TestLecturer.aValidLecturer().build();
		when(this.mockH2LecturerRepository.findByUsernameAndPassword(lecturer.getUsername(), lecturer.getPassword())).thenReturn(TestLecturerEntity.aLecturerEntity());
		
		assertThat(this.repository.isAuthenticated(lecturer), is(true));
	}
	
	@Test
	public void isAuthenticatedShouldReturnFalseIfLecturerNull() {
		Lecturer lecturer = TestLecturer.aValidLecturer().build();
		when(this.mockH2LecturerRepository.findByUsernameAndPassword(lecturer.getUsername(), lecturer.getPassword())).thenReturn(null);
		
		assertThat(this.repository.isAuthenticated(lecturer), is(false));
	}
}
