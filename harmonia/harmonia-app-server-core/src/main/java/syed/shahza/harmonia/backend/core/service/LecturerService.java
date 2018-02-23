package syed.shahza.harmonia.backend.core.service;

import syed.shahza.harmonia.backend.core.domain.Lecturer;

public class LecturerService {

	public Boolean login(Lecturer lecturer) {
		if (lecturer.getUsername().equals("username") && lecturer.getPassword().equals("password")) {
			return true;
		}
		return false;
	}
}