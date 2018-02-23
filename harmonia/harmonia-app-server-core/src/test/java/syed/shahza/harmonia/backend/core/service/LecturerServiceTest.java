package syed.shahza.harmonia.backend.core.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLecturers.aValidLecturer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;

@RunWith(MockitoJUnitRunner.class)
public class LecturerServiceTest {
	private LecturerService lecturerService;
	private Lecturer lecturer;
	
	@Mock
	private LecturerRepository mockLecturerRepository;
	
	@Before
	public void before() {
		lecturer = aValidLecturer().build();
		this.lecturerService = new LecturerService(this.mockLecturerRepository);
	}
	
    @Test
    public void loginInvokesLecturerRepository() {
    	lecturerService.login(lecturer);
    	verify(this.mockLecturerRepository).authorised(lecturer);
    }
	
    @Test
    public void lecturerServiceLoginReturnsTrueIfRepositoryReturnsTrue() {
        when(this.mockLecturerRepository.authorised(lecturer)).thenReturn(true);

        assertThat(lecturerService.login(lecturer), is(true));
    }
    
    @Test
    public void lecturerServiceLoginReturnsFalseIfRepositoryReturnsFalse() {
    	when(this.mockLecturerRepository.authorised(lecturer)).thenReturn(false);

        assertThat(lecturerService.login(lecturer), is(false));
    }
}