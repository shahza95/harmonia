package syed.shahza.harmonia.endtoend.test.service;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.endtoend.test.api.Result;

public interface LectureService {

	public Result create(LectureDto lectureDto);

	public Result join(String password);

	public Result addComment(CommentDto commentDto);

	public void addMood(MoodDto moodDto);

	public void getAllMoods(String lectureTitle);
	
	public Result checkEmojiReceived(String lectureTitle, String emotionString);
	
	public void disableCommenting();

	public Result checkCommentingDisabled();
	
	public void endLecture(String lectureTitle);
	
	public void addFeedback(FeedbackDto feedbackDto);
	
	public void viewAllFeedback(String lectureTitle);
	
	public Result checkFeedbackReceived(FeedbackDto feedbackDto);

	public void addQuestion(QuestionDto questionDto);
	
	public void answerQuestion(QuestionDto questionDto, String answer);

	public Result checkQuestionAnswered(QuestionDto questionDto, String answer);
}
