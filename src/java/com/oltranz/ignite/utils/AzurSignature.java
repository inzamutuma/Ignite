/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Formatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ismaelnzamutuma
 */
public class AzurSignature {
   private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

 private static String toHexString(byte[] bytes) {
  Formatter formatter = new Formatter();
  
  for (byte b : bytes) {
   formatter.format("%02x", b);
  }

  return formatter.toString();
 }

 public static String calculateRFC2104HMAC(String data, String key)
  throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
 {
   SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
  Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
  mac.init(signingKey);
                byte[] hashedData=mac.doFinal(data.getBytes());
                String hashedDataHex=toHexString(hashedData);
                Base64.Encoder encoder= Base64.getEncoder();                
                String hashedDataHex64Encoded=encoder.encodeToString(hashedDataHex.getBytes());
                
  return hashedDataHex64Encoded;

 }
 
 
    
    
}
