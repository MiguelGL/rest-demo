package com.mgl.restdemo.rest;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mgl.restdemo.db.CustomerDbController;
import com.mgl.restdemo.db.SysUserDbController;
import com.mgl.restdemo.db.VehicleDbController;
import com.mgl.restdemo.domain.Customer;
import com.mgl.restdemo.domain.SysUser;
import com.mgl.restdemo.domain.Vehicle;

@Stateless
@Path("customers")
public class CustomersResource {

    @Inject CustomerDbController customerDbController;
    @Inject VehicleDbController vehicleDbController;
    @Inject SysUserDbController userDbController;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer[] getCustomers() {
        return customerDbController.findAll().toArray(new Customer[0]);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer getCustomer(@PathParam("id") long id) {
        return customerDbController.find(id);
    }

    @GET
    @Path("{id}/vehicles")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Vehicle[] getVehicles(@PathParam("id") long id) {
        Customer customer = customerDbController.find(id);
        List<Vehicle> vehicles = vehicleDbController.findByCustomer(customer);
        return vehicles.toArray(new Vehicle[vehicles.size()]);
    }

    @GET
    @Path("{id}/users")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public SysUser[] getUsers(@PathParam("id") long id) {
        Customer customer = customerDbController.find(id);
        List<SysUser> users = userDbController.findByCustomer(customer);
        return users.toArray(new SysUser[users.size()]);
    }

}
