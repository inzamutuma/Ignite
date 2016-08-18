/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.beans.dbaccess;

import com.oltranz.ignite.beans.StatusesMap;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ismaelnzamutuma
 */
@Stateless
public class StatusesMapFacade extends AbstractFacade<StatusesMap> {
    @PersistenceContext(unitName = "BillPaymentPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusesMapFacade() {
        super(StatusesMap.class);
    }
    public StatusesMap findStatusMap(String statusId,String organization)
    {
        Query q = em.createQuery("Select m from StatusesMap m where m.ignitestatusId=:statusId and trim(upper(m.organization))=:organization");
        q.setParameter("statusId", statusId);
        q.setParameter("organization", organization);
        List<StatusesMap> list = new ArrayList();
        list = q.getResultList();
        if(list.size()>0)
        {
            return list.get(0);
        }
        return new StatusesMap();
    }
    
}
