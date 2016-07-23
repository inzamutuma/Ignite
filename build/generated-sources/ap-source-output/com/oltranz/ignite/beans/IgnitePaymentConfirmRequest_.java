package com.oltranz.ignite.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-14T18:11:38")
@StaticMetamodel(IgnitePaymentConfirmRequest.class)
public class IgnitePaymentConfirmRequest_ { 

    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, Integer> paymentSPId;
    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, String> senderId;
    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, Integer> amount;
    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, String> paidOrganization;
    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, String> paymentSPtransactionId;
    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, String> merchantContractId;
    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, String> accountRef;
    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, Date> transactionDate;
    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, String> paymentRef;
    public static volatile SingularAttribute<IgnitePaymentConfirmRequest, String> status;

}