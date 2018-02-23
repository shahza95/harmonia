package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class LoginActionTest {
    private LoginAction loginAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.loginAction = new LoginAction(this.mockRestClient);
    }

    @Test
    public void loginWillInvokeMockRestClientGetMethodWithCorrectParameters() {
    	LecturerDto lecturerDto = new LecturerDto();
        this.loginAction.login(lecturerDto);

        verify(this.mockRestClient).post("/login", lecturerDto, Boolean.class);
    }
}
