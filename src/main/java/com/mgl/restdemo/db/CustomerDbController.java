package com.mgl.restdemo.db;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.auth.message.AuthException;
import javax.ws.rs.Path;

import com.mgl.restdemo.domain.Customer;

@Stateless
@Path("customer")
public class CustomerDbController extends AbstractDbController<Customer> {

    @PersistenceContext(unitName = "RestDemo")
    private EntityManager em;

    public CustomerDbController() {
        super(Customer.class);
    }

    public Customer findByApiKey(String apiKey) throws NoResultException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> from = cq.from(Customer.class);
        return em.createQuery(cq.select(from)
                .where(cb.equal(from.get("apiKey"), apiKey)))
                .getSingleResult();
    }

    public Customer checkAndFindByApiKey(String apiKey) throws AuthException {
        try {
            return findByApiKey(apiKey);
        } catch (NoResultException e) {
            throw new AuthException("Invalid api key: " + apiKey);
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
