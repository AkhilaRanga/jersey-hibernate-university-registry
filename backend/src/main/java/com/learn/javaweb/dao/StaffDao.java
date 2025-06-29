package com.learn.javaweb.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.learn.javaweb.model.Staff;
import com.learn.javaweb.util.HibernateUtils;

public class StaffDao {
    private final SessionFactory sessionFactory;

    public StaffDao() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }
    
    public void save(Staff staff) {
        try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();
	        session.persist(staff);
	        session.getTransaction().commit();
	        session.close();
        }
    }

    public List<Staff> findAll() {
        try (Session session = sessionFactory.openSession()) {
	        List<Staff> staffList = session.createQuery("FROM Staff", Staff.class).list();
	        session.close();
	        return staffList;
        }
    }

    public Staff findById(int staffId) {
        try (Session session = sessionFactory.openSession()) {
        	Staff staff = session.createQuery("FROM Staff WHERE staffId = :staffId", Staff.class)
	                .setParameter("staffId", staffId)
	                .getSingleResult();
	        session.close();
	        return staff;
        } catch (NoResultException e) {
            // Service class will handle this
            return null;
        }
    }

    public Staff findByCode(String staffCode) {
        try (Session session = sessionFactory.openSession()) {
        	Staff staff = session.createQuery("FROM Staff WHERE staffCode = :staffCode", Staff.class)
	                .setParameter("staffCode", staffCode)
	                .getSingleResult();
	        session.close();
	        return staff;
        } catch (NoResultException e) {
            // Service class will handle this
            return null;
        }
    }
    
    public void update(Staff staff) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(staff);
            session.getTransaction().commit();
	        session.close();
        }
    }
    
    public void deleteById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Staff staff = session.get(Staff.class, id);
            if (staff != null) {
                session.remove(staff);
            }
            session.getTransaction().commit();
	        session.close();
        }
    }
}
