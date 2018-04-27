package syed.shahza.harmonia.backend.core.repository.jpa.h2;

import org.springframework.data.repository.Repository;

import syed.shahza.harmonia.backend.core.repository.jpa.LecturerEntity;

public interface H2LecturerRepository extends Repository<LecturerEntity, Long> {

    LecturerEntity findByUsernameAndPassword(String username, String password);
}