/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.entities;

import com.oltranz.ignite.beans.AngazaRequest;
import com.oltranz.ignite.beans.AngazaResponse;
import com.oltranz.ignite.beans.IgnitePaymentConfirmRequest;
import com.oltranz.ignite.utils.CommonLibrary;
import static java.lang.System.out;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author ismaelnzamutuma
 */
public class Executable {
   public static void main(String args[]) throws Exception
   {
        ObjectMapper mapper = new ObjectMapper();
      /* IgnitePaymentConfirmRequest req = new IgnitePaymentConfirmRequest();
       req.setAccountRef("123456789");
       req.setAmount(new Integer(10));
       req.setMerchantContractId("364747");
       req.setPaymentRef("789");
       req.setPaymentSPId(2);
       req.setPaymentSPtransactionId("11112222");
       req.setSenderId("250788636644");
       
       String xml = CommonLibrary.marshalling(req,IgnitePaymentConfirmRequest.class );
       System.out.println(xml);*/
       
     /*  AngazaRequest angaza = new AngazaRequest();
       angaza.setAmount(10);
       angaza.setCustomer("0788636644");
       angaza.setMarchant_account("300");
       angaza.setReference_number("1234567");
       angaza.setTransaction_id("1111111111");
       
       ObjectMapper mapper = new ObjectMapper();
      String json =mapper.writeValueAsString(angaza);
       
       System.out.println(json);*/
       AngazaResponse ar = new AngazaResponse();
       ar.setCode(400);
       ar.setCustomer("0788636644");
       ar.setDescription("bad request");
     
       ar.setReference_number(9140419);
       ar.setStatus("-");
       ar.setTransaction_id("od");
       String json =mapper.writeValueAsString(ar);
       
       System.out.println(json);
   }
   
  
 
   
}
