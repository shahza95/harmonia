package syed.shahza.harmonia.backend.core.repository.jpa.h2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import syed.shahza.harmonia.backend.core.repository.jpa.CommentEntity;

public interface H2CommentRepository extends CrudRepository<CommentEntity, Long> {

	@Query("SELECT c FROM COMMENT c JOIN c.lecture l WHERE l.title = :title")
    List<CommentEntity> findByLectureTitle(@Param("title") String lectureTitle);
}