package edu.sjsu.cmpe275.companyprofile;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import edu.sjsu.cmpe275.companyPositions.CompanyPosition;
import edu.sjsu.cmpe275.job.JobPosting;

@Entity
public class CompanyProfile 
{
	
	@Id
	@Lazy
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="company_id")
   	private int company_id;
	@Column(name="company_email")
   	private String email; 
	@Column(name="password")
   	private String password; 
	@Column(name="company_name")
   	private String company_name;  
	@Column(name="company_website")
    private String company_website;
	@Column(name="logo_url")
    private String logo_url;
	@Column(name="address")
	private String address;
	@Column(name="description")
	private String description;
	@Column(name="is_account_validated")
    private String isAccountValidated;
	@OneToMany(mappedBy="companyProfile")
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="position_id")
	@JsonBackReference
	List<CompanyPosition> companyPosition;

	
	public CompanyProfile()
	{
		
	}
	
	public CompanyProfile(String company_email, String password, String company_name, String company_website, String logo_url, String address, String description)
	{
		this.email = company_email;
		this.password = password;
		this.company_name = company_name;
		this.company_website = company_website;
		this.logo_url = logo_url;
		this.address = address;
		this.description = description;
	}
	

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
		
	public String getCompany_email() {
		return email;
	}

	public void setCompany_email(String company_email) {
		this.email = company_email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_website() {
		return company_website;
	}

	public void setCompany_website(String company_website) {
		this.company_website = company_website;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getIsAccountValidated() {
		return isAccountValidated;
	}

	public void setIsAccountValidated(String isAccountValidated) {
		this.isAccountValidated = isAccountValidated;
	}

	
	
	
	
	

}
