package edu.sjsu.cmpe275.application;

import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Lazy;


@Entity
public class MarkJobs {
		
		@Id
		@Lazy
		@GeneratedValue(strategy = GenerationType.AUTO)
		//@Column(name="applicant_id")
	   	private int id;  
		@Column(name="applicant_id")
	    private int applicantId;
		@Column(name="job_id")
	    private int jobId;
		
		
		
		
	   public  MarkJobs(){
	    	
	    }
	   public  MarkJobs(int applicantId, int jobId){
		this.applicantId=applicantId;
		this.jobId=jobId;
	   }




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getApplicantId() {
		return applicantId;
	}




	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}




	public int getJobId() {
		return jobId;
	}




	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	   
	
	  
	
}
