package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.MoodSummaryDtoList;
import syed.shahza.harmonia.restapi.client.RestClient;

public class GetMoodsSummaryAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecture/active/{lectureTitle}/moods/summary";

    public GetMoodsSummaryAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public MoodSummaryDtoList getSummary(String lectureTitle) {
        return this.restClient.get(URL_ENDPOINT, MoodSummaryDtoList.class, lectureTitle);
    }
}
