package edu.sjsu.cmpe275.mail;

import java.util.Calendar;

public class EventDocument implements IEventDocument{
	private String title;
	private Calendar date;
	private Calendar endDate;
	private String summary;
	
	private String name;
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public Calendar getDate() {
		// TODO Auto-generated method stub
		return date;
	}

	@Override
	public Calendar getEndDate() {
		// TODO Auto-generated method stub
		return endDate;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return summary;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getCanonicalHandleUUID() {
		// TODO Auto-generated method stub
		return "abc";
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setName(String name) {
		this.name = name;
	}

}
