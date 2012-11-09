package com.mgl.restdemo.db;


import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mgl.restdemo.domain.Location;
import com.mgl.restdemo.domain.Vehicle;

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

    public List<Location> findVehicleLocations(Vehicle vehicle, Date fromTs, Date toTs) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Location> cq = cb.createQuery(Location.class);
        Root<Location> from = cq.from(Location.class);
        return em.createQuery(cq.select(from)
                .where(cb.equal(from.get("vehicle"), vehicle),
                       cb.between(from.<Date>get("ts"), fromTs, toTs)))
                .getResultList();
    }

}
