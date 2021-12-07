package com.lamichhane.jpawithhibernate;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lamichhane.jpawithhibernate.entity.FullTimeEmployee;
import com.lamichhane.jpawithhibernate.entity.PartTimeEmployee;
import com.lamichhane.jpawithhibernate.repository.CourseRepository;
import com.lamichhane.jpawithhibernate.repository.EmployeeRepository;
import com.lamichhane.jpawithhibernate.repository.StudentRepository;

@SpringBootApplication
public class JpawithhibernateApplication  implements CommandLineRunner{
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpawithhibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		/*
		employeeRepository.insert(new PartTimeEmployee("Jill",new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jack",new BigDecimal("10000")));
		employeeRepository.insert(new PartTimeEmployee("Sujan",new BigDecimal("500")));
		employeeRepository.insert(new FullTimeEmployee("Hari",new BigDecimal("1000")));
		
		logger.info("All Employees -> {}",employeeRepository.retrieveAllEmployees());
		
		*/
		
		
		/*
		studentRepository.insertStudentAndCourse();
		*/
		
		/*
		List<Review> reviews=new ArrayList<>();
		Review review1=new Review("5","Grat Hands-on Stuff");
		Review review2=new Review("5","Hatsoff");
		reviews.add(review1);
		reviews.add(review2);
		courseRepository.addHardCoadedReviewsForCourse(10003l, reviews);
		*/
		/*
		studentRepository.saveStudentWithPassport();
	
		Course course = repository.findById(10001l);
		logger.info("Course 10001 -> {}",course.getName());
		repository.deleteById(10002l);
		repository.save(new Course("Microservices in 100 Steps"));
		repository.playWithEntityManager();
		*/
	}
	

}
