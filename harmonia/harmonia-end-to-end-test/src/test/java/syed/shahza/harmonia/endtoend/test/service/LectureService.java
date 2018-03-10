package syed.shahza.harmonia.endtoend.test.service;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.endtoend.test.api.Result;

public interface LectureService {

	public Result create(LectureDto lectureDto);

	public Result join(String password);

	public Result addComment(CommentDto commentDto);

	public void addMood(MoodDto moodDto);

	public void getAllMoods(String lectureTitle);
	
	public Result checkEmojiReceived(String lectureTitle, String emoji);
}
