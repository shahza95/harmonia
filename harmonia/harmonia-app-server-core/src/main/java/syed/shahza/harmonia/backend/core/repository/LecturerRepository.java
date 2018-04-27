package syed.shahza.harmonia.backend.core.repository;

import syed.shahza.harmonia.backend.core.domain.Lecturer;

//data access layer Lecturer interface
public interface LecturerRepository {
	
	Boolean isAuthenticated(Lecturer lecturer);
}
