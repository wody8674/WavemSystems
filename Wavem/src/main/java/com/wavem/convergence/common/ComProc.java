package com.wavem.convergence.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComProc {
	
	private static final Logger logger = LoggerFactory.getLogger(ComProc.class);
	
	public static String urlDecoder(String data) {
		
		String retData = "";
		
		if (data != null) {
			try {
				retData = URLDecoder.decode(data, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("utf-8 error");
			}
		}
		
		return retData;
	}
	
	public static String urlPage(String url) {
		
//		String retData = "";
//		BufferedReader br1 = null;
//		
//		try {
//			br1 = new BufferedReader(new InputStreamReader((new URL("http://localhost:8082/SpringProject/selectSidemenu.go")).openConnection().getInputStream()));
//			retData = br1.readLine();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
		
		
		
//		FileInputStream fileInputStream = null;
//		BufferedInputStream bufferedInputStream = null;
//		DataInputStream dataInputStream = null;
//		try {
//			fileInputStream = new FileInputStream("http://localhost:8082/SpringProject/html/login/html/login.html");
//			bufferedInputStream = new BufferedInputStream(fileInputStream);
//			dataInputStream = new DataInputStream(bufferedInputStream);
//			retData = dataInputStream.readUTF();
//			
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		System.out.println(retData);
		return null;
	}

}
