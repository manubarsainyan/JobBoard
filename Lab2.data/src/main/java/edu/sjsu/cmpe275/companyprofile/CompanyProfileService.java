package edu.sjsu.cmpe275.companyprofile;

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

import edu.sjsu.cmpe275.user.UserProfile;
import edu.sjsu.cmpe275.user.userProfileDao;

@RestController
@EnableAutoConfiguration
@ComponentScan({"edu.sjsu.cmpe275.lab2.*"})
@EntityScan("edu.sjsu.cmpe275.lab2.*")
@Transactional
public class CompanyProfileService 
{
	@Autowired
	public CompanyProfileController companyProfileController;
	
	@Autowired
	public CompanyProfileDao companyProfileDao;
	@Autowired
	public userProfileDao userProfileDao;
	
	@Transactional
	@RequestMapping(method=RequestMethod.GET,value="/getCompanyProfile/{company_id}")
	public ResponseEntity<?> getCompanies(@PathVariable(value = "company_id") String company_id
			){
		try{
			CompanyProfile companyProfile=companyProfileController.getCompanyProfile(Integer.parseInt(company_id));
			if(companyProfile!=null){
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(companyProfile);
				return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
			}else{
				return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			}
		
		}catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	@RequestMapping(method=RequestMethod.GET,value="/getCompanyProfile")
	public ResponseEntity<?> getCompaniesProfile(){
		try{
			List<CompanyProfile> companyProfile=companyProfileDao.findAllCompanyProfile();
			if(companyProfile!=null){
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(companyProfile);
				return new ResponseEntity<>(jsonInString, HttpStatus.OK);	
			}else{
				return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			}
		
		}catch(Exception e){
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	

		@RequestMapping(method=RequestMethod.POST,value="/createCompanyProfile")
		public ResponseEntity<?> createCompanyProfile(@RequestParam(value = "company_name", required = false) String company_name,
				@RequestParam(value = "company_email", required = false) String company_email,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "company_website", required = false) String company_website,
				@RequestParam(value = "logo_url", required = false) String logo_url,
				@RequestParam(value = "address", required = false) String address,
				@RequestParam(value = "description", required = false) String description) throws Exception{

			try{
				
				CompanyProfile companyProfile=companyProfileDao.findByEmail(company_email);
				if(companyProfile!=null){
					return new ResponseEntity<>("", HttpStatus.valueOf(401));
				}
				UserProfile userProfile=userProfileDao.findByEmail(company_email);
				if(userProfile!=null){
					return new ResponseEntity<>("", HttpStatus.valueOf(402));
				}
				companyProfile=companyProfileController.createCompanyProfile(company_email, password, company_name, company_website, logo_url, address, description);
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(companyProfile);
				return new ResponseEntity<>(jsonInString, HttpStatus.OK);
				

			}catch(RollbackException e){
				return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			catch(Exception e){
				return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		

		@RequestMapping(method=RequestMethod.POST,value="/updateCompanyProfile/{company_id}")
		public ResponseEntity<?> updateCompanyProfile(@PathVariable(value = "company_id") String company_id,
				@RequestParam(value = "company_email", required = false) String company_email,
				@RequestParam(value = "password", required = false) String password,
				@RequestParam(value = "company_name", required = false) String company_name,
				@RequestParam(value = "company_website", required = false) String company_website,
				@RequestParam(value = "logo_url", required = false) String logo_url,
				@RequestParam(value = "address", required = false) String address,
				@RequestParam(value = "description", required = false) String description
				) throws Exception{

			try{
				CompanyProfile companyProfile=companyProfileController.getCompanyProfile(Integer.parseInt(company_id));
				if(companyProfile!=null){
					companyProfile=companyProfileController.updateCompanyProfile(Integer.parseInt(company_id), company_email, password, company_name, company_website, logo_url, address, description);
					ObjectMapper mapper = new ObjectMapper();
					String jsonInString = mapper.writeValueAsString(companyProfile);
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
		
		@RequestMapping(method=RequestMethod.POST,value="/cologin")
		public ResponseEntity<?> companyLogin(@RequestParam(value = "company_email", required = false) String company_email,
				@RequestParam(value = "password", required = false) String password
				) throws Exception{

			try{
				CompanyProfile companyProfile=companyProfileController.checkLogin(company_email);
				if(companyProfile==null){
					return new ResponseEntity<>("", HttpStatus.valueOf(404));	
				}else if(companyProfile.getPassword().equals(password)){
					ObjectMapper mapper = new ObjectMapper();
					String jsonInString = mapper.writeValueAsString(companyProfile);
					if(companyProfile.getIsAccountValidated()!=null && companyProfile.getIsAccountValidated().equalsIgnoreCase("true")){
						return new ResponseEntity<>(jsonInString, HttpStatus.valueOf(200));
					}else{
						return new ResponseEntity<>("", HttpStatus.valueOf(402));
					}
					
				}else{
					return new ResponseEntity<>("", HttpStatus.valueOf(401));
				}			
			}catch(RollbackException e){
				return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			catch(Exception e){
				return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@RequestMapping(method=RequestMethod.POST,value="/CompanyAccountActivation")
		public ResponseEntity<?> accountValidation1(@RequestParam(value = "email", required = false) String email,
				@RequestParam(value = "authCode", required = false) String authCode
				) throws Exception{
			try{
				CompanyProfile companyProfile=companyProfileController.checkLogin(email);
				if(companyProfile==null){
					return new ResponseEntity<>("", HttpStatus.valueOf(601));	
				}else if(String.valueOf((companyProfile.getCompany_id()+500)*4).equals(authCode)){
					companyProfile.setIsAccountValidated("true");
					companyProfileDao.updateCompanyProfile(companyProfile);
					ObjectMapper mapper = new ObjectMapper();
					String jsonInString = mapper.writeValueAsString(companyProfile);
					return new ResponseEntity<>(jsonInString, HttpStatus.valueOf(200));
					
				}
				else
				
				{
					ObjectMapper mapper = new ObjectMapper();
					String jsonInString = mapper.writeValueAsString(companyProfile);
					return new ResponseEntity<>(jsonInString, HttpStatus.valueOf(602));		
				}			
			}catch(RollbackException e){
				return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			catch(Exception e){
				return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		

		
		
		
	}	


