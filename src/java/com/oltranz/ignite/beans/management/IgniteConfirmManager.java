package com.oltranz.ignite.beans.management;


import com.oltranz.ignite.beans.IgnitePaymentConfirmRequest;
import com.oltranz.ignite.beans.dbaccess.IgnitePaymentConfirmRequestFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ismaelnzamutuma
 */
@Stateless

public class IgniteConfirmManager {
    @EJB
    IgnitePaymentConfirmRequestFacade igniteFacade;
    public void createTransaction(IgnitePaymentConfirmRequest ire)
    {
        igniteFacade.createTransaction(ire);
    }
    public void editTransaction(IgnitePaymentConfirmRequest ire)
    {
        igniteFacade.editTransaction(ire);
    }
    
    public IgnitePaymentConfirmRequest getPaymentConfirmationRequest(IgnitePaymentConfirmRequest ire)
    {
       return igniteFacade.findPaymentConfirm(ire.getPaymentSPId(), ire.getPaymentSPtransactionId());
    }
}
