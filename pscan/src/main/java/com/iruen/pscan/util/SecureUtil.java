/**
 * 
 */
package com.iruen.pscan.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nerve
 *
 */
public class SecureUtil {

	
	private static final Logger logger = LoggerFactory.getLogger(SecureUtil.class);

	public static String SHA256(String base) {
		String result;
        try{
 
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
 
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
 
            result = hexString.toString();
            //출력
            logger.debug("ORIGINAL: {}", base);
            logger.debug("SHA-256 : {}", result);
 
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
	}

}
