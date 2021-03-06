package edu.sjsu.cmpe275.user;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.RollbackException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sjsu.cmpe275.application.Application;
//import edu.sjsu.cmpe275.lab2.CommonUtilities;
//import scala.annotation.meta.param;




@RestController
@EnableAutoConfiguration
@ComponentScan({"edu.sjsu.cmpe275.lab2.*"})
@EntityScan("edu.sjsu.cmpe275.lab2.*")
@Transactional
public class JobSeekerService {
	@Autowired
	public UserProfileController userProfileController;
//	@Autowired
//	public SimpleEmailController simpleEmailController;
//	CommonUtilities commonUtilities= new CommonUtilities();
//	@RequestMapping("/aaa")
//    String home() {
//        return "Hellokjhj;n World!";
//    }
//	

	@Transactional
	@RequestMapping(method=RequestMethod.GET,value="/getUserProfile/{id}")
	public ResponseEntity<?> getPassenger(@PathVariable(value = "id") String userId
			){
		try{
			UserProfile userProfile=userProfileController.getUserProfile(Integer.parseInt(userId));
			if(userProfile!=null){
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(userProfile);
				return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
			}else{
				return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			}
		
		}catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}	 
	
	@RequestMapping(method=RequestMethod.POST,value="/createUserProfile")
	public ResponseEntity<?> createUserProfile(@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "profilePicture", required = false) String profilePicture,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "skills", required = false) String skills,
			@RequestParam(value = "education", required = false) String education,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "isAccountValidated", required = false) String isAccountValidated,
			@RequestParam(value = "isProfileUpdated", required = false) String isProfileUpdated
			) throws Exception{

		try{
			UserProfile userProfile=userProfileController.createUserProfile(firstname, lastname, email, profilePicture, description,skills,education,phone,password,isAccountValidated, isProfileUpdated);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(userProfile);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);
			

		}catch(RollbackException e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/updateUserProfile/{id}")
	public ResponseEntity<?> updateUserProfile(@PathVariable(value = "id") String userId,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "profilePicture", required = false) String profilePicture,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "skills", required = false) String skills,
			@RequestParam(value = "education", required = false) String education,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "isAccountValidated", required = false) String isAccountValidated,
			@RequestParam(value = "isProfileUpdated", required = false) String isProfileUpdated
			) throws Exception{

		try{
			UserProfile userProfile=userProfileController.getUserProfile(Integer.parseInt(userId));
			if(userProfile==null){
				userProfile=userProfileController.updateUserProfile(Integer.parseInt(userId), firstname, lastname, email, profilePicture, description, skills, education, phone, password, isAccountValidated, isProfileUpdated);
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(userProfile);
				return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
			}else{
				return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			}
	}catch(RollbackException e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
//	@RequestMapping(method=RequestMethod.DELETE,value="/createUserProfile/{id}")
//	public String deleteUserProfile(@PathVariable(value = "id") String userId
//			){
//		try{
//			UserProfile passenger=UserProfileController.getPassenger(userId);
//			if(passenger!=null){
//				passengerController.deletePassenger(passengerId);
//				return commonUtilities.createErrorResponse(xml,200," Passenger with id "+passengerId+" is deleted successfully", "Response");
//				
//			}else{
////				return "{BadRequest: {code: 404,msg: Passenger with id "+passengerId+" does not exist} }";
//				return commonUtilities.createErrorResponse(xml,404," Passenger with id "+passengerId+" does not exist", "BadRequest");
//			}
//			
//			
//		}catch(Exception e){
//			return commonUtilities.createErrorResponse(xml,400, e.getMessage(), "BadRequest");
//		}
//	}
	


}
