/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.services;

import com.oltranz.ignite.beans.management.IgniteConfirmManager;
import com.oltranz.ignite.beans.IgnitePaymentConfirmRequest;
import com.oltranz.ignite.beans.IgnitePaymentConfirmResponse;
import java.io.InputStream;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.oltranz.ignite.utils.CommonLibrary;
import com.oltranz.ignite.utils.DlightManager;
import com.oltranz.ignite.utils.AngazaManager;
import javax.ejb.EJB;
import javax.ws.rs.Produces;

/**
 *
 * @author ismaelnzamutuma
 */
@Stateless
@Path("payment")
public class IgnitePaymentConfirmation {
    @EJB
            IgniteConfirmManager imanager;
    IgnitePaymentConfirmRequest confirmRequest;
    IgnitePaymentConfirmResponse confirmResponse ;
    
    @POST
    @Path("confirm")
    @Consumes({"application/xml","application/json"})
    @Produces("application/xml")
    public IgnitePaymentConfirmResponse confirmignitepayment(InputStream is)
    {
        
        
        try
        {
            String receivedxml=CommonLibrary.inputStream2String(is);
            confirmRequest=(IgnitePaymentConfirmRequest) CommonLibrary.unmarshalling(receivedxml, IgnitePaymentConfirmRequest.class);
            
            confirmRequest.setStatus("PENDING");
            confirmRequest.setTransactionDate(new java.util.Date());
            imanager.createTransaction(confirmRequest);
            
            confirmResponse = new IgnitePaymentConfirmResponse();
            confirmResponse.setBalance(0);
            confirmResponse.setSPtransactionId(confirmRequest.getPaymentSPtransactionId());
            confirmResponse.setStatusDesc("PENDING");
            confirmResponse.setStatusId(401);
            
            Thread t= new Thread(new ConfirmPayment());
            t.start();
            
            
            return confirmResponse;
        }
        catch(Exception e)
        {
            confirmResponse = new IgnitePaymentConfirmResponse();
            confirmResponse.setBalance(0);
            confirmResponse.setSPtransactionId(confirmRequest.getPaymentSPtransactionId());
            confirmResponse.setStatusDesc("MESSAGE NOT RECOGNIZED");
            confirmResponse.setStatusId(402);
            return confirmResponse;
        }
        
        
    }
    
    class ConfirmPayment implements Runnable
    {
        
        public void run()
        {
            
           System.out.println("in the run method of the interface");
            
            String accref= confirmRequest.getAccountRef().trim();
            if(accref.length()==9)
            {
                confirmResponse = new IgnitePaymentConfirmResponse();
             confirmResponse=  new DlightManager().processPaymentConfirmationProcessingDlight(confirmRequest);
            if(confirmResponse.getStatusId()==400)
            {
                confirmRequest.setStatus("SUCCESS");
                confirmRequest.setPaidOrganization("DLIGHT");
                imanager.editTransaction(confirmRequest);
              // here we shall send a successful response to a given url
            }
            else
            {
                confirmRequest.setStatus("FAILED");
                confirmRequest.setPaidOrganization("DLIGHT");
                imanager.editTransaction(confirmRequest);
                 
             // we shall send a failed response to a given url
            }
             
            }
            
            
            else if(accref.length()==7)
            {
         // we are dealing with angaza here
                
            confirmResponse = new IgnitePaymentConfirmResponse();
             confirmResponse=  new AngazaManager().processPaymentConfirmationAngaza(confirmRequest);
            if(confirmResponse.getStatusId()==400)
            {
                confirmRequest.setStatus("SUCCESS");
                confirmRequest.setPaidOrganization("ANGAZA");
                imanager.editTransaction(confirmRequest);
              // here we shall send a successful response to a given url
            }
            else
            {
                confirmRequest.setStatus("FAILED");
                confirmRequest.setPaidOrganization("ANGAZA");
                
                imanager.editTransaction(confirmRequest);
                 
             // we shall send a failed response to a given url
            }
            //// other bill payments companies shall be added here
                

     
                
            }
            
            
        }
    }
    
    
    
    
}
