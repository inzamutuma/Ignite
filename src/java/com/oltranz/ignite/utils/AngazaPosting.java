/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
   DataOutputStream wr = new DataOutputStream(con.getOutputStream());
  
		wr.writeBytes(jsonData);
		wr.flush();
		wr.close();
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
}
