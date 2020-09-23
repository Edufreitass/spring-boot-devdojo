package br.com.devdojo.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.error.CustomErrorType;
import br.com.devdojo.model.Student;
import br.com.devdojo.util.DateUtil;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final DateUtil dateUtil;

	public StudentController(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}

	@GetMapping
	public ResponseEntity<?> listAll() {
		System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
		Student student = new Student();
		student.setId(id);
		int index = Student.studentList.indexOf(student);
		if (index == -1) {
			return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
	}

}
