/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.beans;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ismaelnzamutuma
 */
@XmlRootElement(name="COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillPaymentCompletedRequest implements Serializable{
    @XmlElement(name = "merchanttransactionid")
    private String merchantTransactionid;
    
    @XmlElement(name="paymentSPId")
    private Integer paymentSPId;
    @XmlElement(name="paymentSPtransactionId")
    private String paymentSPtransactionId;
    @XmlElement(name="paymentSPaccountId")
    private String paymentSPaccountId;
    @XmlElement(name="paymentSPmerchantId")
    private String paymentSPmerchantId;
    
    @XmlElement(name = "requestTime")
    private String requestTime;
    
    @XmlElement(name = "newbalance")
    private String newbalance;
    
    @XmlElement(name = "statuscode")
    private String statusCode;
    
    @XmlElement(name = "message")
    private String message;
    /**
     * @return the merchantTransactionid
     */
    public String getMerchantTransactionid() {
        return merchantTransactionid;
    }

    /**
     * @param merchantTransactionid the merchantTransactionid to set
     */
    public void setMerchantTransactionid(String merchantTransactionid) {
        this.merchantTransactionid = merchantTransactionid;
    }

    /**
     * @return the paymentSPId
     */
    public Integer getPaymentSPId() {
        return paymentSPId;
    }

    /**
     * @param paymentSPId the paymentSPId to set
     */
    public void setPaymentSPId(Integer paymentSPId) {
        this.paymentSPId = paymentSPId;
    }

    /**
     * @return the paymentSPtransactionId
     */
    public String getPaymentSPtransactionId() {
        return paymentSPtransactionId;
    }

    /**
     * @param paymentSPtransactionId the paymentSPtransactionId to set
     */
    public void setPaymentSPtransactionId(String paymentSPtransactionId) {
        this.paymentSPtransactionId = paymentSPtransactionId;
    }

    /**
     * @return the paymentSPaccountId
     */
    public String getPaymentSPaccountId() {
        return paymentSPaccountId;
    }

    /**
     * @param paymentSPaccountId the paymentSPaccountId to set
     */
    public void setPaymentSPaccountId(String paymentSPaccountId) {
        this.paymentSPaccountId = paymentSPaccountId;
    }

    /**
     * @return the paymentSPmerchantId
     */
    public String getPaymentSPmerchantId() {
        return paymentSPmerchantId;
    }

    /**
     * @param paymentSPmerchantId the paymentSPmerchantId to set
     */
    public void setPaymentSPmerchantId(String paymentSPmerchantId) {
        this.paymentSPmerchantId = paymentSPmerchantId;
    }

    /**
     * @return the requestTime
     */
    public String getRequestTime() {
        return requestTime;
    }

    /**
     * @param requestTime the requestTime to set
     */
    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * @return the newbalance
     */
    public String getNewbalance() {
        return newbalance;
    }

    /**
     * @param newbalance the newbalance to set
     */
    public void setNewbalance(String newbalance) {
        this.newbalance = newbalance;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
}
