package syed.shahza.harmonia.backend.core.repository.jpa.h2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import syed.shahza.harmonia.backend.core.repository.jpa.QuestionEntity;

public interface H2QuestionRepository extends CrudRepository<QuestionEntity, Long> {

	@Query("SELECT q FROM QUESTION q JOIN q.lecture l WHERE l.title = :title")
    List<QuestionEntity> findByLectureTitle(@Param("title") String lectureTitle);
	
	QuestionEntity findByUuid(String uuid);
	
}