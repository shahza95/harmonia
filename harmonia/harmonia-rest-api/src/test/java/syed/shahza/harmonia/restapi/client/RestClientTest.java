package syed.shahza.harmonia.restapi.client;

import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestOperations;

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
        this.restClient.post(ENDPOINT_URL, null, Boolean.class);

        verify(this.mockRestOperations).postForObject(FULL_URL, null, Boolean.class);
    }
    
    @Test
    public void postRequestInvokesMockRestOperationsPostForObjectMethodWithCorrectParameters() {
    	LecturerDto lecturerDto = new LecturerDto();
        this.restClient.post(ENDPOINT_URL, lecturerDto, Boolean.class);

        verify(this.mockRestOperations).postForObject(FULL_URL, lecturerDto, Boolean.class);
    }
    
    @Test
    public void getRequestInvokesMockRestOperationsGetForObjectMethodWithCorrectParameters() {
    	LecturerDto lecturerDto = new LecturerDto();
    	this.restClient.get(ENDPOINT_URL, Boolean.class, lecturerDto);
    	
    	verify(this.mockRestOperations).getForObject(FULL_URL, Boolean.class, lecturerDto);
    }
    
    @Test
    public void deleteRequestInvokesMockRestOperationsDeleteMethodWithCorrectParameters() {
    	LecturerDto lecturerDto = new LecturerDto();
    	Map<String, Object> variables = new HashMap<String, Object>();
    	variables.put("lectureDto", lecturerDto);
    	this.restClient.delete(ENDPOINT_URL, variables);
    	
    	verify(this.mockRestOperations).delete(FULL_URL, variables);
    }
}