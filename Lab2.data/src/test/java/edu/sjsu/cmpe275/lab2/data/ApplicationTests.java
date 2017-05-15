package edu.sjsu.cmpe275.lab2.data;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class ApplicationTests {
	
	@Test
	public void testCreatePassenger() throws JSONException {
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post("https://ec2-34-207-206-98.compute-1.amazonaws.com:8443/passenger?firstname=Anshul&lastname=Agrawal&age=27&gender=male&phone=1683")
					  .header("accept", "application/json")
					  .asJson();
			
			JSONObject jsonObj= jsonResponse.getBody().getObject();
			JSONObject json =  (JSONObject) jsonObj.get("BadRequest");
			
			assertEquals("400", json.get("code"));
			assertEquals("could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement", json.get("message"));
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	@Test
	public void testGetPassenger() throws JSONException {
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.get("https://ec2-34-207-206-98.compute-1.amazonaws.com:8443/passenger/29")
					  .header("accept", "application/json")
					  .asJson();
			
			JSONObject jsonObj= jsonResponse.getBody().getObject();
			JSONObject json =  (JSONObject) jsonObj.get("passenger");

			assertEquals("Anshul", json.get("firstname"));
			assertEquals("male", json.get("gender"));
			assertEquals("18883", json.get("phone"));
			assertEquals("27", json.get("age"));
			assertEquals("Agrawal", json.get("lastname"));
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	

	
	@Test
	public void testUpdatePassenger() throws JSONException {
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.put("https://ec2-34-207-206-98.compute-1.amazonaws.com:8443/passenger/29?firstname=Anshul&lastname=Agrawal&age=27&gender=male&phone=454545")
					  .header("accept", "application/json")
					  .asJson();
			
			JSONObject jsonObj= jsonResponse.getBody().getObject();
			JSONObject json =  (JSONObject) jsonObj.get("passenger");
			
			assertEquals("Anshul", json.get("firstname"));
			assertEquals("male", json.get("gender"));
			assertEquals("454545", json.get("phone"));
			assertEquals("27", json.get("age"));
			assertEquals("Agrawal", json.get("lastname"));
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
    
	@Test
	public void testGetFlight() throws JSONException {
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.get("https://ec2-34-207-206-98.compute-1.amazonaws.com:8443/flight/A4")
					  .header("accept", "application/json")
					  .asJson();
			
			JSONObject jsonObj= jsonResponse.getBody().getObject();
			JSONObject json =  (JSONObject) jsonObj.get("flight");
			
			assertEquals("2017-01-12-10", json.get("departureTime"));
			assertEquals("120", json.get("price"));
			assertEquals("2017-01-12-12", json.get("arrivalTime"));
			assertEquals("EE", json.get("description"));
			assertEquals("A", json.get("from"));
			assertEquals("B", json.get("to"));
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

}


