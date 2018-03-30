package syed.shahza.harmonia.restapi.action;

import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.restapi.client.RestClient;

@RunWith(MockitoJUnitRunner.class)
public class RemoveMoodActionTest {
    private RemoveMoodAction removeMoodAction;

    @Mock
    private RestClient mockRestClient;

    @Before
    public void before() {
        this.removeMoodAction = new RemoveMoodAction(this.mockRestClient);
    }

    @Test
    public void shouldInvokeMockRestClientDeleteMethodWithCorrectParameters() {
    	String emoji = ":)";
    	String lectureTitle = "titleForEmojiLecture";
        this.removeMoodAction.removeMoodByEmoji(lectureTitle, emoji);
        
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("emoji", emoji);
        variables.put("lectureTitle", lectureTitle);

        verify(this.mockRestClient).delete("/student/lecture/active/{lectureTitle}/mood/{emoji}", variables);
    }
}
