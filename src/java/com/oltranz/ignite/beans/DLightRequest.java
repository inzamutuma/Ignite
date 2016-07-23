/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.beans;

import java.io.Serializable;

/**
 *
 * @author ismaelnzamutuma
 */
public class DLightRequest implements Serializable {
 /*   "transactionid": "TT12345",
 "description": "description of payment",
 "reference_number": "123456789",
 "customer": "123456789",
 "amount": 100,
 "payment_method": "SMSPayment"*/
    
    private String transactionid;
    private String description;
    private String reference_number;
    private String customer;
    private Integer amount;
    private String payment_method;

    /**
     * @return the transactionid
     */
    public String getTransactionid() {
        return transactionid;
    }

    /**
     * @param transactionid the transactionid to set
     */
    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the reference_number
     */
    public String getReference_number() {
        return reference_number;
    }

    /**
     * @param reference_number the reference_number to set
     */
    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

   
    /**
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return the payment_method
     */
    public String getPayment_method() {
        return payment_method;
    }

    /**
     * @param payment_method the payment_method to set
     */
    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
    
    
    
}
