package syed.shahza.harmonia.backend.endpoint.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.core.domain.TestLecturers.aValidLecturer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.service.LoginService;
import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.backend.dto.TestLecturerDtos;
import syed.shahza.harmonia.backend.endpoint.adapter.LecturerAdapter;
import syed.shahza.harmonia.backend.endpoint.controller.LoginController;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    private LoginController loginController;
    private LecturerDto lecturerDto;

    @Mock
    private LecturerAdapter mockLecturerAdapter;

    @Mock
    private LoginService mockLecturerService;

    @Before
    public void before() {
        this.loginController = new LoginController(this.mockLecturerService, this.mockLecturerAdapter);
        lecturerDto = TestLecturerDtos.aValidLecturerDto().build();
    }

    @Test
    public void loginInvokesServiceWithLecturerDomainObject() {
    	Lecturer lecturer = aValidLecturer().build();
    	when(mockLecturerAdapter.toDomain(lecturerDto)).thenReturn(lecturer);
        this.loginController.login(lecturerDto);
        
        verify(this.mockLecturerService).login(lecturer);
    }

    @Test
    public void loginInvokesAdapter() {
        this.loginController.login(lecturerDto);
        verify(this.mockLecturerAdapter).toDomain(lecturerDto);
    }
}