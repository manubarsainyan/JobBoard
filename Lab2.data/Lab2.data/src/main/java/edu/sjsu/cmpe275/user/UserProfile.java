package edu.sjsu.cmpe275.user;


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
public class UserProfile {
		
		@Id
		@Lazy
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="applicant_id")
	   	private int userId;  
		@Column(name="first_name")
	    private String firstname;
		@Column(name="last_name")
	    private String lastname;
		@Column(name="email_address")
		private String email;
		@Column(name="profile_picture")
	    private String profilePicture;
		@Column(name="description")
	    private String description;
		@Column(name="skills")
	    private String skills; // Phone numbers must be unique
		@Column(name="education")
	    private String education; 
		@Column(name="phone")
	    private String phone; 
		@Column(name="password")
	    private String password; 
		@Column(name="is_account_validated")
	    private String isAccountValidated;
		@Column(name="is_Profile_Updated")
	    private String isProfileUpdated;
		
	   public  UserProfile(){
	    	
	    }
	   public  UserProfile(String firstname, String lastname, String email, String profilePicture, String description,String skills,String education, String phone, String password,String isAccountValidated, String isProfileUpdated){
		
	    this.firstname=firstname;
	    this.lastname=lastname;
	    this.email=email;
	    //this.profilePicture=profilePicture;
	    this.description=description;
	    this.skills=skills;
	    this.education=education;
	    this.phone=phone;
	    this.password=password;
	    this.isAccountValidated=isAccountValidated;
	    this.isProfileUpdated=isProfileUpdated;
	   }
//	   public  UserProfile(int passengerId,String firstname, String lastname,String age, String gender, String phone){
//			this.id=passengerId;
//		    this.firstname=firstname;
//		    this.lastname=lastname;
//		    this.age=age;
//		    this.gender=gender;
//		    this.phone=phone;
//		   }
	   
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsAccountValidated() {
		return isAccountValidated;
	}
	public void setIsAccountValidated(String isAccountValidated) {
		this.isAccountValidated = isAccountValidated;
	}
	public String getIsProfileUpdated() {
		return isProfileUpdated;
	}
	public void setIsProfileUpdated(String isProfileUpdated) {
		this.isProfileUpdated = isProfileUpdated;
	}	
	  
	
}
