package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class SendMoodActionTest {
    private SendMoodAction sendMoodAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.sendMoodAction = new SendMoodAction(this.mockRestClient);
    }

    @Test
    public void shouldInvokeMockRestClientPostMethodWithCorrectParameters() {
    	MoodDto moodDto = new MoodDto();
        this.sendMoodAction.sendMood(moodDto);

        verify(this.mockRestClient).post("/student/lecture/active/mood", moodDto, MoodDto.class);
    }
}
