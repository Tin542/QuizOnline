/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinnt.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
public class MyUtils {
    private static final Logger LOGGER = Logger.getLogger(MyUtils.class);
    //encrypt password
    public static String encryptPassword(String mess) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(mess.getBytes("UTF-8"));
            
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            LOGGER.error("Error at MyUtil: "+e.getMessage());
        }
        return sb.toString();
    }
}
