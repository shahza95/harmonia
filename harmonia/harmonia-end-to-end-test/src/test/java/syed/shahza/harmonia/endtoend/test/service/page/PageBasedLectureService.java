package syed.shahza.harmonia.endtoend.test.service.page;

import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.endtoend.test.api.Result;
import syed.shahza.harmonia.endtoend.test.page.LectureCreationPage;
import syed.shahza.harmonia.endtoend.test.service.LectureService;


public class PageBasedLectureService implements LectureService {
    private final LectureCreationPage lectureCreationPage;

    public PageBasedLectureService(LectureCreationPage lectureCreationPage) {
        this.lectureCreationPage = lectureCreationPage;
    }

    @Override
    public Result create(LectureDto lectureDto) {
        this.lectureCreationPage.navigateTo();
        this.lectureCreationPage.fillOutLectureForm(lectureDto);
        return this.lectureCreationPage.clickCreateButton();
    }

}
