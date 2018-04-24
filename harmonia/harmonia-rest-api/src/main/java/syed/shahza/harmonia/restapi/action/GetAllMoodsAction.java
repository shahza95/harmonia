package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.MoodDtoList;
import syed.shahza.harmonia.restapi.client.RestClient;

//class encapsulating methods for retrieving all moods through the backend RESTful endpoint
public class GetAllMoodsAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/lecture/active/{lectureTitle}/moods";

    public GetAllMoodsAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public MoodDtoList getAll(String lectureTitle) {
        return this.restClient.get(URL_ENDPOINT, MoodDtoList.class, lectureTitle);
    }
}
