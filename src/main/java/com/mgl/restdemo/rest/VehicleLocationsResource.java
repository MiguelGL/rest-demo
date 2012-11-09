package com.mgl.restdemo.rest;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.message.AuthException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.mgl.restdemo.db.CustomerDbController;
import com.mgl.restdemo.db.LocationDbController;
import com.mgl.restdemo.db.VehicleDbController;
import com.mgl.restdemo.domain.Customer;
import com.mgl.restdemo.domain.Location;
import com.mgl.restdemo.domain.Vehicle;
import lombok.extern.java.Log;

@Stateless
@Path("vehicle/{licensePlate}/locations")
@Log
public class VehicleLocationsResource {

    @Inject VehicleDbController vehicleDbController;
    @Inject CustomerDbController customerDbController;
    @Inject LocationDbController locationDbController;

    // TODO: entity missing: 404

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Location addVehicleLocation(
            @PathParam("licensePlate") String licensePlate,
            @HeaderParam("X-API-Key") @DefaultValue("noapikey") String apiKey,
            Location location)
    throws EntityNotFoundException, AuthException {
        Customer customer = customerDbController.checkAndFindByApiKey(apiKey);
        Vehicle vehicle = vehicleDbController.findByLicensePlateAndCustomer(licensePlate, customer);
        locationDbController.create(location.vehicle(vehicle));
        return location;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Location[] getVehicleLocations(
            @PathParam("licensePlate") String licensePlate,
            @HeaderParam("X-API-Key") @DefaultValue("noapikey") String apiKey,
            @QueryParam("fromUtcTs") @DefaultValue("0") long fromUtcTs,
            @QueryParam("toUtcTs") @DefaultValue("0") long toUtcTs)
    throws EntityNotFoundException, AuthException {
        Customer customer = customerDbController.checkAndFindByApiKey(apiKey);
        Vehicle vehicle = vehicleDbController.findByLicensePlateAndCustomer(licensePlate, customer);
        Date from = new Date(fromUtcTs);
        Date to = (toUtcTs == 0) ? new Date() : new Date(toUtcTs);
        log.log(Level.INFO, "from: {0}, to: {1}", new Object[] {from, to});
        List<Location> locations = locationDbController.findVehicleLocations(vehicle, from, to);
        return locations.toArray(new Location[locations.size()]);
    }

}
