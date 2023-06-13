package com.softra.studentreg.dao;

import java.util.List;
import java.util.Optional;

import com.softra.studentreg.model.Student;

public interface IDao {

	public Student save(Student student);
	
	public List<Student> findAll();
	
	public Student findById(int id);
	
	public boolean deleteById(int id);
	
	public Student modify(Student newStudent, int id);
}
