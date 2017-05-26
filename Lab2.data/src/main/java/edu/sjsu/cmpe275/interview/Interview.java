package edu.sjsu.cmpe275.interview;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.sjsu.cmpe275.companyPositions.CompanyPosition;
import edu.sjsu.cmpe275.user.UserProfile;

@Entity
@Table(name="Interview")
public class Interview  implements Serializable{

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="position_id",referencedColumnName = "position_id", insertable = false, updatable = false)
	private CompanyPosition position;
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "applicant_id", insertable = false, updatable = false)
	private UserProfile user;
	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	@Column(name="user_id")
	private int userId;
	@Column(name="position_id")
	private int positionId;
	
	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	private Date startTime;
	
	private int interviewLength;
	
	public int getInterviewLength() {
		return interviewLength;
	}

	public void setInterviewLength(int interviewLength) {
		this.interviewLength = interviewLength;
	}

	private String location;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CompanyPosition getPosition() {
		return position;
	}

	public void setPosition(CompanyPosition position) {
		this.position = position;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}
