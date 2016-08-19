/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.ws.rs.core.MediaType;
import static sun.util.locale.BaseLocale.SEP;

/**
 *
 * @author ismaelnzamutuma
 */
public class AngazaPosting {
    public String sendXML(String url,String jsonData)
    {
        try
        {
   URL oURL = new URL(url);
   HttpURLConnection con = (HttpURLConnection) oURL.openConnection();
   con.setRequestMethod("POST");
   con.setRequestProperty("Content-type", "text/json; charset=utf-8");
   con.setRequestProperty("X-Authorization", "c58c8761-4fa3-4267-85ba-90552ae4295b");

   con.setDoOutput(true);
   con.setDoInput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(jsonData);
                wr.flush();
            }
    BufferedReader in1= new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in1.readLine()) != null) {
			response.append(inputLine);
		}
                con.disconnect();  
                return response.toString();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        
        
    }
    
    
    public String sendAzurConfirmation(String url, String data,String method) 
    {
          String urlpath ="http://10.171.1.50:8080/Ignite/payment/azurcertificate";
        System.out.println("in the send azur");
        String path = CommonLibrary.sendRESTRequest(urlpath, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);

   File f = new File(path+"/lib/security/azurkeystore.jks");
       
   System.out.println(f.length());
   
          try
        {
            URL oURL = new URL(url);
            
         HttpsURLConnection con = (HttpsURLConnection) oURL.openConnection();
         try
         {
              final KeyStore keyStore = KeyStore.getInstance("JKS"); 
          
    
   System.out.println(f.getAbsolutePath());
		final InputStream is = new FileInputStream(f) ;
			keyStore.load(is, null);
		final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
				.getDefaultAlgorithm());
		kmf.init(keyStore,null);
		final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory
				.getDefaultAlgorithm());
		tmf.init(keyStore);
               final SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new java.security.SecureRandom());
		final SSLSocketFactory socketFactory = sc.getSocketFactory();
                con.setSSLSocketFactory(socketFactory);
		//HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
          System.out.println("able to connect to the url");
         }
         catch(Exception e)
         {
             System.out.println(" SSL Connection not established"+e.getMessage());
         }
            
   // HttpsURLConnection con = (HttpsURLConnection) oURL.openConnection();
   con.setRequestMethod(method);
   con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
   con.setRequestProperty("Authorization", "Basic dGVzdHVzZXI6R3V0ZndDdkVhQ1pMNThuYQ==");
   con.setRequestProperty("Cache-Control", "no-cache");
    con.setRequestProperty("Host", "qa-api.azuri-technologies.com");
    
   con.setDoOutput(true);
   con.setDoInput(true);
   DataOutputStream wr = new DataOutputStream(con.getOutputStream());
  
		wr.writeBytes(data);
		wr.flush();
		wr.close();
    BufferedReader in1= new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in1.readLine()) != null) {
			response.append(inputLine);
		}
                
                in1.close();
                
                con.disconnect();  
                return response.toString();
        }
        catch(Exception e)
        {
            System.out.println("Error Message: "+e.getMessage());
            return null;
        }
    }
    
    
}
