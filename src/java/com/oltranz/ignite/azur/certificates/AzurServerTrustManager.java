/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.azur.certificates;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author ismaelnzamutuma
 */
public class AzurServerTrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
       xcs[0].checkValidity();
       xcs[0].getIssuerUniqueID();
       xcs[0].getSubjectDN();
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
