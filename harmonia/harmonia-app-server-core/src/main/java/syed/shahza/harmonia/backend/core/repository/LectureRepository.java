package syed.shahza.harmonia.backend.core.repository;

import syed.shahza.harmonia.backend.core.domain.Lecture;

//data access layer Lecture interface
public interface LectureRepository {

	Lecture create(Lecture lecture);

	Lecture retrieveLectureFromPassword(String password);

	Lecture retrieveLectureFromTitle(String lectureTitle);

	void update(Lecture lecture);
}
