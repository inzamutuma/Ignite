/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.utils;

import com.oltranz.ignite.beans.AngazaRequest;
import com.oltranz.ignite.beans.AngazaResponse;
import com.oltranz.ignite.beans.IgnitePaymentConfirmRequest;
import com.oltranz.ignite.beans.IgnitePaymentConfirmResponse;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ismaelnzamutuma
 */
public class AngazaManager {
  public   AngazaResponse  processPaymentConfirmationAngaza(IgnitePaymentConfirmRequest confirmRequest)
    {
       String  confirmurl="https://payg.angazadesign.com/hooks/oltranz_mtn_rw/receipts";
        AngazaRequest arequest = new AngazaRequest();
                arequest.setAmount(confirmRequest.getAmount());
                arequest.setCustomer(confirmRequest.getPaymentSPaccountId());
               arequest.setMarchant_account(confirmRequest.getPaymentSPmerchantId());
                //arequest.setMarchant_account("300");
               arequest.setReference_number(confirmRequest.getPaymentRef());
               arequest.setTransaction_id(confirmRequest.getPaymentSPtransactionId());
               String angazajson = CommonLibrary.marshalling(arequest, AngazaRequest.class,"json");
               System.out.println("DATA TO SEND TO ANGAZA WEBSITE: "+angazajson);
                String angazajresponseson =CommonLibrary.sendRESTRequestangaza(confirmurl, angazajson, MediaType.APPLICATION_JSON, "POST").readEntity(String.class);
                      //  new AngazaPosting().sendXML(confirmurl, angazajson);
               // Response response= CommonLibrary.sendRESTRequest(confirmurl, dlightrequestjson, MediaType.APPLICATION_JSON, "POST");
               // String dlresponsejson = response.readEntity(String.class);
                System.out.println("Response from ANGAZA: " +angazajresponseson);
                AngazaResponse aresponse = new AngazaResponse();
                try
                {
                 aresponse = (AngazaResponse)CommonLibrary.unmarshalling(angazajresponseson,AngazaResponse.class, "json");
                 //aresponse.getCode()
                }
                catch(Exception e)
                {
                    System.out.println("Error" +e.getMessage());
                }
          return aresponse;
      
    }
    
}
