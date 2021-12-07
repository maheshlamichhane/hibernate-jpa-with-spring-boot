package com.lamichhane.jpawithhibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lamichhane.jpawithhibernate.JpawithhibernateApplication;
import com.lamichhane.jpawithhibernate.entity.Course;
import com.lamichhane.jpawithhibernate.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpawithhibernateApplication.class)
class JPQLTest {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;


	@Test
	void jpql_basic()
	{
		//Query query = em.createQuery("Select c From Course c");
		Query query=em.createNamedQuery("query_get_all_courses");
		List list = query.getResultList();
		logger.info("Select c From Course c -> {}",list);
	}
	
	@Test
	void jpql_typed()
	{
		//TypedQuery<Course> query=em.createQuery("Select c From Course c",Course.class);
		TypedQuery<Course> query=em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> list = query.getResultList();
		logger.info("Select c From Course c -> {}",list);
	}
	
	@Test
	void jpql_where()
	{
		//TypedQuery<Course> query=em.createQuery("Select c From Course c where name like '%100 Steps'",Course.class);
		TypedQuery<Course> query=em.createNamedQuery("query_get_100_Step_courses", Course.class);
		List<Course> list = query.getResultList();
		logger.info("Select c From Course c -> {}",list);
	}
	
	@Test
	void jpql_courses_without_students()
	{
		//TypedQuery<Course> query=em.createQuery("Select c From Course c where name like '%100 Steps'",Course.class);
		TypedQuery<Course> query=em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> list = query.getResultList();
		logger.info("Results -> {}",list);
	}
	
	@Test
	void jpql_courses_with_atleast_2_students()
	{
		//TypedQuery<Course> query=em.createQuery("Select c From Course c where name like '%100 Steps'",Course.class);
		TypedQuery<Course> query=em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> list = query.getResultList();
		logger.info("Results  -> {}",list);
	}
	
	@Test
	void jpql_courses_with_ordered_by_2_students()
	{
		//TypedQuery<Course> query=em.createQuery("Select c From Course c where name like '%100 Steps'",Course.class);
		TypedQuery<Course> query=em.createQuery("Select c from Course c order by  size(c.students) desc", Course.class);
		List<Course> list = query.getResultList();
		logger.info("Results  -> {}",list);
	}
	
	@Test
	void jpql_students_courses_ordered_by_students()
	{
		//TypedQuery<Course> query=em.createQuery("Select c From Course c where name like '%100 Steps'",Course.class);
		TypedQuery<Student> query=em.createQuery("Select s from Student s where s.passport.number like '%1234%'",Student.class);
		List<Student> list = query.getResultList();
		logger.info("Results  -> {}",list);
		
		//like
		//BETWEEN 100 and 1000
		//IS NULL
		//UPPER,LOWER,TRIM,LENGTH
	}
	
	//JOIN => Select c,s from Course c JOIN c.students s
	//LEFT JOIN => Select c,s from Course c LEFT JOIN c.students s
	//CROSS JOIN => Selct c,s from Course c,Students s
	
	@Test
	public void join()
	{
		Query query=em.createQuery("Select c,s from Course c JOIN c.students s");
		List<Object[]> resultList=query.getResultList();
		logger.info("Results size -> {}",resultList.size());
		for(Object[] result:resultList)
		{
			logger.info("Course {} Student{}",result[0],result[1]);
		}
	}
	
	@Test
	public void left_join()
	{
		Query query=em.createQuery("Select c,s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList=query.getResultList();
		logger.info("Results size -> {}",resultList.size());
		for(Object[] result:resultList)
		{
			logger.info("Course {} Student{}",result[0],result[1]);
		}
	}
	
	@Test
	public void cross_join()
	{
		Query query=em.createQuery("Select c,s from Course c,Student s");
		List<Object[]> resultList=query.getResultList();
		logger.info("Results size -> {}",resultList.size());
		for(Object[] result:resultList)
		{
			logger.info("Course {} Student{}",result[0],result[1]);
		}
	}
	
	@Test
	public void criteria_query()
	{
		//1. User Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq=cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot=cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder
		//4. Add Predicated etc to the Criteria Query
		//5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query=em.createQuery(cq.select(courseRoot));
		List<Course> resultList=query.getResultList();
		logger.info("Typed Query -> {}",resultList);
	}
	
	@Test
	public void criteria_query_having_100Steps_with_criteriaquery()
	{
		//Select c From Course c where name like '%100 Steps' "
		
		
		//1. User Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq=cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot=cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder
	    Predicate like=cb.like(courseRoot.get("name"), "%50 Steps");
	    
	    
		//4. Add Predicated etc to the Criteria Query
	    cq.where(like);
	    
		//5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query=em.createQuery(cq.select(courseRoot));
		List<Course> resultList=query.getResultList();
		logger.info("Typed Query -> {}",resultList);
	}
	
	@Test
	public void all_courses_without_students_with_criteriaquery()
	{
		//Select c From Course c where name like '%100 Steps' "
		
		
		//1. User Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq=cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot=cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder
	    Predicate studentsEmpty=cb.isEmpty(courseRoot.get("students"));
	    
	    
		//4. Add Predicated etc to the Criteria Query
	    cq.where(studentsEmpty);
	    
		//5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query=em.createQuery(cq.select(courseRoot));
		List<Course> resultList=query.getResultList();
		logger.info("Typed Query -> {}",resultList);
	}
	
	@Test
	public void join_with_criteria_query()
	{
		//Select c From Course c where name like '%100 Steps' "
		
		
		//1. User Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq=cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot=cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder
	    Join<Object,Object> join=courseRoot.join("students");
	    
	    

	    
		//5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query=em.createQuery(cq.select(courseRoot));
		List<Course> resultList=query.getResultList();
		logger.info("Typed Query -> {}",resultList);
	}
	
	
	
	
	
	
	

}
