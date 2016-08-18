/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.beans.dbaccess;

import com.oltranz.ignite.beans.IgnitePaymentConfirmRequest;
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
public class IgnitePaymentConfirmRequestFacade extends AbstractFacade<IgnitePaymentConfirmRequest> {
    @PersistenceContext(unitName = "BillPaymentPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IgnitePaymentConfirmRequestFacade() {
        super(IgnitePaymentConfirmRequest.class);
    }
    
    public void createTransaction(IgnitePaymentConfirmRequest ire)
    {
        super.create(ire);
    }
    public void editTransaction(IgnitePaymentConfirmRequest ire)
    {
        super.edit(ire);
    }
    
    public IgnitePaymentConfirmRequest findPaymentConfirm(int spId, String sptransactionid)
    {
        Query q = em.createQuery("select pc from IgnitePaymentConfirmRequest pc where pc.paymentSPId=:spId and pc.paymentSPtransactionId=:sptransactionid");
        q.setParameter("spId", spId);
        q.setParameter("sptransactionid",sptransactionid);
        List<IgnitePaymentConfirmRequest> list = q.getResultList();
        if(list.size()>0)
        {
            return list.get(0);
        }
        else
        {
            return null;
        }
        
    }
}
