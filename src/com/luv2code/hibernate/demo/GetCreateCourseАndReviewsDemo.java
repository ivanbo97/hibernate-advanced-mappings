package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;




public class GetCreateCourse└ndReviewsDemo {

	public static void main(String[] args) {
		
		// create session factory
				SessionFactory factory = new Configuration()
										 .configure("hibernate.cfg.xml")
										 .addAnnotatedClass(Instructor.class)
										 .addAnnotatedClass(InstructorDetail.class)
										 .addAnnotatedClass(Course.class)
										 .addAnnotatedClass(Review.class)
										 .buildSessionFactory();
				
				Session session = factory.getCurrentSession();
				
				try {
					
					
					// start transaction
					session.beginTransaction();
					
					// get the course
					int courseId = 11;
					Course retrievedCourse = session.get(Course.class, courseId);
					// print the course
					System.out.println("Course: " + retrievedCourse);
					
					// print the reviews related to the course
					System.out.println("Course reviews: " + retrievedCourse.getReviews());
					
					// alternative for getting a course and reviews at one
				   /*Query<Course> query = session.createQuery("select c from Course c "
										+ "JOIN FETCH c.reviews "
										+ "where c.id=:courseId ",						
										Course.class);
					query.setParameter("courseId",courseId);
					Course retrievedCourse = query.getSingleResult();*/
						
					// commit transaction			
					session.getTransaction().commit();
					System.out.println("Done!");
					
				} finally {
					factory.close();
					session.close();
				}
				

	}

}
