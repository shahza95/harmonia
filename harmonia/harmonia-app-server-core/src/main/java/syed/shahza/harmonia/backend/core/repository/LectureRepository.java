package syed.shahza.harmonia.backend.core.repository;

import java.util.ArrayList;

import syed.shahza.harmonia.backend.core.domain.Lecture;

public class LectureRepository {
	private ArrayList<Lecture> lectures = new ArrayList<Lecture>();
	
	public Lecture create(Lecture lecture) {
		lectures.add(lecture);
		return lecture;
	}
}
