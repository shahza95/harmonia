package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.restapi.client.RestClient;

public class GetAllCommentsAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/student/lecture/active/comments";

    public GetAllCommentsAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public CommentDtoList getAll(LectureDto lectureDto) {
        return this.restClient.get(URL_ENDPOINT, CommentDtoList.class, lectureDto);
    }
}
