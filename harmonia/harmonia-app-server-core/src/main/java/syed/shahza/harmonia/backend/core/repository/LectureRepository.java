package syed.shahza.harmonia.backend.core.repository;

import static syed.shahza.harmonia.backend.core.domain.Lecture.aLecture;

import java.util.ArrayList;

import syed.shahza.harmonia.backend.core.domain.Lecture;

public class LectureRepository {
	private ArrayList<Lecture> lectures = new ArrayList<Lecture>();
	
	public Lecture create(Lecture lecture) {
		lectures.add(lecture);
		return lecture;
	}
	
	public Lecture retrieveLectureFromPassword(String password) {
		for(Lecture lecture: lectures) {
			if(lecture.getPassword().equals(password)) {
				return lecture;
			}
		}
		return aLecture().build();
	}
}
