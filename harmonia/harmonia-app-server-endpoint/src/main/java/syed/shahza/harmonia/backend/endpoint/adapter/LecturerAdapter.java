package syed.shahza.harmonia.backend.endpoint.adapter;

import static syed.shahza.harmonia.backend.dto.LecturerDto.aLecturerDto;
import static syed.shahza.harmonia.backend.core.domain.Lecturer.aLecturer;

import syed.shahza.harmonia.backend.core.domain.Lecturer;
import syed.shahza.harmonia.backend.dto.LecturerDto;


public class LecturerAdapter {

    public LecturerDto toDto(Lecturer lecturer) {
        return aLecturerDto().username(lecturer.getUsername()).password(lecturer.getPassword()).build();
    }
    
    public Lecturer toDomain(LecturerDto lecturerDto) {
    	return aLecturer().username(lecturerDto.getUsername()).password(lecturerDto.getPassword()).build();
    }
}