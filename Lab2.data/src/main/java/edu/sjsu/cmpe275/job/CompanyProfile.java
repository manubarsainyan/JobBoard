apackage edu.sjsu.cmpe275.job;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class CompanyProfile {

	@Id
	@GeneratedValue
	@Column(name="company_id")
	private int company_id;
	
	@Column(name="company_name")
	private String company_name;
	
	@Column(name="logo_image_url")
	private String logo_image_url;
	
	@Column(name="website_url")
	private String website_url;
	
	@Column(name="description")
	private String description;

	@Column(name="email_id")
	private String email_id;
	
	@Column(name="phone")
	private String phone;
	
	@OneToMany(mappedBy="companyProfile")
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="jobId")
	@JsonBackReference
	List<JobPosting> jobPostings;

	public List<JobPosting> getJobPostings() {
		return jobPostings;
	}

	public void setJobPostings(List<JobPosting> jobPostings) {
		this.jobPostings = jobPostings;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getLogo_image_url() {
		return logo_image_url;
	}

	public void setLogo_image_url(String logo_image_url) {
		this.logo_image_url = logo_image_url;
	}

	public String getWebsite_url() {
		return website_url;
	}

	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
