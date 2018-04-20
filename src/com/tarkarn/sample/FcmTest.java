package com.tarkarn.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.tarkarn.fcm.FcmService;
import com.tarkarn.fcm.MulticastResult;
import com.tarkarn.fcm.Result;

public class FcmTest {

	// Android 에서 받을 extra key (Android App과 동일해야함)
	private final static String TITLE_KEY = "title";
	private final static String MSG_KEY = "msg";
	
	public static void main(String[] args) {
		
		// Firebase 에서 생성된 서버용 
		final String serverKey = "Firebase Server key.";
		
		// FCM용 사용자 토큰 값.
		final String testToken = "User Test Token.";
		
		// Set Token
		List<String> regIds = new ArrayList<String>();
		regIds.add(testToken);
		regIds.add("123");
		
		// Set Data.
		Map<String, String> message = new HashMap<String, String>();
		message.put(TITLE_KEY, "TITLE_KEY");
		message.put(MSG_KEY, "MSG_KEY");
		
		// Set parameters.
		String params = setMessageToJson(message, regIds);
		
		// Set FcmService.
		FcmService fs = new FcmService();
		fs.setServerKey(serverKey);		// Set ServerKey.
		
		// Request FCM.
		MulticastResult result = fs.send(params);
		System.out.println(result.getSuccess());
		
		// Response 
		List<Map<String, String>> rtnList = new ArrayList<Map<String, String>>();
		ArrayList<Result> resList = result.getResults();
		for (int i = 0; i < result.getResults().size(); i++) {
			Result results = resList.get(i);
			
			// Set Result.
			Map<String, String> rtnMap = new HashMap<String, String>();
			rtnMap.put("regIds", regIds.get(i));
			rtnMap.put("messageId", results.getMessageId());
			rtnMap.put("error", results.getError());

			// Send Success
			if (results.getMessageId() != null) { 
				System.out.println(String.format("Success msgId[%s] errorMsg[%s]", rtnMap.get("messageId"), rtnMap.get("error")));
				
			// Send Fail
			} else { 
				System.out.println(String.format("Fail msgId[%s] errorMsg[%s]", rtnMap.get("messageId"), rtnMap.get("error")));
			}

			rtnList.add(rtnMap);
		}
		
	}
	
	private static String setMessageToJson(Map<String, String> message, List<String> regIds) {
		Map<String, Object> fcmMsg = new HashMap<String, Object>();
		fcmMsg.put("registration_ids", regIds);
		fcmMsg.put("data", message);
		
		System.out.println(new Gson().toJson(fcmMsg));
		
		return new Gson().toJson(fcmMsg);
	}
}
