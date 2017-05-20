package edu.sjsu.cmpe275.companyPositions;






import java.util.ArrayList;
import java.util.List;

import javax.persistence.RollbackException;

import org.springframework.beans.factory.annotation.Autowired;
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

import edu.sjsu.cmpe275.job.SkillsNeeded;
import edu.sjsu.cmpe275.job.SkillsNeededDao;



@RestController
@EnableAutoConfiguration
@ComponentScan({"edu.sjsu.cmpe275.lab2.*"})
@EntityScan("edu.sjsu.cmpe275.lab2.*")
@Transactional
public class CompanyPositionService 
{
	@Autowired
	public CompanyPositionController companyPositionController;
	
	@Autowired
	public SkillsNeededDao skillsNeededDao;
	
	@Autowired
	public CompanyPositionDao companyPositionDao;
	
	@Transactional
	
	@RequestMapping(method=RequestMethod.GET,value="/getAllPositionLocations")
	public ResponseEntity<?> getPositions(){
		try{
			List<String> locList=companyPositionDao.findAllJobLocations();
			
			if(locList!=null){
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(locList);
				return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
			}else{
				return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			}
		
		}catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/getCompanyPosition/{position_id}")
	public ResponseEntity<?> getPositions(@PathVariable(value = "position_id") String position_id
			){
		try{
			CompanyPosition companyPosition=companyPositionController.getCompanyPosition(Integer.parseInt(position_id));
			if(companyPosition!=null){
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(companyPosition);
				return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
			}else{
				return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			}
		
		}catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getPositionsByCompanyId/{company_id}")
	public ResponseEntity<?> positionNames(@PathVariable(value = "company_id") String company_id
			){
		try{
			List<CompanyPosition> companyPositions=companyPositionController.getPositionsByCompanyId(Integer.parseInt(company_id));
			//if(companyPositions!=null)
			
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(companyPositions);
				return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
			
			
		
		}catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/createCompanyPosition")
	public ResponseEntity<?> createCompanyPosition(@RequestParam(value = "position_title", required = false) String position_title,
			@RequestParam(value = "company_id", required = false) int company_id,
			@RequestParam(value = "position_description", required = false) String position_description,
			@RequestParam(value = "position_responsibility", required = false) String position_responsibility,
			@RequestParam(value = "office_location", required = false) String office_location,
			@RequestParam(value = "salary", required = false) String salary,
			@RequestParam(value = "skills", required = false) String skills
			
			) throws Exception{

		try{
			CompanyPosition companyPosition=companyPositionController.createCompanyPosition(company_id, position_title, position_description, position_responsibility, office_location, salary);
			if(companyPosition!=null && skills.trim()!=null){
				
				ArrayList<SkillsNeeded> snList=new ArrayList<SkillsNeeded>();
				for(String sk:skills.split(",")){
					SkillsNeeded skillsNeeded = new SkillsNeeded();
					skillsNeeded.setJobid(companyPosition.getPosition_id());
					skillsNeeded.setSkill(sk);
					snList.add(skillsNeeded);
				}
				skillsNeededDao.addAllSkillsNeeded(snList);
				
				
			}
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(companyPosition);
			return new ResponseEntity<>(jsonInString, HttpStatus.OK);
			

		}catch(RollbackException e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/updateCompanyPosition/{position_id}")
	public ResponseEntity<?> updateCompanyPosition(@PathVariable(value = "position_id") String position_id,
			@RequestParam(value = "company_id", required = false) int company_id,
			@RequestParam(value = "position_title", required = false) String position_title,
			@RequestParam(value = "position_description", required = false) String position_description,
			@RequestParam(value = "position_responsibility", required = false) String position_responsibility,
			@RequestParam(value = "office_location", required = false) String office_location,
			@RequestParam(value = "salary", required = false) String salary
			) throws Exception{

		try{
			CompanyPosition companyPosition=companyPositionController.getCompanyPosition(Integer.parseInt(position_id));
			if(companyPosition!=null){
				companyPosition=companyPositionController.updateCompanyPosition(Integer.parseInt(position_id), company_id, position_title, position_description, position_responsibility, office_location, salary);
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(companyPosition);
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
	
	
	
	
	
	
}
