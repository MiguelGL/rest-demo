package com.mgl.restdemo.demo;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.mgl.restdemo.domain.Customer;
import com.mgl.restdemo.domain.SysUser;
import com.mgl.restdemo.domain.Vehicle;
import com.mgl.restdemo.db.CustomerDbController;
import com.mgl.restdemo.db.SysUserDbController;
import com.mgl.restdemo.db.VehicleDbController;
import com.mgl.restdemo.util.Util;

@Singleton
@Startup
public class Populator {

    @Inject CustomerDbController customerController;
    @Inject SysUserDbController userController;
    @Inject VehicleDbController vehicleController;

    // TODO: no exists -! not found
    // TODO: constraint fail -! 400 algo

    @PostConstruct
    public void populateDemoData() {

        // --------------------------------

        Customer c1 = new Customer()
            .name("SEUR")
            .description("FAKE SEUR transportation company")
            .apiKey(Util.md5Sum("SEUR"))
            .creationTs(new Date());

        customerController.create(c1);

        SysUser user1 = new SysUser()
            .customer(c1)
            .username("miguel")
            .email("miguel@email.com")
            .administrator(false)
            .md5Password(Util.md5Sum("miguelpass"));

        userController.create(user1);

        SysUser user2 = new SysUser()
            .customer(c1)
            .username("alberto")
            .email("alberto@email.com")
            .administrator(true)
            .md5Password(Util.md5Sum("albertopass"));

        userController.create(user2);

        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = new Vehicle()
                .customer(c1)
                .licensePlate(String.format("%s-%02d", c1.getName(), i))
                .sortIndex(i);

            vehicleController.create(vehicle);
        }

        // --------------------------------

        Customer c2 = new Customer()
            .name("MRW")
            .description("FAKE MRW transportation company")
            .apiKey(Util.md5Sum("MRW"))
            .creationTs(new Date());

        customerController.create(c2);

        SysUser user3 = new SysUser()
            .customer(c2)
            .username("foo")
            .email("foo@email.com")
            .administrator(true)
            .md5Password(Util.md5Sum("foopass"));

        userController.create(user3);

        SysUser user4 = new SysUser()
            .customer(c2)
            .username("bar")
            .email("bar@email.com")
            .administrator(false)
            .md5Password(Util.md5Sum("barpass"));

        userController.create(user4);

        SysUser user5 = new SysUser()
            .customer(c2)
            .username("acme")
            .email("acme@email.com")
            .administrator(false)
            .md5Password(Util.md5Sum("acmepass"));

        userController.create(user5);

        for (int i = 0; i < 15; i++) {
            Vehicle vehicle = new Vehicle()
                .customer(c2)
                .licensePlate(String.format("%s-%02d", c2.getName(), i))
                .sortIndex(i);

            vehicleController.create(vehicle);
        }

    }

}
