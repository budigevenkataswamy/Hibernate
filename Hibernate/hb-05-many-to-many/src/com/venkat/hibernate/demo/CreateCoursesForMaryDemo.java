package com.venkat.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.venkat.hibernate.demo.entity.Course;
import com.venkat.hibernate.demo.entity.Instructor;
import com.venkat.hibernate.demo.entity.InstructorDetail;
import com.venkat.hibernate.demo.entity.Review;
import com.venkat.hibernate.demo.entity.Student;

public class CreateCoursesForMaryDemo {

	public static void main(String[] args) {
		
		//create a Session factory
		SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create a session
		Session session= factory.getCurrentSession();
		
		 try {
			 session.beginTransaction();
			 
			 int studentId=2;
			 
			 Student tempStudent=session.get(Student.class, studentId);
			 
			 System.out.println("\nloaded student: "+studentId);
			 System.out.println("Course: "+tempStudent.getCourses());
			 
			
			 Course tempCourse1=new Course("Rubik's cube-how to speed cube");
			 Course tempCourse2=new Course("atari 2600 -game development");
			 
			 
			 tempCourse1.addStudent(tempStudent);
			 tempCourse2.addStudent(tempStudent);
			 
			 session.save(tempCourse1);
			 session.save(tempCourse2);
				
			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");
			 
			
		} finally {
			session.close();
			factory.close();
			
		}
	}

}
