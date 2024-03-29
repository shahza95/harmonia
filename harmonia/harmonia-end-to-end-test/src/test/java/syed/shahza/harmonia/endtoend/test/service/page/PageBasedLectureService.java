package syed.shahza.harmonia.endtoend.test.service.page;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureFeedbackLecturerPage;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureFeedbackStudentPage;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureLecturerPage;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureMoodLecturerPage;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureMoodStudentPage;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureQuestionLecturerPage;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureQuestionStudentPage;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureStudentPage;
import syed.shahza.harmonia.endtoend.test.page.JoinLecturePage;
import syed.shahza.harmonia.endtoend.test.page.LectureCreationPage;
import syed.shahza.harmonia.endtoend.test.service.LectureService;


public class PageBasedLectureService implements LectureService {
    private final LectureCreationPage lectureCreationPage;
    private final JoinLecturePage joinLecturePage;
    private final ActiveLectureLecturerPage activeLectureLecturerPage;
    private final ActiveLectureStudentPage activeLectureStudentPage;
    private final ActiveLectureMoodStudentPage activeLectureMoodStudentPage;
    private final ActiveLectureMoodLecturerPage activeLectureMoodLecturerPage;
    private final ActiveLectureFeedbackStudentPage activeLectureFeedbackStudentPage;
    private final ActiveLectureFeedbackLecturerPage activeLectureFeedbackLecturerPage;
    private final ActiveLectureQuestionStudentPage activeLectureQuestionStudentPage;
    private final ActiveLectureQuestionLecturerPage activeLectureQuestionLecturerPage;

    public PageBasedLectureService(LectureCreationPage lectureCreationPage, JoinLecturePage joinLecturePage, ActiveLectureLecturerPage activeLectureLecturerPage, ActiveLectureStudentPage activeLectureStudentPage, ActiveLectureMoodStudentPage activeLectureMoodStudentPage, ActiveLectureMoodLecturerPage activeLectureMoodLecturerPage, ActiveLectureFeedbackStudentPage activeLectureFeedbackStudentPage, ActiveLectureFeedbackLecturerPage activeLectureFeedbackLecturerPage, ActiveLectureQuestionStudentPage activeLectureQuestionStudentPage,  ActiveLectureQuestionLecturerPage activeLectureQuestionLecturerPage) {
        this.lectureCreationPage = lectureCreationPage;
        this.joinLecturePage = joinLecturePage;
        this.activeLectureLecturerPage = activeLectureLecturerPage;
        this.activeLectureStudentPage = activeLectureStudentPage;
        this.activeLectureMoodStudentPage = activeLectureMoodStudentPage;
        this.activeLectureMoodLecturerPage = activeLectureMoodLecturerPage;
        this.activeLectureFeedbackStudentPage = activeLectureFeedbackStudentPage;
        this.activeLectureFeedbackLecturerPage = activeLectureFeedbackLecturerPage;
        this.activeLectureQuestionStudentPage = activeLectureQuestionStudentPage;
        this.activeLectureQuestionLecturerPage = activeLectureQuestionLecturerPage;
    }

    @Override
    public Result create(LectureDto lectureDto) {
        this.lectureCreationPage.navigateTo();
        this.lectureCreationPage.fillOutLectureForm(lectureDto);
        return this.lectureCreationPage.clickCreateButton();
    }
    
    @Override
    public Result join(String password) {
        this.joinLecturePage.navigateTo();
        this.joinLecturePage.enterPassword(password);
        return this.joinLecturePage.clickJoinButton();
    }

	@Override
	public Result addComment(CommentDto commentDto) {
		this.activeLectureStudentPage.enterMessage(commentDto.getMessage());
		return this.activeLectureStudentPage.clickCommentButton(commentDto.getMessage());
	}

	@Override
	public void addMood(MoodDto moodDto) {
		this.activeLectureMoodStudentPage.navigateTo(moodDto.getLectureDto().getTitle());
		this.activeLectureMoodStudentPage.enterEmoji(moodDto);
		this.activeLectureMoodStudentPage.clickSendButton();
	}

	@Override
	public void getAllMoods(String lectureTitle) {
		this.activeLectureMoodLecturerPage.navigateTo(lectureTitle);
	}
	
	@Override
	public Result checkEmojiReceived(String lectureTitle, String emotionString) {
		getAllMoods(lectureTitle);
		return this.activeLectureMoodLecturerPage.checkEmojiIsPresent(emotionString);
	}

	@Override
	public void disableCommenting() {
		this.activeLectureLecturerPage.clickDisableButton();
	}

	@Override
	public Result checkCommentingDisabled() {
		return this.activeLectureStudentPage.checkCommentingDisabled();
	}

	@Override
	public void endLecture(String lectureTitle) {
		this.activeLectureLecturerPage.clickEndLectureButton();
	}

	@Override
	public void addFeedback(FeedbackDto feedbackDto) {
		this.activeLectureFeedbackStudentPage.enterFeedback(feedbackDto);
		this.activeLectureFeedbackStudentPage.clickSubmitButton();
	}

	@Override
	public void viewAllFeedback(String lectureTitle) {
		this.activeLectureFeedbackLecturerPage.navigateTo(lectureTitle);
		
	}

	@Override
	public Result checkFeedbackReceived(FeedbackDto feedbackDto) {
		return this.activeLectureFeedbackLecturerPage.checkFeedbackReceived(feedbackDto);
	}
	
	@Override
	public void addQuestion(QuestionDto questionDto) {
		this.activeLectureQuestionStudentPage.navigateTo(questionDto.getLectureDto().getTitle());
		this.activeLectureQuestionStudentPage.enterQuestion(questionDto);
		this.activeLectureQuestionStudentPage.clickAskButton();
	}
	
	@Override
	public void answerQuestion(QuestionDto questionDto, String answer) {
		this.activeLectureQuestionLecturerPage.navigateToAllQuestions(questionDto.getLectureDto().getTitle());
		this.activeLectureQuestionLecturerPage.clickQuestion(questionDto.getQuestion());
		this.activeLectureQuestionLecturerPage.enterAnswer(answer);
		this.activeLectureQuestionLecturerPage.clickAnswerButton();
	}

	@Override
	public Result checkQuestionAnswered(QuestionDto questionDto, String answer) {
		this.activeLectureQuestionStudentPage.navigateTo(questionDto.getLectureDto().getTitle());
		this.activeLectureQuestionStudentPage.clickQuestion(questionDto.getQuestion());
		return this.activeLectureQuestionStudentPage.checkAnswerVisible(answer);
	}
}
