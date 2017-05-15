package edu.sjsu.cmpe275.job;

import java.util.List;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sjsu.cmpe275.user.UserProfile;


@RestController
@EnableAutoConfiguration
@ComponentScan({"com.sjsu.cmpe275.*"})
@EntityScan("com.sjsu.cmpe275.*")
@Transactional
public class JobSearchController {
	
	@Autowired
	public JobSearchService jobSearchService;
	
	@Transactional
	@RequestMapping(method=RequestMethod.GET,value="/searchJobs")
	public ResponseEntity<?> searchJobs(@RequestParam(value = "text", required=false) String text
			,@RequestParam(value = "company", required=false) String company,
			@RequestParam(value = "location", required=false) String location,
			@RequestParam(value = "minsal", required=false) String minsal,
			@RequestParam(value = "maxsal", required=false) String maxsal) throws JsonProcessingException{
		
			List<JobPosting> jobs=jobSearchService.searchJobs(text,company,location,minsal,maxsal);
			if(jobs!=null){
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(jobs);
				return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
			}else{
				return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			}
		
		
	}	 

}
