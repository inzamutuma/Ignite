/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.services;

import com.oltranz.ignite.beans.AngazaResponse;
import com.oltranz.ignite.beans.BillPaymentCompletedRequest;
import com.oltranz.ignite.beans.DLightResponse;
import com.oltranz.ignite.beans.management.IgniteConfirmManager;
import com.oltranz.ignite.beans.IgnitePaymentConfirmRequest;
import com.oltranz.ignite.beans.IgnitePaymentConfirmResponse;
import com.oltranz.ignite.beans.StatusesMap;
import com.oltranz.ignite.beans.dbaccess.StatusesMapFacade;
import java.io.InputStream;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.oltranz.ignite.utils.CommonLibrary;
import com.oltranz.ignite.utils.DlightManager;
import com.oltranz.ignite.utils.AngazaManager;
import com.oltranz.ignite.utils.AngazaPosting;
import com.oltranz.ignite.utils.AzurResponse;
import com.oltranz.ignite.utils.AzurSignature;



import java.io.File;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author ismaelnzamutuma
 */
@Stateless
@Path("payment")
public class IgnitePaymentConfirmation {
    @EJB
            IgniteConfirmManager imanager;
    @EJB  StatusesMapFacade statusmapFacade;
    IgnitePaymentConfirmRequest confirmRequest;
    IgnitePaymentConfirmResponse confirmResponse ;
    
    @GET
    @Path("azurcertificate")
    public  String azurgetcertificatepath()
    {
        /*  FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext)context.getExternalContext().getContext();
        String path=servletContext.getRealPath("/");*/
        String path= System.getProperty("java.home");
        
        return path;
    }
    @GET
    @Path("correspondingstatus/{statusId}/{organization}")
    @Produces("application/json")
    public StatusesMap getmapofstatus(@PathParam("statusId")String statusId,@PathParam("organization")String organization)
    {
        return statusmapFacade.findStatusMap(statusId, organization);
    }
    
    
    
