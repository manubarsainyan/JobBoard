package edu.sjsu.cmpe275.interview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.RollbackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sjsu.cmpe275.application.MarkJobs;
import edu.sjsu.cmpe275.companyPositions.CompanyPosition;
import edu.sjsu.cmpe275.companyPositions.CompanyPositionController;
import edu.sjsu.cmpe275.companyPositions.CompanyPositionService;
import edu.sjsu.cmpe275.mail.MailService;
import edu.sjsu.cmpe275.user.UserProfile;
import edu.sjsu.cmpe275.user.userProfileDao;

@RestController
@EnableAutoConfiguration
@ComponentScan({"edu.sjsu.cmpe275.*"})
@EntityScan("edu.sjsu.cmpe275.*")
@Transactional
public class InterviewController {
	
	@Autowired
	InterviewService interviewService;
	@Autowired
	MailService mailService;
	@Autowired
	userProfileDao userProfileDao;
	@Autowired 
	CompanyPositionController companyPositionController;
	
	
	@RequestMapping(method=RequestMethod.GET,value="/setInterview")
	public ResponseEntity<?> setInterviewForPosition(@RequestParam("position_id") String position_id,
			@RequestParam("user_id") String user_id,
			@RequestParam("agenda") String agenda,
			@RequestParam("date") String date,
			@RequestParam("starttime") String starttime,
			@RequestParam("time") String time,
			@RequestParam("location") String location,
			@RequestParam("company") int company_id) throws Exception{
		try{
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			String[] timearr=starttime.split(":");
			Calendar cal = Calendar.getInstance();
		    cal.setTime(date1);
		    cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(timearr[0]));
		    cal.add(Calendar.MINUTE, Integer.parseInt(timearr[1]));
		
			
			Date d2=cal.getTime();
			Interview interview=new Interview();
			interview.setUserId(Integer.parseInt(user_id));
			CompanyPosition cp=companyPositionController.getCompanyPosition(Integer.parseInt(position_id));
			interview.setPositionId(Integer.parseInt(position_id));
			interview.setStartTime(d2);
			interview.setInterviewLength(Integer.parseInt(time));
			interview.setLocation(location);
			interview.setStatus("");
			UserProfile userProfile=userProfileDao.findByUserId(Integer.parseInt(user_id));
			interviewService.addInterview(interview);
			mailService.sendCalendarEmail(userProfile,agenda,d2,time,location);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(interview);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);
			
	}catch(RollbackException e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(method=RequestMethod.GET,value="/getInterviews/{company_id}")
	public ResponseEntity<?> getInterviewsByCompany(@PathVariable("company_id") String company_id) throws Exception{
		try{

			List<CompanyPosition> positions=companyPositionController.getPositionsByCompanyId(Integer.parseInt(company_id));
			List<Interview> interviews = new ArrayList<Interview>();
			for(CompanyPosition p:positions)
			{
			 interviews.addAll(interviewService.getInterviewByPositionId(p.getPosition_id()));
			}
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(interviews);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
	}catch(RollbackException e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(method=RequestMethod.GET,value="/getInterview")
	public ResponseEntity<?> getInterviewsByUser(@RequestParam("user_id") String user_id) throws Exception{
		try{

			List<Interview> interviews=interviewService.getInterviewByUserId(Integer.parseInt(user_id));
	
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(interviews);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);
	}catch(RollbackException e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(method=RequestMethod.GET,value="/updateInterview")
	public ResponseEntity<?> acceptorRejectInterview(@RequestParam("status") String status,
			@RequestParam("id") String id) throws Exception{
		try{
			Interview interview=interviewService.getInterviewById(Integer.parseInt(id));
			interview.setStatus(status);
			Interview interviews=interviewService.updateInterview(interview);
	
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(interviews);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
	}catch(RollbackException e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
