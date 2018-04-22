package syed.shahza.harmonia.backend.core.repository.jpa;

import org.apache.commons.lang3.RandomStringUtils;

public class TestLecturerEntity {
    public static LecturerEntity aLecturerEntity() {
        LecturerEntity lecturerEntity = new LecturerEntity();
        lecturerEntity.setUsername(RandomStringUtils.random(10));
        lecturerEntity.setPassword(RandomStringUtils.random(10));
        return lecturerEntity;
    }
}
