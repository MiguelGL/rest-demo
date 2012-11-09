package com.mgl.restdemo.demo;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.mgl.restdemo.domain.Customer;
import com.mgl.restdemo.rest.CustomerFacadeREST;

@Singleton
@Startup
public class Populator {

    @Inject CustomerFacadeREST customerController;

    @PostConstruct
    public void populateDemoData() {
        Customer c1 = new Customer();
        c1.setName("SEUR");
        c1.setDescription("FAKE SEUR transportation company");
        c1.setCreationTs(new Date());

        customerController.create(c1);
    }

}
