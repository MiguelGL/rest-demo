package com.mgl.restdemo.db;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mgl.restdemo.domain.Location;

@Stateless
public class LocationDbController extends AbstractDbController<Location> {

    @PersistenceContext(unitName = "RestDemo")
    private EntityManager em;

    public LocationDbController() {
        super(Location.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
