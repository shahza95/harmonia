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
public class LoginServiceTest {
	private LoginService loginService;
	private Lecturer lecturer;
	
	@Mock
	private LecturerRepository mockLecturerRepository;
	
	@Before
	public void before() {
		lecturer = aValidLecturer().build();
		this.loginService = new LoginService(this.mockLecturerRepository);
	}
	
    @Test
    public void loginInvokesLecturerRepository() {
    	loginService.login(lecturer);
    	verify(this.mockLecturerRepository).authorised(lecturer);
    }
	
    @Test
    public void loginReturnsTrueIfRepositoryReturnsTrue() {
        when(this.mockLecturerRepository.authorised(lecturer)).thenReturn(true);

        assertThat(loginService.login(lecturer), is(true));
    }
    
    @Test
    public void loginReturnsFalseIfRepositoryReturnsFalse() {
    	when(this.mockLecturerRepository.authorised(lecturer)).thenReturn(false);

        assertThat(loginService.login(lecturer), is(false));
    }
}