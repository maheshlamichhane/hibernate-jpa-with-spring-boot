package com.lamichhane.jpawithhibernate.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Passport
{
	@Id
	@GeneratedValue
	private Long id;
	
	
	@Column(nullable=false)
	private String number;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy="passport")
	private Student student;
	


	public Passport() {
	}


	public Passport(Long id, String number) {
		this.id = id;
		this.number = number;
	}
	
	


	public Passport(String number) {
		this.number = number;
	}


	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number + "]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
  
	
	
	

}
