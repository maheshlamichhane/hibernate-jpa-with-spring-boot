package com.lamichhane.jpawithhibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lamichhane.jpawithhibernate.entity.Course;
import com.lamichhane.jpawithhibernate.entity.Review;

@Repository
@Transactional
public class CourseRepository
{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	
	public Course findById(Long id)
	{
		return em.find(Course.class,id);
	}
	
	public void deleteById(Long id) {
		Course course=findById(id);
		em.remove(course); 
	}
	
	public Course save(Course course)
	{
		if(course.getId()==null)
		{
			em.persist(course);
		}
		else
		{
			em.merge(course);
		}
		return course;
	}
	
	public void playWithEntityManager()
	{
			Course course1=new Course("Web Services in 100 Steps");
			em.persist(course1);
			Course course2=new Course("Angular Js  in 100 Steps");
			em.persist(course2);
			em.flush();
			em.detach(course2);
			
			
			course1.setName("Web Services in 1000 Steps Updated");
			em.refresh(course1);
			em.flush();
			
			course2.setName("Angular Js in 1000 Steps Updated");
			em.flush();
			
			Course course3=findById(10003l);
			course3.setName("HQL in 100 Steps");
			em.flush();
	}
	
	public void addHardCoadedReviewsForCourse(Long courseId,List<Review> reviews)
	{
		Course course=findById(courseId);
		logger.info("course.getReviews() -> {}",course.getReviews());
		
		Course course2=new Course("Hello You");
		em.persist(course2);
		
		for(Review review:reviews)
		{
			
			//setting the relationship
			course2.addReview(review);
			review.setCourse(course2);
			
			//save ot tp tje database 
			em.persist(review);
		}
	}

}
