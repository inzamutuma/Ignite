/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.beans;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author ismaelnzamutuma
 */
public class AngazaRequest implements Serializable{
    @JsonProperty("merchant_account")
    private String marchant_account;
    private String reference_number;
    private String customer;
    private String transaction_id;
    private Integer amount;

    /**
     * @return the marchant_account
     */
    public String getMarchant_account() {
        return marchant_account;
    }

    /**
     * @param marchant_account the marchant_account to set
     */
    public void setMarchant_account(String marchant_account) {
        this.marchant_account = marchant_account;
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

   

            
            
}
