package com.venkat.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.venkat.hibernate.demo.entity.Course;
import com.venkat.hibernate.demo.entity.Instructor;
import com.venkat.hibernate.demo.entity.InstructorDetail;
import com.venkat.hibernate.demo.entity.Review;
import com.venkat.hibernate.demo.entity.Student;

public class GetCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		//create a Session factory
		SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		//create a session
		Session session= factory.getCurrentSession();
		
		 try {
			 //start transaction
			 session.beginTransaction();
			 
			 int theId=10;
			 Course tempCourse=session.get(Course.class, theId);
			 System.out.println(tempCourse);
			 
			 System.out.println(tempCourse.getReviews());
			 
			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");
			 
			
		} finally {
			session.close();
			factory.close();
			
		}
	}

}
