package syed.shahza.harmonia.backend.core.domain;

import static syed.shahza.harmonia.backend.core.domain.Comment.aComment;
import syed.shahza.harmonia.backend.core.domain.Comment.Builder;

public class TestComments {
    public static Builder aValidComment() {
        return aComment().message("my comment here");
    }
}