    @POST
    @Path("confirm")
    //  @Consumes({"application/xml","application/json"})
    @Produces("application/xml")
    public IgnitePaymentConfirmResponse confirmignitepayment(InputStream is)
    {
        String receivedxml=CommonLibrary.inputStream2String(is);
        System.out.println(receivedxml);
        
        // confirmRequest = new IgnitePaymentConfirmRequest();
        try
        {
            
            
            
            confirmRequest=(IgnitePaymentConfirmRequest) CommonLibrary.unmarshalling(receivedxml, IgnitePaymentConfirmRequest.class);
            IgnitePaymentConfirmRequest ipcr =imanager.getPaymentConfirmationRequest(confirmRequest);
            if(ipcr==null)
            {
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
            else
            {
            confirmResponse = new IgnitePaymentConfirmResponse();
            confirmResponse.setBalance(0);
            confirmResponse.setSPtransactionId(confirmRequest.getPaymentSPtransactionId());
            confirmResponse.setStatusDesc(ipcr.getStatus());
            confirmResponse.setStatusId(ipcr.getStatusCode()); 
            return confirmResponse;
            }
        }
        catch(Exception e)
        {
            confirmResponse = new IgnitePaymentConfirmResponse();
            confirmResponse.setBalance(0);
            confirmResponse.setSPtransactionId(confirmRequest.getPaymentSPtransactionId());
            confirmResponse.setStatusDesc("MESSAGE NOT RECOGNIZED");
            confirmResponse.setStatusId(404);
            return confirmResponse;
        }
        
        
    }
    
    
    
    class ConfirmPayment implements Runnable
    {
        
        public void run()
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
            BillPaymentCompletedRequest confirmBillPayment = new BillPaymentCompletedRequest();
            confirmBillPayment.setPaymentSPId(confirmRequest.getPaymentSPId());
            confirmBillPayment.setPaymentSPaccountId(confirmRequest.getPaymentSPaccountId());
            confirmBillPayment.setPaymentSPmerchantId(confirmRequest.getPaymentSPmerchantId());
            confirmBillPayment.setPaymentSPtransactionId(confirmRequest.getPaymentSPtransactionId());
            
            String confirmUrl ="http://10.171.1.53:8080/PaymentGateway/payments/SPInitiatedPayments/PaymentCompletedRequestHandler";
            try
            {
                System.out.println("in the run method of the interface");
                System.out.println(confirmRequest.getPaymentSPtransactionId());
                
                String accref= confirmRequest.getAccountRef().trim();
                if(accref.length()==9)
                {
                    
                    DLightResponse dresponse =  new DlightManager().processPaymentConfirmationProcessingDlight(confirmRequest);
                   StatusesMap smap =statusmapFacade.findStatusMap(""+dresponse.getCode(), "DLIGHT");
                  
                    if(dresponse.equals("200"))
                    {
                        
                   
                        confirmRequest.setStatus("SUCCESS");
                        confirmRequest.setPaidOrganization("DLIGHT");
                        confirmRequest.setStatusCode(Integer.parseInt(smap.getOltranzstatus()));
                        imanager.editTransaction(confirmRequest);
                    }
                    else
                    {
                        confirmRequest.setStatus("FAILED");
                        confirmRequest.setPaidOrganization("DLIGHT");
                        confirmRequest.setStatusCode(Integer.parseInt(smap.getOltranzstatus()));
                        imanager.editTransaction(confirmRequest);
                        
                    }
                    confirmBillPayment.setMerchantTransactionid(dresponse.getPayment_reference());
                    confirmBillPayment.setMessage(dresponse.getDescription());
                    confirmBillPayment.setNewbalance("0");
                    
                    confirmBillPayment.setRequestTime(sdf.format(new java.util.Date()));
                    //StatusesMap smap =statusmapFacade.findStatusMap(""+dresponse.getCode(), "DLIGHT");
                    
                    confirmBillPayment.setStatusCode(smap.getOltranzstatus());
                    String confirmBillPaymentxml = CommonLibrary.marshalling(confirmBillPayment, BillPaymentCompletedRequest.class);
                    System.out.println("CONFIRMATION RESPONSE FROM THE ISMAEL: "+confirmBillPaymentxml);
                    CommonLibrary.sendRESTRequest(confirmUrl, confirmBillPaymentxml, MediaType.APPLICATION_XML, "POST");
                    
                }
                
                
                else if(accref.length()==7)
                {
                    // we are dealing with angaza here
                    
                    
                    confirmResponse = new IgnitePaymentConfirmResponse();
                    AngazaResponse aresponse=  new AngazaManager().processPaymentConfirmationAngaza(confirmRequest);
                    StatusesMap smap =statusmapFacade.findStatusMap(""+aresponse.getCode(), "ANGAZA");
                    if(aresponse.getCode()==200)
                    {
                        
                        
                        confirmRequest.setStatus("SUCCESS");
                        confirmRequest.setPaidOrganization("ANGAZA");
                        confirmRequest.setPaymentRef(aresponse.getPayment_reference());
                        confirmRequest.setStatusCode(Integer.parseInt(smap.getOltranzstatus()));
                        
                        imanager.editTransaction(confirmRequest);
                        
                    }
                    else
                    {
                        confirmRequest.setStatus("FAILED");
                        confirmRequest.setPaidOrganization("ANGAZA");
                        confirmRequest.setStatusCode(Integer.parseInt(smap.getOltranzstatus()));
                        imanager.editTransaction(confirmRequest);
                        
                        confirmRequest.setStatus("FAILED");
                        confirmRequest.setPaidOrganization("ANGAZA");
                        imanager.editTransaction(confirmRequest);
                        
                    }
                    confirmBillPayment.setMerchantTransactionid(aresponse.getPayment_reference());
                    confirmBillPayment.setMessage(aresponse.getDescription());
                    confirmBillPayment.setNewbalance("0");
                    
                    confirmBillPayment.setRequestTime(sdf.format(new java.util.Date()));
                    
                    confirmBillPayment.setStatusCode(smap.getOltranzstatus());
                    String confirmBillPaymentxml = CommonLibrary.marshalling(confirmBillPayment, BillPaymentCompletedRequest.class);
                    System.out.println("CONFIRMATION RESPONSE FROM THE ISMAEL: "+confirmBillPaymentxml);
                    CommonLibrary.sendRESTRequest(confirmUrl, confirmBillPaymentxml, MediaType.APPLICATION_XML, "POST");
                    // here we shall send a successful response to a given url
                    
                    
                    
                    
                }
                else if(accref.length()==8)
                {
                    
                    System.out.println("in the azur method");
                    Map parameters = new HashMap();
                    parameters.put("amount", ""+confirmRequest.getAmount());
                    parameters.put("currency", "RWF");
                    parameters.put("partner_id", "TEST");
                    parameters.put("partner_transaction_ref",confirmRequest.getPaymentRef());
                    parameters.put("payment_result", "PAID");
                    parameters.put("provider",confirmRequest.getPaymentSPaccountId());
                    parameters.put("provider_account", confirmRequest.getAccountRef()); // it has to be 10 numbers
                    parameters.put("provider_transaction_ref",confirmRequest.getPaymentSPtransactionId());
                    parameters.put("subscriber_first_name", confirmRequest.getPaymentSPaccountId());
                    parameters.put("subscriber_last_name", confirmRequest.getPaymentSPaccountId());
                    parameters.put("subscriber_phone_number", confirmRequest.getPaymentSPaccountId());
                    //parameters.put("subscriber_phone_number","0123456789");
                    parameters.put("transaction_code", confirmRequest.getPaymentSPtransactionId());
                    Date date = new Date();
                    SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdf1;
                    sdf1 = new SimpleDateFormat("HH mm ss");
                    String today = sdf2.format(date)+"T"+sdf1.format(date);
                    today=today.replace(" ", "%3A");
                    parameters.put("transaction_timestamp", today);
                    Map<String, String> sortedParameters = new TreeMap<String, String>(parameters);
                    
                    String data = "";
                    for(String key:sortedParameters.keySet())
                    {
                        data=data+key+"="+sortedParameters.get(key)+"&";
                    }
                    data= data.substring(0,data.lastIndexOf("&"));
                    
                    //System.out.println(data);
                    String signature="";
                    
                    try {
                        
                        
                        signature =AzurSignature.calculateRFC2104HMAC(data, "AR1KZMVAC363XIFPLEQZWA8W9OA42GC6A");
                        data=data+"&signature="+signature;
                        String azurresponse=  new AngazaPosting().sendAzurConfirmation("https://qa-api.azuri-technologies.com/epayments/v1/paymentresult", data, "POST");
                        
                        System.out.println(azurresponse);
                        ObjectMapper mapper = new ObjectMapper();
                        AzurResponse aresponse = new AzurResponse();
                        aresponse=(AzurResponse)mapper.readValue(azurresponse, AzurResponse.class);
                        System.out.println(aresponse.getMessage());
                         StatusesMap smap =statusmapFacade.findStatusMap(""+aresponse.getStatus(), "AZUR");
                   
                        
                        if(aresponse.getStatus()==200)
                        {
                    confirmRequest.setStatus("SUCCESS");
                        confirmRequest.setPaidOrganization("AZUR");
                        confirmRequest.setPaymentRef(aresponse.getPayment_id());
                        confirmRequest.setStatusCode(Integer.parseInt(smap.getOltranzstatus()));
                        
                        imanager.editTransaction(confirmRequest);  
                        }
                        else
                        {
                           confirmRequest.setStatus("FAILED");
                        confirmRequest.setPaidOrganization("AZUR");
                        confirmRequest.setPaymentRef(aresponse.getPayment_id());
                        confirmRequest.setStatusCode(Integer.parseInt(smap.getOltranzstatus()));
                        
                        imanager.editTransaction(confirmRequest);   
                        }
                        
                      confirmBillPayment.setMerchantTransactionid(aresponse.getPayment_id());
                    confirmBillPayment.setMessage(aresponse.getInfo().getSubscriber_message());
                    confirmBillPayment.setNewbalance("0");
                    
                    confirmBillPayment.setRequestTime(sdf.format(new java.util.Date()));
                    
                    confirmBillPayment.setStatusCode(smap.getOltranzstatus());
                    String confirmBillPaymentxml = CommonLibrary.marshalling(confirmBillPayment, BillPaymentCompletedRequest.class);
                    System.out.println("CONFIRMATION RESPONSE FROM THE ISMAEL: "+confirmBillPaymentxml);
                    CommonLibrary.sendRESTRequest(confirmUrl, confirmBillPaymentxml, MediaType.APPLICATION_XML, "POST");
                   
                        
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    
                }
                else   /// if the accref length is not 9 or 7 or 10
                {
                    confirmBillPayment.setMerchantTransactionid(confirmRequest.getMarchantTransactionId());
                    confirmBillPayment.setMessage("The account reference does not meet the requirements");
                    confirmBillPayment.setNewbalance("0");
                    
                    confirmBillPayment.setRequestTime(sdf.format(new java.util.Date()));
                    //  StatusesMap smap = statusmapFacade.findStatusMap(""+aresponse.getCode(), "ANGAZA");
                    
                    confirmBillPayment.setStatusCode("402");
                    String confirmBillPaymentxml = CommonLibrary.marshalling(confirmBillPayment, BillPaymentCompletedRequest.class);
                    System.out.println("CONFIRMATION RESPONSE TO THE PAYMENT GATEWAY: "+confirmBillPaymentxml);
                    CommonLibrary.sendRESTRequest(confirmUrl, confirmBillPaymentxml, MediaType.APPLICATION_XML, "POST");
                    
                    
                    //// other bill payments companies shall be added here
                }
                
                
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            
            
        }
        
        
    }
    
    
    
    
    
    
}
