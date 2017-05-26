package edu.sjsu.cmpe275.application;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

//import scala.annotation.meta.param;




@RestController
@EnableAutoConfiguration
@ComponentScan({"edu.sjsu.cmpe275.*"})
@EntityScan("edu.sjsu.cmpe275.*")
@Transactional
public class ApplicationService {
	@Autowired
	public ApplicationController applicationController;
	@Autowired
	public ApplicationDao applicationDao;


	@Transactional
	@RequestMapping(method=RequestMethod.GET,value="/getApplication/{id}")
	public ResponseEntity<?> getPassenger(@PathVariable(value = "id") String applicationId){
		try{
			Application application=applicationController.getApplication(Integer.parseInt(applicationId));
			if(application!=null){
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(application);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);
		}else{
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			}
		}catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}


	
	@RequestMapping(method=RequestMethod.POST,value="/createApplication")
	public ResponseEntity<?> createApplication(@RequestParam(value = "applicantId", required = false) String applicantId,
			@RequestParam(value = "jobId", required = false) String jobId,
			@RequestParam(value = "appliedOn", required = false) String appliedOn,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "active", required = false) String active
			) throws Exception{

		try{
			Application application=applicationController.createApplication(Integer.parseInt(applicantId), Integer.parseInt(jobId), new Date(), status, active);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(application);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);
			
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/createApplication/{applicantId}")
	public ResponseEntity<?> updateApplication(@PathVariable(value = "applicationId") String applicationId,
			@RequestParam(value = "applicantId", required = false) String applicantId,
			@RequestParam(value = "jobId", required = false) String jobId,
			@RequestParam(value = "appliedOn", required = false) String appliedOn,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "active", required = false) String active
			) throws Exception{

		try{
			Application application=applicationController.getApplication(Integer.parseInt(applicationId));
			if(application!=null){
			if(applicantId!=null) application.setApplicantId(Integer.parseInt(applicantId));	
			if(jobId!=null) application.setJobId(Integer.parseInt(jobId));	
			//if(appliedOn!=null) application.setAppliedOn(new Date());	
			if(status!=null) application.setStatus(status);	
			if(appliedOn!=null) application.setActive(active);
			
			application=applicationDao.addApplication(application);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(application);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);
			}else{
				return new ResponseEntity<>("", HttpStatus.NOT_FOUND);	
			}
		}catch(RollbackException e){
		}
		catch(Exception e){
		}
		return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@RequestMapping(method=RequestMethod.DELETE,value="/createApplication/{applicationId}")
	public ResponseEntity<?> deleteApplication(@PathVariable(value = "id") String applicationId
			){
		try{
			Application application=applicationController.getApplication(Integer.parseInt(applicationId));
			if(application!=null){
			
			applicationDao.deleteApplicationId(Integer.parseInt(applicationId));
			return new ResponseEntity<>("", HttpStatus.OK);
			}else{
				return new ResponseEntity<>("", HttpStatus.NOT_FOUND);	
			}
		}catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	

}
