package com.mgl.restdemo.db;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mgl.restdemo.domain.Customer;
import com.mgl.restdemo.domain.Vehicle;

@Stateless
public class VehicleDbController extends AbstractDbController<Vehicle> {

    @PersistenceContext(unitName = "RestDemo")
    private EntityManager em;

    public VehicleDbController() {
        super(Vehicle.class);
    }

    public Vehicle findByLicensePlateAndCustomer(String licensePlate, Customer customer)
    throws NoResultException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> from = cq.from(Vehicle.class);
        return em.createQuery(cq.select(from)
                .where(cb.equal(from.get("licensePlate"), licensePlate),
                       cb.equal(from.get("customer"), customer)))
                .getSingleResult();
    }

    public List<Vehicle> findByCustomer(Customer customer) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> from = cq.from(Vehicle.class);
        return em.createQuery(cq.select(from)
                .where(cb.equal(from.get("customer"), customer)))
                .getResultList();
    }

    public Vehicle findByLicensePlate(String licensePlate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> from = cq.from(Vehicle.class);
        try {
            return em.createQuery(cq.select(from)
                    .where(cb.equal(from.get("licensePlate"), licensePlate)))
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
