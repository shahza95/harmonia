package syed.shahza.harmonia.backend.core.domain;

import syed.shahza.harmonia.backend.core.domain.Lecturer.Builder;
import static syed.shahza.harmonia.backend.core.domain.Lecturer.aLecturer;

public class TestLecturer {
    public static Builder aValidLecturer() {
        return aLecturer().username("username").password("password");
    }
}