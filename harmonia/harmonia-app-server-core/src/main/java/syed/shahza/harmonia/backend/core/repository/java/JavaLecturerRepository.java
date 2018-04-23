package syed.shahza.harmonia.backend.core.repository.java;

import java.util.ArrayList;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;

public class JavaLecturerRepository implements LecturerRepository {
	private ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
	
	public JavaLecturerRepository() {
		lecturers.add(Lecturer.aLecturer().username("user").password("pass").build());
	}
	
	public Boolean isAuthenticated(Lecturer lecturer) {
		for (Lecturer existingLecturer: lecturers) {
			if(existingLecturer.getUsername().equals(lecturer.getUsername()) && existingLecturer.getPassword().equals(lecturer.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
