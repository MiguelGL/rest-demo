package com.mgl.restdemo.db;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mgl.restdemo.domain.SysUser;

@Stateless
public class SysUserDbController extends AbstractDbController<SysUser> {
    
    @PersistenceContext(unitName = "RestDemo")
    private EntityManager em;

    public SysUserDbController() {
        super(SysUser.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
