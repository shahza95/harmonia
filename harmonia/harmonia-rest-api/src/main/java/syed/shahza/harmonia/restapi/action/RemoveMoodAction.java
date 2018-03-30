package syed.shahza.harmonia.restapi.action;

import java.util.HashMap;
import java.util.Map;

import syed.shahza.harmonia.restapi.client.RestClient;

public class RemoveMoodAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/student/lecture/active/{lectureTitle}/mood/{emoji}";

    public RemoveMoodAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public void removeMoodByEmoji(String lectureTitle, String emoji) {
    	Map<String, Object> variables = new HashMap<String, Object>();
    	variables.put("lectureTitle", lectureTitle);
    	variables.put("emoji", emoji);
        this.restClient.delete(URL_ENDPOINT, variables);
    }
}
