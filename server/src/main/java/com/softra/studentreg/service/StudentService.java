package com.softra.studentreg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softra.studentreg.dao.IDao;
import com.softra.studentreg.model.Student;

@Service
public class StudentService implements IService {

	@Autowired
	@Qualifier(value = "hiberjparepos")
	private IDao dao;
	
	@Override
	public List<Student> retrieveAllStudents() {
		System.out.println("Inside retrieveAllStudents() of StudentService");
		
		List<Student> students = dao.findAll();
		return students;
	}

	@Override
	public Student retrieveOne(int id) {
		System.out.println("Inside retrieveOne() of StudentService");
		Student student = null;
		student = dao.findById(id);

		return student;
	}

	@Override
	public Student save(Student student) {
		System.out.println("Inside save() of StudentService");
		Student s = dao.save(student);
		return s;
	}
	
	public boolean deleteOneStudent(int id) {
		System.out.println("Inside deleteOneStudent() of StudentService");
		boolean wasFound = dao.deleteById(id);
		return wasFound;
	}

	@Override
	public Student updateStudent(Student newStudent, int id) {
		Student s = dao.modify(newStudent, id);
		return s;
	}
	
	

}
