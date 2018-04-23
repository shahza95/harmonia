package syed.shahza.harmonia.backend.core.repository.jpa.h2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import syed.shahza.harmonia.backend.core.repository.jpa.MoodEntity;

public interface H2MoodRepository extends CrudRepository<MoodEntity, Long> {

	@Query("SELECT m FROM MOOD m JOIN m.lecture l WHERE l.title = :title")
    List<MoodEntity> findByLectureTitle(@Param("title") String lectureTitle);

	@Query("SELECT m FROM MOOD m JOIN m.lecture l WHERE l.title = :title AND m.emoji = :emoji")
	MoodEntity findByTitleAndEmoji(@Param("title") String lectureTitle,@Param("emoji") String emoji);
	
}