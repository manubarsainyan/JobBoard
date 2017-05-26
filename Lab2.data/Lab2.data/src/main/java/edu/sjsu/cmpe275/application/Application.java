package edu.sjsu.cmpe275.application;

import java.sql.Blob;
import java.util.Date;
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
public class Application {
		
		@Id
		@Lazy
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="application_id")
	   	private int aplicationId; 
		
		@Column(name="applicant_id")
	    private int applicantId;
		
		@Column(name="job_id")
	    private int jobId;
		@Column(name="applied_on")
		private Date appliedOn;
		@Column(name="status")
	    private String status;
		@Column(name="active")
	    private String active;
		
	   public  Application(){
	    	
	    }
	   
	   public  Application(int applicantId, int jobId, Date appliedOn, String status, String active){
		   this.applicantId=applicantId;
		   this.jobId=jobId;
		   this.appliedOn=appliedOn;
		   this.status=status;
		   this.active=active;
	   }

	public int getAplicationId() {
		return aplicationId;
	}

	public void setAplicationId(int aplicationId) {
		this.aplicationId = aplicationId;
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

	public Date getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	   

	  
	
}
