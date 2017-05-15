package edu.sjsu.cmpe275.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JobSearchService {

	@Autowired
	JobSearchDao jobSearchDao;
	
	public List<JobPosting> searchJobs(String text, String company, String location, String minsal, String maxsal) {
	
	return jobSearchDao.searchJobs(text,company,location,minsal,maxsal);
	}

}
