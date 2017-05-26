package edu.sjsu.cmpe275.application;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository  extends CrudRepository<Application,Integer>{

	//Passenger save(Passenger passenger, int id);

}
