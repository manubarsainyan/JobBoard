package edu.sjsu.cmpe275.interview;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.companyPositions.CompanyPosition;
import edu.sjsu.cmpe275.user.UserProfile;
import edu.sjsu.cmpe275.user.UserProfileRepository;

@Service
public class InterviewDao {

	@Autowired
	private InterviewRepository interviewRepository;
	
	public Interview addInterview(Interview a){
		return interviewRepository.save(a);
	}
	
	public List<Interview> findAllInterviewsByUserId(int userId){
		List<Interview> interviews = new ArrayList<Interview>();
		interviews=interviewRepository.findAllByUserId(userId);
		return interviews;
	}
	
	public Interview findInterview(int id){
		return interviewRepository.findOne(id);
	}
	
	public Interview updateInterview(Interview interview){
		return interviewRepository.save(interview);
	}
	
	public List<Interview> findAllInterviewsByPositionId(int position_id){
		List<Interview> interviews = new ArrayList<Interview>();
		interviews=interviewRepository.findAllByPositionId(position_id);
		return interviews;
	}

	public List<Interview> findAllInterviewsByPosition(CompanyPosition p) {
		List<Interview> interviews = new ArrayList<Interview>();
		interviews=interviewRepository.findAllInterviewsByPosition(p);
		return interviews;
	}
	
	
}
