package syed.shahza.harmonia.restapi.action;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.restapi.client.RestClient;

public class AddCommentAction {
    private final RestClient restClient;
    private final static String URL_ENDPOINT = "/student/lecture/active/comments/add";

    public AddCommentAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public CommentDto addComment(CommentDto commentDto) {
        return this.restClient.post(URL_ENDPOINT, commentDto, CommentDto.class);
    }
}
