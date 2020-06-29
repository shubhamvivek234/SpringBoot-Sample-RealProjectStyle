package com.student.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.demo.exception.StudentNotFoundException;
import com.student.demo.model.Student;
import com.student.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> findAll() {
		List<Student> objStudent = studentRepository.findAll();
		return objStudent;
	}

	public Student findById(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (!student.isPresent())
			throw new StudentNotFoundException("Student with id-" + id + " is not available.");
		return student.get();
	}

	@Transactional
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Transactional
	public void deleteById(Long id) {
		studentRepository.deleteById(id);
	}

	@Transactional
	public void update(Student student, Long id) {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if (!studentOptional.isPresent())
			throw new StudentNotFoundException("Student with id-" + id + " is not available.");
		student.setId(id);
		studentRepository.save(student);
	}
}
