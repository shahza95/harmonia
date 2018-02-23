package syed.shahza.harmonia.backend.core.repository;

import java.util.ArrayList;

import syed.shahza.harmonia.backend.core.domain.Lecturer;

public class LecturerRepository {
	private ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
	
	public LecturerRepository() {
		lecturers.add(Lecturer.aLecturer().username("user").password("pass").build());
	}
	
	public Boolean authorised(Lecturer lecturer) {
		for (Lecturer existingLecturer: lecturers) {
			if(existingLecturer.getUsername().equals(lecturer.getUsername()) && existingLecturer.getPassword().equals(lecturer.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
