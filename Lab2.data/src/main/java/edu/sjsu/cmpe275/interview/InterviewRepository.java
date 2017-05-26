package edu.sjsu.cmpe275.interview;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.sjsu.cmpe275.companyPositions.CompanyPosition;


public interface InterviewRepository extends CrudRepository<Interview,Integer>{

	List<Interview> findAllByUserId(int userId);

	List<Interview> findAllByPositionId(int position_id);

	List<Interview> findAllInterviewsByPosition(CompanyPosition p);

}
