package edu.sjsu.cmpe275.companyPositions;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import edu.sjsu.cmpe275.companyprofile.CompanyProfile;
import edu.sjsu.cmpe275.job.SkillsNeeded;

@Entity
public class CompanyPosition 
{

	@Id
	@Lazy
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="position_id")
   	private int position_id;
	@Column(name="position_title")
   	private String position_title;  
	@Column(name="position_description")
    private String position_description;
	@Column(name="position_responsibility")
    private String position_responsibility;
	@Column(name="office_location")
	private String office_location;
	@Column(name="salary")
	private String salary;
	@Column(name="company_id")
	private int company ;


	
	@Column(name = "Status")
	private String status;
	
	CompanyPosition(){
		
	}
	public CompanyPosition(int company_id, String position_title, String position_description, String position_responsibility, String office_location, String salary)
	{
		this.company = company_id;
		this.position_title = position_title;
		this.position_description = position_description;
		this.position_responsibility = position_responsibility;
		this.office_location = office_location;
		this.salary = salary;
	}
	
	@ManyToOne
	@JoinColumn(name="company_id",referencedColumnName = "company_id", insertable = false, updatable = false)
	private CompanyProfile companyProfile;
	public CompanyProfile getCompanyProfile() {
		return companyProfile;
	}
	
	@OneToMany(mappedBy="companyPosition")
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
	private List<SkillsNeeded> skills;

	public List<SkillsNeeded> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillsNeeded> skills) {
		this.skills = skills;
	}

	public void setCompanyProfile(CompanyProfile companyProfile) {
		this.companyProfile = companyProfile;
	}

	public int getPosition_id() {
		return position_id;
	}

	
	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public String getPosition_title() {
		return position_title;
	}

	public void setPosition_title(String position_title) {
		this.position_title = position_title;
	}

	public String getPosition_description() {
		return position_description;
	}

	public void setPosition_description(String position_description) {
		this.position_description = position_description;
	}

	public String getPosition_responsibility() {
		return position_responsibility;
	}

	public void setPosition_responsibility(String position_responsibility) {
		this.position_responsibility = position_responsibility;
	}

	public String getOffice_location() {
		return office_location;
	}

	public void setOffice_location(String office_location) {
		this.office_location = office_location;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	public int getCompany_id() {
		return company;
	}

	public void setCompany_id(int company_id) {
		this.company = company_id;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	


}

