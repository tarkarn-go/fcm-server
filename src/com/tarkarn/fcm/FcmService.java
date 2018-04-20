package com.tarkarn.fcm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

/**
 * Firebase Cloud Messaging Service
 * @author tarkarn
 * @since 2018.04.19
 */
public class FcmService {
	
	private String serverKey = "";
	private int connectTimeout = 30;
	private int readTimeout = 30;
	
	// [2018.04.19] Firebase Cloud Messaging Server URL.
//	private final String GCM_URL = "https://gcm-http.googleapis.com/gcm/send";		// [DEPRECATED 4/10/2018] Google Cloud Message.
	private final String FCM_URL = "https://fcm.googleapis.com/fcm/send";			// Firebase Cloud Message.

	/**
	 * Set FCM Server API Key
	 * @param serverKey
	 */
	public void setServerKey(String serverKey) {
		this.serverKey = serverKey;
	}
	
	/**
	 * Set Http Connect Timeout 
	 * @param second : default is 30 second
	 */
	public void setConnectTimeout(int second) {
		this.connectTimeout = second;
	}
	
	/**
	 * Set Http Read Timeout
	 * @param second default is 30 second
	 */
	public void setReadTimeout(int second) {
		this.readTimeout = second;
	}
	
	/**
	 * FCM Send Messaging.
	 * @param params Json Type.
	 * @return	Response Body String.
	 */
	public MulticastResult send(String params) {
//		System.out.println("FcmManager send params : " + params);
		
		String result = "";
		URL obj;
		
		try {
			obj = new URL(FCM_URL);
		
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	
			//set timeout 
			con.setConnectTimeout(connectTimeout * 1000);
			con.setReadTimeout(readTimeout * 1000);
			
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "key="+serverKey);
	
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(params);
			wr.flush();
			wr.close();
	
			int responseCode = con.getResponseCode();
			
			BufferedReader in = null;
			if (HttpsURLConnection.HTTP_OK == responseCode || HttpsURLConnection.HTTP_CREATED == responseCode) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			result = response.toString();
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Gson().fromJson(result, MulticastResult.class);
	}
}
