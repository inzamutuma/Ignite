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
public class DLightResponse implements Serializable
        {
    /*
    "code": "500",
  "status": "FAILED",
  "account_balance": 0,
  "customer": "252700000",
  "reference_number": "245543123",
  "description": "Transaction already exists",
  "payment_reference": "245543123",
  "transactionId": "TT12345"*/
    private String code;
    private String account_balance;
    private String customer;
    private String reference_number;
    private String description;
    private String payment_reference;
    private String transacitonId;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

  
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the account_balance
     */
    public String getAccount_balance() {
        return account_balance;
    }

    /**
     * @param account_balance the account_balance to set
     */
    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
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
     * @return the payment_reference
     */
    public String getPayment_reference() {
        return payment_reference;
    }

    /**
     * @param payment_reference the payment_reference to set
     */
    public void setPayment_reference(String payment_reference) {
        this.payment_reference = payment_reference;
    }

    /**
     * @return the transacitonId
     */
    public String getTransacitonId() {
        return transacitonId;
    }

    /**
     * @param transacitonId the transacitonId to set
     */
    public void setTransacitonId(String transacitonId) {
        this.transacitonId = transacitonId;
    }
    
    
    
}
