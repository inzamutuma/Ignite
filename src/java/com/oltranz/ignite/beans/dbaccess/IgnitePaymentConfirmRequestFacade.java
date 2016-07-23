/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.beans.dbaccess;

import com.oltranz.ignite.beans.IgnitePaymentConfirmRequest;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
