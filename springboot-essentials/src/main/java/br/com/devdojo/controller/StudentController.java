package br.com.devdojo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentDao;

	@GetMapping
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(studentDao.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
		verifyIfStudentExists(id);
		Student student = studentDao.findById(id).get();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/findByName/{name}")
	public ResponseEntity<?> findStudentsByName(@PathVariable String name) {
		return new ResponseEntity<>(studentDao.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
	}

	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> save(@Valid @RequestBody Student student) {
		return new ResponseEntity<>(studentDao.save(student), HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		verifyIfStudentExists(id);
		studentDao.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Student student) {
		verifyIfStudentExists(student.getId());
		studentDao.save(student);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private void verifyIfStudentExists(Long id) {
		if (!studentDao.findById(id).isPresent()) {
			throw new ResourceNotFoundException("Student not found for ID: " + id);
		}
	}

}
