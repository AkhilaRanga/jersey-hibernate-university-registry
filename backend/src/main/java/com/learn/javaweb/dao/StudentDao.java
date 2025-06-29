package com.learn.javaweb.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.learn.javaweb.model.Student;
import com.learn.javaweb.util.HibernateUtils;

public class StudentDao {
    private final SessionFactory sessionFactory;

    public StudentDao() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }
    
    public void save(Student student) {
        try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();
	        session.persist(student);
	        session.getTransaction().commit();
	        session.close();
        }
    }

    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
	        List<Student> studentsList = session.createQuery("FROM Student", Student.class).list();
	        session.close();
	        return studentsList;
        }
    }

    public Student findById(int studentId) {
        try (Session session = sessionFactory.openSession()) {
        	Student student = session.createQuery("FROM Student WHERE studentId = :studentId", Student.class)
	                .setParameter("studentId", studentId)
	                .getSingleResult();
	        session.close();
	        return student;
        } catch (NoResultException e) {
            // Service class will handle this
            return null;
        }
    }

    public Student findByCode(String rollNumber) {
        try (Session session = sessionFactory.openSession()) {
        	Student student = session.createQuery("FROM Student WHERE rollNumber = :rollNumber", Student.class)
	                .setParameter("rollNumber", rollNumber)
	                .getSingleResult();
	        session.close();
	        return student;
        } catch (NoResultException e) {
            // Service class will handle this
            return null;
        }
    }
    
    public void update(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
	        session.close();
        }
    }
    
    public void deleteById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
            }
            session.getTransaction().commit();
	        session.close();
        }
    }
}
