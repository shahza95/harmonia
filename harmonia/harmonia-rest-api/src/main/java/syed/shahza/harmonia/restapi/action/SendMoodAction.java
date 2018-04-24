package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.restapi.client.RestClient;

//class encapsulating methods for sending mood through the backend RESTful endpoint
public class SendMoodAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/student/lecture/active/mood";

    public SendMoodAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public MoodDto sendMood(MoodDto moodDto) {
        return this.restClient.post(URL_ENDPOINT, moodDto, MoodDto.class);
    }
}
