package syed.shahza.harmonia.backend.core.repository;

import syed.shahza.harmonia.backend.core.domain.Lecture;

public interface LectureRepository {

	Lecture create(Lecture lecture);

	Lecture retrieveLectureFromPassword(String password);

	Lecture retrieveLectureFromTitle(String lectureTitle);

	void update(Lecture lecture);
}
