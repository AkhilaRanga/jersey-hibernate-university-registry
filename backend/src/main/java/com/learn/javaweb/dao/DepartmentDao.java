package com.learn.javaweb.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.learn.javaweb.model.Department;
import com.learn.javaweb.util.HibernateUtils;

public class DepartmentDao {
    private final SessionFactory sessionFactory;

    public DepartmentDao() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }
    
    public void save(Department department) {
        try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();
	        session.persist(department);
	        session.getTransaction().commit();
	        session.close();
        }
    }

    public List<Department> findAll() {
        try (Session session = sessionFactory.openSession()) {
	        List<Department> departmentsList = session.createQuery("FROM Department", Department.class).list();
	        session.close();
	        return departmentsList;
        }
    }

    public Department findById(int departmentId) {
        try (Session session = sessionFactory.openSession()) {
	        Department department = session.createQuery("FROM Department WHERE departmentId = :departmentId", Department.class)
	                .setParameter("departmentId", departmentId)
	                .getSingleResult();
	        session.close();
	        return department;
        } catch (NoResultException e) {
            // Service class will handle this
            return null;
        }
    }

    public Department findByCode(String departmentCode) {
        try (Session session = sessionFactory.openSession()) {
	        Department department = session.createQuery("FROM Department WHERE departmentCode = :departmentCode", Department.class)
	                .setParameter("departmentCode", departmentCode)
	                .getSingleResult();
	        session.close();
	        return department;
        } catch (NoResultException e) {
            // Service class will handle this
            return null;
        }
    }
    
    public void update(Department department) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
	        session.close();
        }
    }
    
    public void deleteById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Department department = session.get(Department.class, id);
            if (department != null) {
                session.remove(department);
            }
            session.getTransaction().commit();
	        session.close();
        }
    }
}
