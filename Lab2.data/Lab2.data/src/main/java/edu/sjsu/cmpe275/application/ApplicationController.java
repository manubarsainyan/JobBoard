package edu.sjsu.cmpe275.application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ApplicationController {
	
	@Autowired
	public ApplicationDao applicationDao;
	

	public Application getApplication(int applicationId){
		return applicationDao.findApplication(applicationId);
	}

	public Application createApplication(int applicantId, int jobId, Date appliedOn, String status, String active) throws Exception{
		Application application = new Application(applicantId,jobId,appliedOn, status,active);
		try{
			application=applicationDao.addApplication(application);
		return application;
		}catch(Exception e){
			throw new Exception("Phone number present. Please try with different phone number");
		}
	}
	

	public Application updateApplication(int applicationId, int applicantId, int jobId, Date appliedOn, String status, String active){
		Application application=getApplication(applicationId);
		if(application==null) return application;
		if(applicantId>0) application.setApplicantId(applicantId);
		if(jobId>0) application.setJobId(jobId);
		if(appliedOn!=null) application.setAppliedOn(appliedOn);
		if(status!=null) application.setStatus(status);
		if(active!=null) application.setActive(active);
		
		return applicationDao.updateApplication(application);
	}
	
	
	/**
	 * @param passengerId
	 */
	public void deleteApplication(int applicationId){	
		applicationDao.deleteApplicationId(applicationId);;
	}
}