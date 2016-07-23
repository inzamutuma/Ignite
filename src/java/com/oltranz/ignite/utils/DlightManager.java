/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.utils;

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
public class DlightManager {
    public IgnitePaymentConfirmResponse processPaymentConfirmationProcessingDlight(IgnitePaymentConfirmRequest confirmRequest)
    {
       String  confirmurl="http://52.11.206.252:8081/CSPortalToV2/Mtn/payment";
                DLightRequest dlrequest = new DLightRequest();
                dlrequest.setAmount(confirmRequest.getAmount());
                dlrequest.setCustomer(confirmRequest.getSenderId());
                dlrequest.setDescription("");
                dlrequest.setReference_number(confirmRequest.getAccountRef());
                dlrequest.setTransactionid(confirmRequest.getPaymentSPtransactionId());
                dlrequest.setPayment_method("MOMO");
                String dlightrequestjson = CommonLibrary.marshalling(dlrequest, DLightRequest.class, "json");
                Response response= CommonLibrary.sendRESTRequest(confirmurl, dlightrequestjson, MediaType.APPLICATION_JSON, "POST");
                String dlresponsejson = response.readEntity(String.class);
                System.out.println("Response from DLight: " +dlresponsejson);
                DLightResponse dlresponse = (DLightResponse)CommonLibrary.unmarshalling(dlresponsejson,DLightResponse.class, "json");
                IgnitePaymentConfirmResponse confirmResponse = new IgnitePaymentConfirmResponse();
                confirmResponse.setBalance(new Integer(0));
                confirmResponse.setSPtransactionId(dlresponse.getTransacitonId());
                confirmResponse.setStatusDesc(dlresponse.getDescription());
                
                confirmResponse.setStatusId(new Integer(Integer.parseInt(dlresponse.getCode())));  
                
          if(dlresponse.getCode().equals("200"))
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
