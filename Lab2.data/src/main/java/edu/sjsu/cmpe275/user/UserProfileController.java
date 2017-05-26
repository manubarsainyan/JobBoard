package edu.sjsu.cmpe275.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class UserProfileController {
	
	@Autowired
	public userProfileDao userProfileDao;
	

	public UserProfile getUserProfile(int userId){
		return userProfileDao.findUserProfile(userId);
	}
	
	public UserProfile checkLogin(String email){
		UserProfile userProfile=userProfileDao.findByEmail(email);
		return userProfile;	
	}
	


	public UserProfile createUserProfile(String firstname, String lastname, String email, String profilePicture, String description,String skills,String education, String phone,String password,String isAccountValidated, String isProfileUpdated) throws Exception{
		UserProfile user = new UserProfile(firstname, lastname, email, profilePicture, description,skills,education,phone,password,isAccountValidated, isProfileUpdated);
		try{
			UserProfile userProfile=userProfileDao.addUserProfile(user);
		return userProfile;
		}catch(Exception e){
			throw new Exception("Phone number present. Please try with different phone number");
		}
	}
	

	public UserProfile updateUserProfile(int userId, String firstname, String lastname, String email, byte[] profilePicture, String description,String skills,String education, String phone,String password, String isAccountValidated, String isProfileUpdated){
		UserProfile userProfile=getUserProfile(userId);
		if(userProfile==null) return userProfile;
		if(firstname!=null) userProfile.setFirstname(firstname);
		if(lastname!=null) userProfile.setLastname(lastname);
		
		if(email!=null) userProfile.setEmail(email);
		if(profilePicture!=null) userProfile.setProfilePicture(profilePicture);
		if(description!=null) userProfile.setDescription(description);
		if(skills!=null) userProfile.setSkills(skills);
		if(education!=null) userProfile.setEducation(education);
		if(phone!=null) userProfile.setPhone(phone);
		if(password!=null) userProfile.setPassword(password);
		if(isAccountValidated!=null) userProfile.setIsAccountValidated(isAccountValidated);
		if(isProfileUpdated!=null) userProfile.setIsProfileUpdated(isProfileUpdated);
		
		return userProfileDao.updateUserProfile(userProfile);
	}
	
	
	
	
	
	/**
	 * @param passengerId
	 */
	public void deleteUserProfile(int userId){	
		userProfileDao.deleteUserProfile(userId);;
	}
}