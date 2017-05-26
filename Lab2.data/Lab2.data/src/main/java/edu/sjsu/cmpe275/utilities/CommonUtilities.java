package edu.sjsu.cmpe275.utilities;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonUtilities {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public ResponseEntity<?> createErrorResponse(String isXml,int errorCode, String errorMessage, String errorSartText){
		JSONObject json= new JSONObject();
		try {
			JSONObject inner = new JSONObject();
			inner.put("code",String.valueOf(errorCode));
			inner.put("message",errorMessage);
			json.put(errorSartText, inner);
			
			HttpStatus httpStatus=HttpStatus.OK;
			if(errorCode==404) httpStatus=HttpStatus.NOT_FOUND;
			if(errorCode==202) httpStatus=HttpStatus.ACCEPTED;
			if(errorCode==400) httpStatus=HttpStatus.BAD_REQUEST;
		if(isXml!=null && isXml.equalsIgnoreCase("true")){
			return new ResponseEntity<>(XML.toString(json), httpStatus);
		}else{
			return new ResponseEntity<>(json.toString(), httpStatus);
		}
		} catch (JSONException e1) {
			
		}
		return null;
	}
}
