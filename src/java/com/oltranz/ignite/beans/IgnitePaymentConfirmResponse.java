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
public class IgnitePaymentConfirmResponse implements Serializable {
   @XmlElement(name="SPtransactionId")
    private String SPtransactionId;
    @XmlElement(name="statusId")
    private Integer statusId;
    @XmlElement(name="statusDesc")
    private String statusDesc;
    @XmlElement(name="balance")
    private Integer balance; 

    /**
     * @return the SPtransactionId
     */
    public String getSPtransactionId() {
        return SPtransactionId;
    }

    /**
     * @param SPtransactionId the SPtransactionId to set
     */
    public void setSPtransactionId(String SPtransactionId) {
        this.SPtransactionId = SPtransactionId;
    }

    /**
     * @return the statusId
     */
    public Integer getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     * @return the statusDesc
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * @param statusDesc the statusDesc to set
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    /**
     * @return the balance
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    
    
}
