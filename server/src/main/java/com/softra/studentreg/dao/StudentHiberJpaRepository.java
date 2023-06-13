package com.softra.studentreg.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softra.studentreg.exception.StudentNotFoundException;
import com.softra.studentreg.model.Student;

@Repository(value = "hiberjparepos")
public class StudentHiberJpaRepository implements IDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Student save(Student student) {
		System.out.println("Inside save() of StudentHiberJpaRepository: " + em);
		em.merge(student);
		
		return student;
	}

	@Override
	@Transactional
	public List<Student> findAll() {
		System.out.println("Inside findAll() of StudentHiberJpaRepository: " + em);
		List<Student> students = em.createQuery("from student", Student.class).getResultList();
		
		return students;
	}

	@Override
	@Transactional
	public Student findById(int id) {
		System.out.println("Inside findById() of StudentHiberJpaRepository: " + em);
		Student student = null;
		Query query = em.createQuery("from student where id=:id", Student.class);
		query.setParameter("id", id);
		
		try {
			student = (Student) query.getSingleResult();

		} catch(NoResultException e) {
			return null;
		}
		
		return student;
	}

	@Override
	@Transactional
	public boolean deleteById(int id) {
		System.out.println("Inside deleteById() of StudentHiberJpaRepository: " + em);
		Student student = null;
		student = em.find(Student.class, id);
		if(student == null) {
			return false;
		}
		em.remove(student);
		return true;
	}

	@Override
	@Transactional
	public Student modify(Student newStudent, int id) {
		System.out.println("Inside modify() of StudentHiberJpaRepository: " + em);
		Student modifiedStudent = null;
		
		modifiedStudent = em.find(Student.class, id);
		if(modifiedStudent == null) {
			return null;
		}
		
		modifiedStudent.setName(newStudent.getName());
		modifiedStudent.setAge(newStudent.getAge());
		modifiedStudent.setMobile(newStudent.getMobile());
		modifiedStudent.setAddress(newStudent.getAddress());
		
		em.merge(modifiedStudent);
		
		return modifiedStudent;
	}

}
