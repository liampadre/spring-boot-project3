package com.example.projectweb1.demo.dao.impl;

import com.example.projectweb1.demo.dao.IClientDao;
import com.example.projectweb1.demo.model.entity.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ClientDao implements IClientDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return em.createQuery("SELECT c FROM Client c").getResultList();
    }

    public Client findById(Long id) {
        return em.find(Client.class, id);
    }

    public List<Client> findByStatus(String status) {
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.status = '" + status + "'");
        return query.getResultList();
    }

}
