package br.com.devdojo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.model.Student;

@RestController
@RequestMapping("/student")
public class StudentController {

	@GetMapping("/list")
	public List<Student> listAll() {
		return Arrays.asList(new Student("Deku"), new Student("Todoroki"));
	}

}
