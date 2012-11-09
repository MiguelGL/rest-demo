package com.mgl.restdemo.demo;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.mgl.restdemo.domain.Customer;
import com.mgl.restdemo.domain.SysUser;
import com.mgl.restdemo.rest.CustomerFacadeREST;
import com.mgl.restdemo.rest.SysUserFacadeREST;
import com.mgl.restdemo.util.Util;

@Singleton
@Startup
public class Populator {

    @Inject CustomerFacadeREST customerController;
    @Inject SysUserFacadeREST userController;

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
            .md5Password(Util.md5Sum("miguelpass"));

        userController.create(user1);

        SysUser user2 = new SysUser()
            .customer(c1)
            .username("alberto")
            .email("alberto@email.com")
            .md5Password(Util.md5Sum("albertopass"));

        userController.create(user2);
    }

}
