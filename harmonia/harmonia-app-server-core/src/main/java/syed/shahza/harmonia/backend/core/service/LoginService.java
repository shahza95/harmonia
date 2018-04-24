package syed.shahza.harmonia.backend.core.service;

import org.springframework.stereotype.Component;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;

// entry point to core application where business logic occurs
@Component
public class LoginService {
	private final LecturerRepository lecturerRepository;
	
	public LoginService(LecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
	}

	// handle login request received from rest controller (app-server-endpoint)
	public Boolean login(Lecturer lecturer) {
		if (lecturerRepository.isAuthenticated(lecturer)) {
			return true;
		}
		return false;
	}
}