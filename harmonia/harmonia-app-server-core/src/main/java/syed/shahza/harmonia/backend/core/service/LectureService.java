package syed.shahza.harmonia.backend.core.service;

import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.repository.LectureRepository;

public class LectureService {
	private final LectureRepository lectureRepository;
	
	public LectureService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}

	public Lecture create(Lecture lecture) {
		return lectureRepository.create(lecture);
	}
	
	public Lecture join(String password) {
		return lectureRepository.retrieveLectureFromPassword(password);
	}
}