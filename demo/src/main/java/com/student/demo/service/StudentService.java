package com.student.demo.service;

import java.util.List;

import com.student.demo.model.Student;

public interface StudentService {

	public List<Student> findAll();

	public Student findById(Long id);

	public void save(Student student);

	public void deleteById(Long id);

	public void update(Student student, Long id);
}
