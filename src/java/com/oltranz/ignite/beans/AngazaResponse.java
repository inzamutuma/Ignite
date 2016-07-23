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
public class AngazaResponse implements Serializable {
 private String code;
 private String customer;
 private String description;
 private String reference_number;
 private String payment_reference;
 private String status;
 private String transaction_id;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the transaction_id
     */
    public String getTransaction_id() {
        return transaction_id;
    }

    /**
     * @param transaction_id the transaction_id to set
     */
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
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
    
  
}
