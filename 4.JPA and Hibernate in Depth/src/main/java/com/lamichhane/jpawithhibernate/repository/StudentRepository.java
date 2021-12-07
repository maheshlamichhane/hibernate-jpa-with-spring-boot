package com.lamichhane.jpawithhibernate.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lamichhane.jpawithhibernate.entity.Course;
import com.lamichhane.jpawithhibernate.entity.Passport;
import com.lamichhane.jpawithhibernate.entity.Student;

@Repository
@Transactional
public class StudentRepository
{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	
	public Student findById(Long id)
	{
		return em.find(Student.class,id);
	}
	
	public void deleteById(Long id) {
		Student student=findById(id);
		em.remove(student); 
	}
	
	public Student save(Student student)
	{
		if(student.getId()==null)
		{
			em.persist(student);
		}
		else
		{
			em.merge(student);
		}
		return student;
	}
	
	public void saveStudentWithPassport()
	{
	   Passport passport=new Passport("Z123456");
	   em.persist(passport);
	   
	   Student student=new Student("Mike");
	   student.setPassport(passport);
	   em.persist(student);
	}
	
	
	public void someDummyOperation() {
		 //Database Operation 1 - Retrive student
		  Student student =em.find(Student.class, 20001l);
		  
		  //Database Operaion 2 - Retrive passport
		  Passport passport=student.getPassport();
		  
		  //Database Operation 3 - update passport
		  passport.setNumber("Z34343443");
		  
		 
		  //Database Operaion 4 - update student
		  student.setName("Mahesh - Updated");
	}
	
	  public void insertStudentAndCourse() {
		  Student student=new Student("Jack");
		  Course course=new Course("Microservices in 100 steps");
		  
		  em.persist(student);
		  em.persist(course);
		  
		  student.addCourses(course);
		  course.addStudents(student);
		  em.persist(student);
		  
	  }

}
