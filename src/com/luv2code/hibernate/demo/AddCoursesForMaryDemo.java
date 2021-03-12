package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;




public class AddCoursesForMaryDemo {

	public static void main(String[] args) {
		
		// create session factory
				SessionFactory factory = new Configuration()
										 .configure("hibernate.cfg.xml")
										 .addAnnotatedClass(Instructor.class)
										 .addAnnotatedClass(InstructorDetail.class)
										 .addAnnotatedClass(Course.class)
										 .addAnnotatedClass(Review.class)
										 .addAnnotatedClass(Student.class)
										 .buildSessionFactory();
				
				Session session = factory.getCurrentSession();
				
				try {
					
					
					// start transaction
					session.beginTransaction();
					
					// get student Mary from DB
					int studentId = 8;
					Student student = session.get(Student.class, studentId);
					
					System.out.println("Loaded student: " + student);
					System.out.println("Courses: " + student.getCourses());
					
					// create more courses
					Course course1 = new Course("Rubik's Cube - How to Speed Cube");
					Course course2 = new Course("Atari 2600 - Game Development");
					
					//add Mary to those courses
					course1.addStudent(student);
					course2.addStudent(student);
					
					// save course
					System.out.println("\nSaving courses...");
					session.save(course1);
					session.save(course2);
					
					// commit transaction			
					session.getTransaction().commit();
					System.out.println("Done!");
					
				} finally {
					factory.close();
					session.close();
				}
				

	}

}
