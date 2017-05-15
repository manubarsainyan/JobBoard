package edu.sjsu.cmpe275.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSearchDao {
	
	@Autowired
	JobPostingRepository jobPostingRepository;
	@PersistenceContext
    private EntityManager em;
	
	public List<JobPosting> searchJobs(String text, String company, String location, String minsal, String maxsal) {
	List<JobPosting> jobs=new ArrayList<JobPosting>();
	if(text!=null)
	{
	String arr[]=text.split(" ");
	Map<Integer,JobPosting> jobList=new HashMap<Integer,JobPosting>();
	
	for(int i=0;i<arr.length;i++)
	{
		List<JobPosting> jobList2=jobPostingRepository.findAllByTitleOrSkill(arr[i],arr[i],arr[i]);
		for(JobPosting j:jobList2)
		{
			if(!(jobList.keySet().contains(j.getJobId())))
			{
				jobList.put(j.getJobId(), j);
			}
		}
		
	}
	jobs.addAll(jobList.values());
	}
	String query="";
	int count=0;
	if(company!=null)
	{
		String[] arr=company.split(",");
		String s="";
		for(int i=0;i<arr.length-1;i++)
		{
			s+="\""+arr[i]+"\""+",";
		}
		s+="\""+arr[arr.length-1]+"\"";
		company=s;
		query+= " c.company_name in ( "+company+" )";
		count++;
	}
	if(location!=null)
	{
		String[] arr=location.split(",");
		String s="";
		for(int i=0;i<arr.length-1;i++)
		{
			s+="\""+arr[i]+"\""+",";
		}
		s+="\""+arr[arr.length-1]+"\"";
		location=s;
		if(count>0)
		{
			query+="and";
		}
		query+= " j.location in ( "+location+" )";
		count++;
	}
	if(minsal!=null)
	{
		if(count>0)
		{
			query+="and";
		}
		query+= " j.salary > "+minsal;
		count++;
	}
	if(maxsal!=null)
	{
		if(count>0)
		{
			query+="and";
		}
		query+= " j.salary < "+maxsal;
	
	}
	
	jobs=em.createNativeQuery("Select j.company_id, j.job_id, j.title, j.description from Job_Posting j, Company_Profile c where "+query+" and j.company_id=c.company_id GROUP BY j.job_id").getResultList();
 
	return jobs;
	}
}
