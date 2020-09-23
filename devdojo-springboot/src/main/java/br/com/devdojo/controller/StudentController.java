package br.com.devdojo.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.model.Student;
import br.com.devdojo.util.DateUtil;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private DateUtil dateUtil;

	@GetMapping("/list")
	public List<Student> listAll() {
		System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return Arrays.asList(new Student("Deku"), new Student("Todoroki"));
	}

}
