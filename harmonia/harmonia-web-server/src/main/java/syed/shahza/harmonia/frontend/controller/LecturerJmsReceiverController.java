package syed.shahza.harmonia.frontend.controller;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import syed.shahza.harmonia.backend.dto.LectureDto;

//@EnableJms
public class LecturerJmsReceiverController {

//	@JmsListener(destination = "lecture", containerFactory = "myFactory")
//	public ModelAndView updateComments(RedirectAttributes redirectAttributes) {
//		ModelAndView modelAndView = new ModelAndView("redirect:/lecturer/lecture/active/comments");
//		redirectAttributes.addFlashAttribute("lectureDto", this.lectureDto);
//		return modelAndView;
//	}
}
