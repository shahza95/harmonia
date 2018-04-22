package syed.shahza.harmonia.backend.core.repository.jpa;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.core.repository.LecturerRepository;
import syed.shahza.harmonia.backend.core.repository.jpa.h2.H2LecturerRepository;

public class JpaLecturerRepository implements LecturerRepository {
	private final H2LecturerRepository lecturerRepository;
	
    public JpaLecturerRepository(H2LecturerRepository sqliteLecturerRepository) {
        this.lecturerRepository = sqliteLecturerRepository;
    }
	
	@Override
	public Boolean isAuthenticated(Lecturer lecturer) {
		if(this.lecturerRepository.findByUsernameAndPassword(lecturer.getUsername(), lecturer.getPassword()) != null) {
			 return true;
		 }
		return false;
	}
}
