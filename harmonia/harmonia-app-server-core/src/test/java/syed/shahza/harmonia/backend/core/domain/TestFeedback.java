package syed.shahza.harmonia.backend.core.domain;

import static syed.shahza.harmonia.backend.core.domain.Feedback.aFeedback;
import syed.shahza.harmonia.backend.core.domain.Feedback.Builder;

public class TestFeedback {
    public static Builder aValidFeedback() {
        return aFeedback().lecture(TestLecture.aValidLecture().build()).rating(5).message("my comment here");
    }
}