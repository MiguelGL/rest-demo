package com.mgl.restdemo.db;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mgl.restdemo.domain.Vehicle;

@Stateless
public class VehicleDbController extends AbstractDbController<Vehicle> {

    @PersistenceContext(unitName = "RestDemo")
    private EntityManager em;

    public VehicleDbController() {
        super(Vehicle.class);
    }

    public Vehicle findByLicensePlate(String licensePlate) throws NoResultException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> from = cq.from(Vehicle.class);
        return em.createQuery(cq.select(from)
                .where(cb.equal(from.get("licensePlate"), licensePlate)))
                .getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
