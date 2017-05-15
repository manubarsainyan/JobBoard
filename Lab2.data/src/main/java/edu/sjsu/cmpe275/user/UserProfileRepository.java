package edu.sjsu.cmpe275.user;

import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository  extends CrudRepository<UserProfile,Integer>{

	//Passenger save(Passenger passenger, int id);

}
