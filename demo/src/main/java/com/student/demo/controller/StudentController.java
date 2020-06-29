package com.student.demo.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.student.demo.model.Student;
import com.student.demo.service.StudentService;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
//@RequestMapping(path = "/api/v/1")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	private StudentService studentService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value="/student", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Student> retrieveAllStudents() {
		logger.info("Retrieve all students data");
		return studentService.findAll();
	}

	@GetMapping("/student/{id}")
	public Student retrieveStudent(
			@PathVariable @Validated @Min(value = 0, message = "id must be greater than 0") @Max(value = 1000, message = "id must be lower than or equal to 1000") Long id) {
		logger.info("Retrieve student data by id");
		Student student = studentService.findById(id);
		return student;
	}

	@PostMapping("/student")
	public String createStudent(@Valid @RequestBody Student student, Locale locale) {
		logger.info("Inserting student data");
		studentService.save(student);
		return messageSource.getMessage("sucess.insert", null, locale);
	}

	@PutMapping("/student/{id}")
	public String updateStudent(@Valid @RequestBody Student student, @PathVariable long id, Locale locale) {
		logger.info("Updating Student data");
		studentService.update(student, id);
		return messageSource.getMessage("sucess.update", null, locale);
	}

	@DeleteMapping("/student/{id}")
	public String deleteStudent(@PathVariable long id, Locale locale) {
		logger.info("Deleting student data");
		studentService.deleteById(id);
		return messageSource.getMessage("error.delete", null, locale);
	}
}
