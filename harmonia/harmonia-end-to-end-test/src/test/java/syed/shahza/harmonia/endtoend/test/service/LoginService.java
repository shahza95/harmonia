package syed.shahza.harmonia.endtoend.test.service;

import syed.shahza.harmonia.backend.dto.LecturerDto;
import syed.shahza.harmonia.endtoend.test.api.Result;

public interface LoginService {

	public Result login(LecturerDto currentUser);
}
