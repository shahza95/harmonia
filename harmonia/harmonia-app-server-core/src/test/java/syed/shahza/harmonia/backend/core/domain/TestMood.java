package syed.shahza.harmonia.backend.core.domain;

import static syed.shahza.harmonia.backend.core.domain.Mood.aMood;
import syed.shahza.harmonia.backend.core.domain.Mood.Builder;

public class TestMood {
    public static Builder aValidMood() {
        return aMood().emoji("&#x1F642;").emotion(Emotion.HAPPY).lecture(TestLecture.aValidLecture().build());
    }
}
