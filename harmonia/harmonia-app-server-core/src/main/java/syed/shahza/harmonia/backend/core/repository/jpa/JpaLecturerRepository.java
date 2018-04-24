package syed.shahza.harmonia.backend.core.repository.jpa;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LecturerRepository;

//data access layer, Lecturer related requests, for any database implementation (currently set to the only option: H2)
public class JpaLecturerRepository implements LecturerRepository {
	private final H2LecturerRepository lecturerRepository;
	
    public JpaLecturerRepository(H2LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }
	
	@Override
	public Boolean isAuthenticated(Lecturer lecturer) {
		if(this.lecturerRepository.findByUsernameAndPassword(lecturer.getUsername(), lecturer.getPassword()) != null) {
			 return true;
		 }
		return false;
	}
}
