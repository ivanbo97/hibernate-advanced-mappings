package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;




public class DeleteCourseDemo {

	public static void main(String[] args) {
		
		// create session factory
				SessionFactory factory = new Configuration()
										 .configure("hibernate.cfg.xml")
										 .addAnnotatedClass(Instructor.class)
										 .addAnnotatedClass(InstructorDetail.class)
										 .addAnnotatedClass(Course.class)
										 .buildSessionFactory();
				
				Session session = factory.getCurrentSession();
				
				try {
					
					
					// start transaction
					session.beginTransaction();
					
					// get the course from the DB
					int courseId = 10;
					Course courseForDeleteCourse = session.get(Course.class, courseId);
					
					//delete the course
					System.out.println("Deleting course: " + courseForDeleteCourse);
					session.delete(courseForDeleteCourse);
					
					// commit transaction			
					session.getTransaction().commit();
					System.out.println("Done!");
					
				} finally {
					factory.close();
					session.close();
				}
				

	}

}
