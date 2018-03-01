package syed.shahza.harmonia.endtoend.test.service;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Result;

public interface LectureService {

	public Result create(LectureDto lectureDto);

	public Result join(String password);
}
