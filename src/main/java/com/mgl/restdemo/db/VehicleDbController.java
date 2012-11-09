package com.mgl.restdemo.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mgl.restdemo.domain.Vehicle;

@Stateless
@Path("com.mgl.restdemo.domain.vehicle")
public class VehicleDbController extends AbstractDbController<Vehicle> {
    @PersistenceContext(unitName = "RestDemo")
    private EntityManager em;

    public VehicleDbController() {
        super(Vehicle.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Vehicle entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Vehicle entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Vehicle find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Vehicle> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Vehicle> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
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
