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
import edu.sjsu.cmpe275.mail.MailService;

import edu.sjsu.cmpe275.application.Application;
//import edu.sjsu.cmpe275.lab2.CommonUtilities;
//import scala.annotation.meta.param;
//import edu.sjsu.cmpe275.utilities.SendMailsService;




@RestController
@EnableAutoConfiguration
@ComponentScan({"edu.sjsu.cmpe275.lab2.*"})
@EntityScan("edu.sjsu.cmpe275.lab2.*")
@Transactional
public class JobSeekerService {
	@Autowired
	public UserProfileController userProfileController;
	@Autowired
	public userProfileDao userProfileDao;
	@Autowired
	public MailService mailService;
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
			@RequestParam(value = "picture", required = false) String profilePicture,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "skills", required = false) String skills,
			@RequestParam(value = "education", required = false) String education,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "isAccountValidated", required = false) String isAccountValidated,
			@RequestParam(value = "isProfileUpdated", required = false) String isProfileUpdated
			) throws Exception{

		try{
			UserProfile userProfile=userProfileDao.findByEmail(email);
			if(userProfile!=null){
				return new ResponseEntity<>("", HttpStatus.valueOf(401));
			}else{
			userProfile=userProfileController.createUserProfile(firstname, lastname, email, profilePicture, description,skills,education,phone,password,isAccountValidated, isProfileUpdated);
			mailService.sendRegistrationEmail(userProfile);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(userProfile);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);
			}

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
			@RequestParam(value = "profilePicture", required = false) byte[] profilePicture,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "skills", required = false) String skills,
			@RequestParam(value = "education", required = false) String education,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "isAccountValidated", required = false) String isAccountValidated,
			@RequestParam(value = "isProfileUpdated", required = false) String isProfileUpdated
			) throws Exception{

		try{
			UserProfile userProfile=null;
			System.out.println("working ");;
			if(email!=null){
				userProfile=userProfileDao.findByEmail(email);
				 if(userProfile!=null){
					 userProfile=userProfileController.getUserProfile(Integer.parseInt(userId)); 
				 }else{
					 return new ResponseEntity<>("", HttpStatus.valueOf(401)); 
				 }
			}else{
			userProfile=userProfileController.getUserProfile(Integer.parseInt(userId));
			}
			if(userProfile!=null){
				userProfile=userProfileController.updateUserProfile(Integer.parseInt(userId), firstname, lastname, email, profilePicture, description, skills, education, phone, password, isAccountValidated, "true");
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
	
	@RequestMapping(method=RequestMethod.POST,value="/login")
	public ResponseEntity<?> login(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password
			) throws Exception{

		try{
			UserProfile userProfile=userProfileController.checkLogin(email);
			if(userProfile==null){
				return new ResponseEntity<>("", HttpStatus.valueOf(404));	
			}else if(userProfile.getPassword()!=null && userProfile.getPassword().equals(password)){
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(userProfile);
				
				if(userProfile.getIsAccountValidated()==null || !userProfile.getIsAccountValidated().trim().equalsIgnoreCase("true")){
						return new ResponseEntity<>(jsonInString, HttpStatus.valueOf(405));
					}
				
				
				if(userProfile.getIsAccountValidated()!=null && userProfile.getIsAccountValidated().equalsIgnoreCase("true")){
					return new ResponseEntity<>(jsonInString, HttpStatus.valueOf(200));
				}else{
					return new ResponseEntity<>(jsonInString, HttpStatus.valueOf(406));
				}
				
			}else{
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(userProfile);
				return new ResponseEntity<>(jsonInString, HttpStatus.valueOf(401));
			}			
		}catch(RollbackException e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/login")
	public ResponseEntity<?> loginGet(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password
			) throws Exception{
		return new ResponseEntity<>("", HttpStatus.OK);
	
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/AccountActivation")
	public ResponseEntity<?> accountValidation(@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "authCode", required = false) String authCode
			) throws Exception{
		try{
			if(userId==null || userId.equals("")){
				return new ResponseEntity<>("", HttpStatus.valueOf(402));	
			}
			UserProfile userProfile=userProfileDao.findByUserId(Integer.parseInt(userId));
			if(userProfile==null){
				return new ResponseEntity<>("", HttpStatus.valueOf(404));	
			}else if(userId.equals(authCode)){
				userProfile.setIsAccountValidated("true");
				userProfileDao.updateUserProfile(userProfile);
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(userProfile);
				return new ResponseEntity<>(jsonInString, HttpStatus.valueOf(200));	
			}else{
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(userProfile);
				return new ResponseEntity<>(jsonInString, HttpStatus.valueOf(402));		
			}			
		}catch(RollbackException e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@RequestMapping(method=RequestMethod.GET,value="/mail")
//	public void sendMail(){
//		try{
//			sendMailsService.sendMail();
//			
//		
//		}catch(Exception e){
//			
//		}	
//	}
	
	
	
	
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
