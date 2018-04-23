package syed.shahza.harmonia.backend.core.repository.jpa.h2;

import org.springframework.data.repository.CrudRepository;

import syed.shahza.harmonia.backend.core.repository.jpa.LectureEntity;

public interface H2LectureRepository extends CrudRepository<LectureEntity, Long> {

    LectureEntity findByPassword(String password);
    
    LectureEntity findByTitle(String title);
}