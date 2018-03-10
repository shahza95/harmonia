package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.MoodDtoList;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class GetAllMoodsActionTest {
    private GetAllMoodsAction getAllMoodsAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.getAllMoodsAction = new GetAllMoodsAction(this.mockRestClient);
    }

    @Test
    public void getAllWillInvokeMockRestClientGetMethodWithCorrectParameters() {
    	String lectureTitle = "title";
        this.getAllMoodsAction.getAll(lectureTitle);

        verify(this.mockRestClient).get("/lecture/active/{lectureTitle}/moods", MoodDtoList.class, lectureTitle);
    }
}
