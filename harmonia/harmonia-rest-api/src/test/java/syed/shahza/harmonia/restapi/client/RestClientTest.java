package syed.shahza.harmonia.restapi.client;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestOperations;

import syed.shahza.harmonia.restapi.client.RestClient;
import syed.shahza.harmonia.backend.dto.LecturerDto;

@RunWith(MockitoJUnitRunner.class)
public class RestClientTest {
    private RestClient restClient;
    private final static String BASE_URL = "localhost:8080";
    private final static String ENDPOINT_URL = "/login";
    private final static String FULL_URL = BASE_URL + ENDPOINT_URL;

    @Mock
    private RestOperations mockRestOperations;

    @Before
    public void before() {
        this.restClient = new RestClient(BASE_URL, this.mockRestOperations);
    }
    
    @Test
    public void postRequestInvokesMockRestOperationsPostForObjectMethodWithCorrectParametersForNullObjectPost() {
        this.restClient.post(ENDPOINT_URL, null, LecturerDto.class);

        verify(this.mockRestOperations).postForObject(FULL_URL, null, LecturerDto.class);
    }
}