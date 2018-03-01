package syed.shahza.harmonia.backend.core.service;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;

public class LoginService {
	private final LecturerRepository lecturerRepository;
	
	public LoginService(LecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
	}

	public Boolean login(Lecturer lecturer) {
		if (lecturerRepository.authorised(lecturer)) {
			return true;
		}
		return false;
	}
}