package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;

@RestController
@RequestMapping("/lecturer/lecture")
public class LectureControllerLecturer {
    private final LectureService lectureService;
    private final LectureAdapter lectureAdapter;

    public LectureControllerLecturer(LectureService lectureService, LectureAdapter lectureAdapter) {
        this.lectureService = lectureService;
        this.lectureAdapter = lectureAdapter;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public LectureDto create(@RequestBody LectureDto lectureDto) {
        return this.lectureAdapter.toDto(this.lectureService.create(this.lectureAdapter.toDomain(lectureDto)));
    }
    
    @RequestMapping(value = "/active/comments", method = RequestMethod.PUT)
    public void toggleComments(@RequestBody LectureDto lectureDto) {
    	this.lectureService.update(this.lectureAdapter.toDomain(lectureDto));
    }
}
