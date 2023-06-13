package com.softra.studentreg.service;

import java.util.List;

import com.softra.studentreg.model.Student;

public interface IService {

	public List<Student> retrieveAllStudents();
	
	public Student retrieveOne(int id);
	
	public Student save(Student student);
	
	public boolean deleteOneStudent(int id);
	
	public Student updateStudent(Student newStudent, int id);
	
	
}
