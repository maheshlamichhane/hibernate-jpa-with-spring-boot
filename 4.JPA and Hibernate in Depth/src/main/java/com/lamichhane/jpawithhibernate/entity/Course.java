package com.lamichhane.jpawithhibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NamedQueries(
		value= {
				@NamedQuery(name="query_get_all_courses",query="Select c From Course c"),
				@NamedQuery(name="query_get_100_Step_courses",query="Select c From Course c where name like '%100 Steps'")
		}
		)
@Cacheable
public class Course
{
	@Id
	@GeneratedValue
	private Long id;
	
	
	@Column(name="name",nullable=false)
	private String name;
	
	@OneToMany(mappedBy="course")
	private List<Review> reviews=new ArrayList<>();
	
	@ManyToMany(mappedBy="courses")
	private List<Student> students=new ArrayList<>();
	
	
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;
	
	

	public Course() {
	}

	public Course(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review reviews) {
		this.reviews.add(reviews);
	}
	
	public void removeReviews(Review review)
	{
		this.reviews.remove(review);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudents(Student student) {
		this.students.add(student);
	}
	
	
	
	
	
	
	

}
