package syed.shahza.harmonia.backend.core.domain;

import static syed.shahza.harmonia.backend.core.domain.Question.aQuestion;

import java.util.UUID;

import syed.shahza.harmonia.backend.core.domain.Question.Builder;

public class TestQuestion {
    public static Builder aValidQuestion() {
        return aQuestion().id(UUID.randomUUID().toString()).question("my question here").answer("my answer here").lecture(TestLecture.aValidLecture().build());
    }
}
