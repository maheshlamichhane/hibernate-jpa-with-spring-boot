package com.lamichhane.jpawithhibernate.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lamichhane.jpawithhibernate.JpawithhibernateApplication;
import com.lamichhane.jpawithhibernate.entity.Course;
import com.lamichhane.jpawithhibernate.entity.Passport;
import com.lamichhane.jpawithhibernate.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpawithhibernateApplication.class)
class StudentRepositoryTest {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	  /* if any operaion of below fail then whole transaction will be roll back 
	   * 
	   * Persistence context is a place where all the entities which you are operation upon are being stored
	   * exact querires will be filed at the end of the entire method at the end of the entire method all the changes made to database
	   * using jpa we don't need to care about transactional
	   * */
	  @Test
	  //@Transactional
	  public void someTest() {
		  
		  repository.someDummyOperation();
		  /*
		  //Database Operation 1 - Retrive student
		  Student student =em.find(Student.class, 20001l);
		  
		  //Database Operaion 2 - Retrive passport
		  Passport passport=student.getPassport();
		  
		  //Database Operation 3 - update passport
		  passport.setNumber("Z34343443");
		  
		 
		  //Database Operaion 4 - update student
		  student.setName("Mahesh - Updated");
		  */
	  }

	  @Test
	  @Transactional
      public void retriveStudentAndPassport()
      {
    	  Student student=em.find(Student.class,20001l);
    	  logger.info("student -> {}",student);
    	  logger.info("Passport -> {}",student.getPassport());
      }
	  
	  @Test
	  @Transactional
      public void retrivePassportAndStudent()
      {
    	  Passport passport=em.find(Passport.class,40001l);
    	  logger.info("Passoirt -> {}",passport);
    	  logger.info("Student -> {}",passport.getStudent());
      }
	  
	  @Test
	  @Transactional
      public void retrieveStudentAndCourses()
      {
    	Student student=em.find(Student.class,20001l);
    	logger.info("student -> {}",student);
    	logger.info("courses -> {}",student.getCourses());
      }
	  
	
	  
	  
	
	
	

}
