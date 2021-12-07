package com.lamichhane.jpawithhibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lamichhane.jpawithhibernate.JpawithhibernateApplication;
import com.lamichhane.jpawithhibernate.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpawithhibernateApplication.class)
class NativeQueriesTest {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;


	@Test
	void native_queries_basic()
	{
		//Query query = em.createQuery("Select c From Course c");
		Query query=em.createNativeQuery("select * from course",Course.class);
		List list = query.getResultList();
		logger.info("Select * from Course -> {}",list);
	}
	
	@Test
	void native_queries_advanced()
	{
		//Query query = em.createQuery("Select c From Course c");
		Query query=em.createNativeQuery("select * from course where id=?",Course.class);
		query.setParameter(1, 10001l);
		List list = query.getResultList();
		logger.info("Select * Course where id=? -> {}",list);
	}
	//we can also put :id in the query
	//we use native queries mostly for mass updte

	
	
	
	

}
