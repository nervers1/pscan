/**
 * 
 */
package com.iruen.pscan.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @author nerve
 *
 */
public class StringUtil {
	
	public static void main(String[] args) {
		String text = StringUtil.getRandomString(30);
		System.out.println(">> " + text);
	}
	
	
	public static String getRandomString(int length) {
	  StringBuffer buffer = new StringBuffer();
	  Random random = new Random();
	  String chars[] = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
	  
	  for (int i=0 ; i<length ; i++) {
	    buffer.append(chars[random.nextInt(chars.length)]);
	  }
	  return buffer.toString();
	}
	
	public static String getCurrentDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
		return strToday;
	}
}
