package syed.shahza.harmonia.backend.core.domain;

import static syed.shahza.harmonia.backend.core.domain.Comment.aComment;
import syed.shahza.harmonia.backend.core.domain.Comment.Builder;

public class TestComment {
    public static Builder aValidComment() {
        return aComment().message("my comment here").lecture(TestLecture.aValidLecture().build());
    }
}
