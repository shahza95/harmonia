package syed.shahza.harmonia.endtoend.test.service.page;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.JoinLecturePage;
import syed.shahza.harmonia.endtoend.test.page.LectureCreationPage;
import syed.shahza.harmonia.endtoend.test.service.LectureService;


public class PageBasedLectureService implements LectureService {
    private final LectureCreationPage lectureCreationPage;
    private final JoinLecturePage joinLecturePage;

    public PageBasedLectureService(LectureCreationPage lectureCreationPage, JoinLecturePage joinLecturePage) {
        this.lectureCreationPage = lectureCreationPage;
        this.joinLecturePage = joinLecturePage;
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

}
