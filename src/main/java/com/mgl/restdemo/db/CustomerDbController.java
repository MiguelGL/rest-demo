package com.mgl.restdemo.db;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
