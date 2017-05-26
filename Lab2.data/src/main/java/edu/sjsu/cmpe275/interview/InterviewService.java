package edu.sjsu.cmpe275.interview;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.companyPositions.CompanyPosition;

@Service
public class InterviewService {
@Autowired
private InterviewDao interviewDao;

public Interview addInterview(Interview interview) {
	return interviewDao.addInterview(interview);
}

public List<Interview> getInterviewByPositionId(int position_id) {
	// TODO Auto-generated method stub
	return interviewDao.findAllInterviewsByPositionId(position_id);
}

public List<Interview> getInterviewByUserId(int user_id) {
	// TODO Auto-generated method stub
	return interviewDao.findAllInterviewsByUserId(user_id);
}

public Interview getInterviewById(int id) {
		return interviewDao.findInterview(id);
}

public Interview updateInterview(Interview interview) {
	// TODO Auto-generated method stub
	return interviewDao.updateInterview(interview);
}

public List<Interview> getInterviewByPosition(CompanyPosition p) {
	// TODO Auto-generated method stub
	return interviewDao.findAllInterviewsByPosition(p);
}

}
