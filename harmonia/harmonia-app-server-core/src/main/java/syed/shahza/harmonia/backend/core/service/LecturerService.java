package syed.shahza.harmonia.backend.core.service;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;

public class LecturerService {
	private final LecturerRepository lecturerRepository;
	
	public LecturerService(LecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
	}

	public Boolean login(Lecturer lecturer) {
		if (lecturerRepository.authorised(lecturer)) {
			return true;
		}
		return false;
	}
}