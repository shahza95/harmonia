package syed.shahza.harmonia.backend.endpoint.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import syed.shahza.harmonia.backend.core.service.LectureService;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.endpoint.adapter.LectureAdapter;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;
    private final LectureAdapter lectureAdapter;

    public LectureController(LectureService lectureService, LectureAdapter lectureAdapter) {
        this.lectureService = lectureService;
        this.lectureAdapter = lectureAdapter;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public LectureDto create(@RequestBody LectureDto lectureDto) {
        return this.lectureAdapter.toDto(this.lectureService.create(this.lectureAdapter.toDomain(lectureDto)));
    }
    
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public LectureDto join(@RequestBody String password) {
    	return this.lectureAdapter.toDto(this.lectureService.join(password));
    }
}
