/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.utils;

import com.oltranz.ignite.beans.AngazaRequest;
import com.oltranz.ignite.beans.AngazaResponse;
import com.oltranz.ignite.beans.DLightRequest;
import com.oltranz.ignite.beans.DLightResponse;
import com.oltranz.ignite.beans.IgnitePaymentConfirmRequest;
import com.oltranz.ignite.beans.IgnitePaymentConfirmResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ismaelnzamutuma
 */
public class AngazaManager {
  public   IgnitePaymentConfirmResponse processPaymentConfirmationAngaza(IgnitePaymentConfirmRequest confirmRequest)
    {
       String  confirmurl="https://payg.angazadesign.com/hooks/oltranz_mtn_rw/receipts";
        AngazaRequest arequest = new AngazaRequest();
                arequest.setAmount(confirmRequest.getAmount());
                arequest.setCustomer(confirmRequest.getSenderId());
               arequest.setMarchant_account(confirmRequest.getPaymentRef());
               arequest.setReference_number(confirmRequest.getAccountRef());
               arequest.setTransaction_id(confirmRequest.getPaymentSPtransactionId());
               String angazajson = CommonLibrary.marshalling(arequest, AngazaRequest.class,"json");
                String angazajresponseson = new AngazaPosting().sendXML(confirmurl, angazajson);
               // Response response= CommonLibrary.sendRESTRequest(confirmurl, dlightrequestjson, MediaType.APPLICATION_JSON, "POST");
               // String dlresponsejson = response.readEntity(String.class);
                System.out.println("Response from ANGAZA: " +angazajresponseson);
                AngazaResponse aresponse = (AngazaResponse)CommonLibrary.unmarshalling(angazajresponseson,AngazaResponse.class, "json");
               IgnitePaymentConfirmResponse confirmResponse ;
               /* confirmResponse.setBalance(new Integer(0));
                confirmResponse.setSPtransactionId(aresponse.getTransaction_id());
                confirmResponse.setStatusDesc(aresponse.getDescription());
                
                confirmResponse.setStatusId(new Integer(Integer.parseInt(aresponse.getCode())));  */
        
         if(aresponse.getCode().equals("200"))
                {
                    confirmResponse = new IgnitePaymentConfirmResponse();
                    confirmResponse.setBalance(0);
                    confirmResponse.setSPtransactionId(confirmRequest.getPaymentSPtransactionId());
                    confirmResponse.setStatusDesc("SUCCESS");
                    confirmResponse.setStatusId(400);
                    
                    
                }
               else
                {
                    confirmResponse = new IgnitePaymentConfirmResponse();
                    confirmResponse.setBalance(0);
                    confirmResponse.setSPtransactionId(confirmRequest.getPaymentSPtransactionId());
                    confirmResponse.setStatusDesc("FAILED");
                    confirmResponse.setStatusId(404);
                }  
          return confirmResponse;
      
    }
    
}
