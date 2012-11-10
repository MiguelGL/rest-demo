package com.mgl.restdemo.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mgl.restdemo.db.VehicleDbController;
import com.mgl.restdemo.domain.Vehicle;

@Stateless
@Path("vehicles")
public class VehiclesResource {

    @Inject VehicleDbController vehicleDbController;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Vehicle getCustomer(@PathParam("id") long id) {
        return vehicleDbController.find(id);
    }

    @POST
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Vehicle updateVehicle(@PathParam("id") long id, Vehicle vehicle) {
        Vehicle exists = vehicleDbController.find(id);
        vehicle = vehicleDbController.edit(vehicle.id(exists.getId()));
        return vehicle;
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Vehicle createVehicle(Vehicle vehicle) throws EntityExistsException {
        if (vehicleDbController.findByLicensePlate(vehicle.getLicensePlate()) == null) {
            vehicleDbController.create(vehicle);
            return vehicle;
        } else {
            throw new EntityExistsException();
        }
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteVehicle(@PathParam("id") long id) {
        vehicleDbController.remove(vehicleDbController.find(id));
        return Response.ok().build();
    }

}
