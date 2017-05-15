package edu.sjsu.cmpe275.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationDao {

	@Autowired
	private ApplicationRepository applicationRepository;
	
	public Application addApplication(Application a){
		return applicationRepository.save(a);
	}
	
	public List<Application> findAllApplications(){
		List<Application> applicationList = new ArrayList<Application>();
		applicationRepository.findAll().forEach(applicationList::add);
		return applicationList;
	}
	
	public Application findApplication(int applicationId){
		return applicationRepository.findOne(applicationId);
	}
	
	public Application updateApplication(Application application){
		return applicationRepository.save(application);
	}
	
	void deleteApplicationId(int applicationId){
		applicationRepository.delete(applicationId);
	}
}
