package com.mgl.restdemo.db;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mgl.restdemo.domain.Customer;
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

    public List<SysUser> findByCustomer(Customer customer) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SysUser> cq = cb.createQuery(SysUser.class);
        Root<SysUser> from = cq.from(SysUser.class);
        return em.createQuery(cq.select(from)
                .where(cb.equal(from.get("customer"), customer)))
                .getResultList();
    }

}
