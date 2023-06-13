package com.softra.studentreg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name", nullable = false)
	@Size(min=3, message = "Your name must have at least 3 characters!")
	@NotBlank(message = "Your name is mandatory!")
	private String name;
	
	@Column(name = "age", nullable = false)
	@Min(value = 1, message = "Your age cannot be 0!")
	private Integer age;
	
	@Column(name = "mobile", nullable = false)
	@Min(value = 1, message = "Your mobile number cannot be 0!")
	@Range(min=1000000000L, message="Your mobile number needs to be 10 digits!")
	private long mobile;
	
	@Column(name = "address", nullable = false)
	@NotBlank(message = "Your address is mandatory!")
	private String address;
	
	public Student() {}

	public Student(String name, Integer age, long mobile, String address) {
		super();
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.address = address;
	}
	
	public Student(int id, String name, Integer age, long mobile, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", mobile=" + mobile + ", address=" + address + "]";
	}
}
