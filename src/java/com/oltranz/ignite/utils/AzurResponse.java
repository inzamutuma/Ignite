/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.utils;

/**
 *
 * @author ismaelnzamutuma
 */
public class AzurResponse {
    private int status;
    private String message;
    private AzurInfo info;
   private String payment_id;
    private boolean is_duplicate;

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
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

    /**
     * @return the info
     */
    public AzurInfo getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(AzurInfo info) {
        this.info = info;
    }

    /**
     * @return the payment_id
     */
    public String getPayment_id() {
        return payment_id;
    }

    /**
     * @param payment_id the payment_id to set
     */
    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    /**
     * @return the is_duplicate
     */
    public boolean isIs_duplicate() {
        return is_duplicate;
    }

    /**
     * @param is_duplicate the is_duplicate to set
     */
    public void setIs_duplicate(boolean is_duplicate) {
        this.is_duplicate = is_duplicate;
    }
    
    
    
}
