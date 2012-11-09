package com.mgl.restdemo.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.mgl.restdemo.domain.Location;
import com.mgl.restdemo.domain.Vehicle;

@Stateless
@Path("vehicle/{licensePlate}/locations")
public class VehicleLocationsResource {

    @Inject VehicleFacadeREST vehicleDbController;
    @Inject LocationFacadeREST locationDbController;

    // TODO: entity missing: 404

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Location addVehicleLocation(
            @PathParam("licensePlate") String licensePlate,
            Location location)
    throws EntityNotFoundException {
        Vehicle vehicle = vehicleDbController.findByLicensePlate(licensePlate);
        locationDbController.create(location.vehicle(vehicle));
        return location;
    }

}
