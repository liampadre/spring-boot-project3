package com.example.projectweb1.demo.dao.impl;

import com.example.projectweb1.demo.dao.IEmployeeDao;
import com.example.projectweb1.demo.model.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDao implements IEmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return em.createQuery("SELECT c FROM Employee c").getResultList();
    }

    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findByStatus(String status) {
        Query query = em.createQuery("SELECT c FROM Employee c WHERE c.status = '" + status + "'");
        return query.getResultList();
    }

}
