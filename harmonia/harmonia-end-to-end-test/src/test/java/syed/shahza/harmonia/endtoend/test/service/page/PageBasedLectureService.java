package syed.shahza.harmonia.endtoend.test.service.page;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.ActiveLectureStudentPage;
import syed.shahza.harmonia.endtoend.test.page.JoinLecturePage;
import syed.shahza.harmonia.endtoend.test.page.LectureCreationPage;
import syed.shahza.harmonia.endtoend.test.service.LectureService;


public class PageBasedLectureService implements LectureService {
    private final LectureCreationPage lectureCreationPage;
    private final JoinLecturePage joinLecturePage;
    private final ActiveLectureStudentPage activeLectureStudentPage;

    public PageBasedLectureService(LectureCreationPage lectureCreationPage, JoinLecturePage joinLecturePage, ActiveLectureStudentPage activeLectureStudentPage) {
        this.lectureCreationPage = lectureCreationPage;
        this.joinLecturePage = joinLecturePage;
        this.activeLectureStudentPage = activeLectureStudentPage;
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

}
