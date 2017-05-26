package edu.sjsu.cmpe275.companyPositions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CompanyPositionController 
{
	
	@Autowired
	public CompanyPositionDao companyPositionDao;
	
	public CompanyPosition getCompanyPosition(int position_id)
	{
		return companyPositionDao.findCompanyPosition(position_id);
	}
	
	public List<CompanyPosition> getPositionsByCompanyId(int company_id)
	{
		List<CompanyPosition> companyPosition=companyPositionDao.findByCompany(company_id);
		return companyPosition;	
	}
	
	public CompanyPosition createCompanyPosition(int company_id,String position_title, String position_description, String position_responsibility, String office_location, String salary, String status) throws Exception{
		CompanyPosition position = new CompanyPosition(company_id,position_title, position_description, position_responsibility, office_location, salary, status);
		try
		{
			CompanyPosition companyPosition=companyPositionDao.addCompanyPosition(position);
			return companyPosition;
		}
		catch(Exception e)
		{
			throw new Exception("general exception");
		}
	}
	
	public CompanyPosition updateCompanyPosition(int position_id,int company_id, String position_title, String position_description, String position_responsibility, String office_location, String salary, String status)
	{
		CompanyPosition companyPosition = getCompanyPosition(position_id);
		if(companyPosition==null) return companyPosition;
		if(position_title !=null) companyPosition.setPosition_title(position_title);
		if(position_description!=null) companyPosition.setPosition_description(position_description);
		
		if(position_responsibility!=null) companyPosition.setPosition_responsibility(position_responsibility);
//		if(profilePicture!=null) userProfile.setProfilePicture(profilePicture);
		if(office_location!=null) companyPosition.setOffice_location(office_location);
		if(salary!=null) companyPosition.setSalary(salary);
		if(salary!=null) companyPosition.setStatus(status);
		
		return companyPositionDao.updateCompanyPosition(companyPosition);
	}
	

}
