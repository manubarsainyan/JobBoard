package edu.sjsu.cmpe275.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class userProfileDao {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	public UserProfile addUserProfile(UserProfile a){
		return userProfileRepository.save(a);
	}
	
	public List<UserProfile> findAllUserProfile(){
		List<UserProfile> userProfileList = new ArrayList<UserProfile>();
		userProfileRepository.findAll().forEach(userProfileList::add);
		return userProfileList;
	}
	
	public UserProfile findUserProfile(int userId){
		return userProfileRepository.findOne(userId);
	}
	
	public UserProfile updateUserProfile(UserProfile userProfile){
		return userProfileRepository.save(userProfile);
	}
	
	void deleteUserProfile(int userId){
		userProfileRepository.delete(userId);
	}
	
	public UserProfile findByEmail(String email){
		return userProfileRepository.findByEmail(email);
	}
	
}
