package com.softra.studentreg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softra.studentreg.exception.StudentNotFoundException;
import com.softra.studentreg.model.Student;
import com.softra.studentreg.service.IService;

@RestController
@CrossOrigin(origins = "*")
public class StudentResource {
	
	@Autowired
	private IService service;
	
	@GetMapping(path = "/students")
	public @ResponseBody List<Student> fetchAllStudents(){
		System.out.println("Inside fetchAllUsers() of UserResource");
		
		List<Student> users = service.retrieveAllStudents();
		
		return users;
	}
	
	@GetMapping(path = "/students/{id}")
	public @ResponseBody Student fetchStudent(@PathVariable int id) {
		System.out.println("Inside fetchStudent() of StudentResource");
		
		Student student = service.retrieveOne(id);
		
		if(student == null) { 
			throw new StudentNotFoundException("No student found with id: " + id);
		}
		
		return student;
	}
	
	@PostMapping(path="/student", consumes = "application/json")
	@ResponseBody 
	public Student createStudent(@Valid @RequestBody Student student) {
		System.out.println("Inside createStudent() of StudentResource");
		Student s = service.save(student);
		return s;
	}
	
	@DeleteMapping(path="/students/{id}")
	public void deleteStudent(@PathVariable int id) {
		System.out.println("Inside deleteStudent() of StudentResource");
		
		boolean wasFound = service.deleteOneStudent(id);
		if(!wasFound) {
			throw new StudentNotFoundException("No student to delete with id: " + id);
		}
	}
	
	@PutMapping(path = "/students/{id}", consumes = "application/json")
	public Student updateStudent(@Valid @RequestBody Student student, @PathVariable int id) {
		System.out.println("Inside deleteStudent() of StudentResource");
		Student s = service.updateStudent(student, id);
		
		if(s == null) { 
			throw new StudentNotFoundException("No student to update with id: " + id);
		}
		return s;
	}
}
