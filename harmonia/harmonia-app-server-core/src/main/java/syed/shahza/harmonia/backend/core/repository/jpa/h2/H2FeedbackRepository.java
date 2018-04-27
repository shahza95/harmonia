package syed.shahza.harmonia.backend.core.repository.jpa.h2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import syed.shahza.harmonia.backend.core.repository.jpa.FeedbackEntity;

public interface H2FeedbackRepository extends CrudRepository<FeedbackEntity, Long> {

	@Query("SELECT f FROM FEEDBACK f JOIN f.lecture l WHERE l.title = :title")
    List<FeedbackEntity> findByLectureTitle(@Param("title") String lectureTitle);
	
}