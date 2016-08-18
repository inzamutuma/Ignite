/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ismaelnzamutuma
 */
@Entity
@Table(name ="BillPayments")
@XmlRootElement(name="COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class IgnitePaymentConfirmRequest  implements Serializable{
@Id
    @XmlElement(name="paymentSPId")
    private Integer paymentSPId;
    @XmlElement(name="paymentSPtransactionId")
    @Id
    private String paymentSPtransactionId;
    @XmlElement(name="paymentSPaccountId")
    private String paymentSPaccountId;
    @XmlElement(name="paymentSPmerchantId")
    private String paymentSPmerchantId;
   
    @XmlElement(name="accountRef")
    private String accountRef;
    @XmlElement(name="paymentRef")
    private String paymentRef;
    @XmlElement(name="amount")
    private Integer amount;
    @XmlElement(name="statusCode")
    private Integer statusCode;
    
    
    
   @XmlTransient
    private String status;
   @XmlTransient
   @Temporal(TemporalType.DATE)
   private java.util.Date transactionDate;
  @XmlTransient
   private String paidOrganization; 
  @XmlTransient
  private String marchantTransactionId;
          
          
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
     * @return the accountRef
     */
    public String getAccountRef() {
        return accountRef;
    }

    /**
     * @param accountRef the accountRef to set
     */
    public void setAccountRef(String accountRef) {
        this.accountRef = accountRef;
    }

    /**
     * @return the paymentRef
     */
    public String getPaymentRef() {
        return paymentRef;
    }

    /**
     * @param paymentRef the paymentRef to set
     */
    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
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
     * @return the transactionDate
     */
    public java.util.Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(java.util.Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the paidOrganization
     */
    public String getPaidOrganization() {
        return paidOrganization;
    }

    /**
     * @param paidOrganization the paidOrganization to set
     */
    public void setPaidOrganization(String paidOrganization) {
        this.paidOrganization = paidOrganization;
    }

    /**
     * @return the marchantTransactionId
     */
    public String getMarchantTransactionId() {
        return marchantTransactionId;
    }

    /**
     * @param marchantTransactionId the marchantTransactionId to set
     */
    public void setMarchantTransactionId(String marchantTransactionId) {
        this.marchantTransactionId = marchantTransactionId;
    }

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    
}
