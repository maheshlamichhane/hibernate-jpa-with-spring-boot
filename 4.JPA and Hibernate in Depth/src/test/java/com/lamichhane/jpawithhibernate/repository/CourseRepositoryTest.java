package com.lamichhane.jpawithhibernate.repository;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lamichhane.jpawithhibernate.JpawithhibernateApplication;
import com.lamichhane.jpawithhibernate.entity.Course;
import com.lamichhane.jpawithhibernate.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpawithhibernateApplication.class)
class CourseRepositoryTest {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;

	@Test
	void findById_basic()
	{
		Course course=repository.findById(10001l);
		assertEquals("JPA in 50 Steps",course.getName());
		logger.info("Testing is Running");
	}
	
	/*
	@Test
	@DirtiesContext
	void deleteById_basic()
	{
	   repository.deleteById(10003l);
	   assertNull(repository.findById(10003l));
	}
	*/
	
	@Test
	@DirtiesContext
	public void save_basic()
	{
		//update course
		Course course=repository.findById(10001l);
		assertEquals("JPA in 50 Steps",course.getName());
		course.setName("JPA in 50 Steps - Updated");
		repository.save(course);
		
		Course course1=repository.findById(10001l);
		assertEquals("JPA in 50 Steps - Updated",course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntitymanager()
	{
	    repository.playWithEntityManager();
	}
	
	
	
	
	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course=repository.findById(10001l);
		logger.info("{}",course.getReviews());
	}
	
	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class,50001l);
		logger.info("{}",review.getCourse());
	}
	
	@Test
	@Transactional
	public void findId_firstlevelCacheDemo() {
		Course course=repository.findById(10001l);
		logger.info("First Course Retrived {} ",course);
		
		Course course1=repository.findById(10001l);
		logger.info("First Course Retrieved again {}",course1);
		
		assertEquals("JPA in 50 Steps",course.getName());
		assertEquals("JPA in 50 Steps",course1.getName());
	}
	
	
	

}
