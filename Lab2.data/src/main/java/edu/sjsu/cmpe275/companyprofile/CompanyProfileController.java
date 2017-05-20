package edu.sjsu.cmpe275.companyprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyProfileController 
{
	@Autowired
	public CompanyProfileDao companyProfileDao;
	
	public CompanyProfile getCompanyProfile(int company_id)
	{
		return companyProfileDao.findCompanyProfile(company_id);
	}
	
	public CompanyProfile checkLogin(String company_email)
	{
		CompanyProfile companyProfile=companyProfileDao.findByEmail(company_email);
		return companyProfile;	
	}

	
	public CompanyProfile createCompanyProfile(String company_email, String password, String company_name, String company_website, String logo_url, String address, String description) throws Exception{
		CompanyProfile company = new CompanyProfile(company_email, password, company_name, company_website, logo_url, address, description);
		try
		{
			CompanyProfile companyProfile=companyProfileDao.addCompanyProfile(company);
			return companyProfile;
		}
		catch(Exception e)
		{
			throw new Exception("general exception");
		}
	}
	
	
	public CompanyProfile updateCompanyProfile(int company_id, String company_email, String password, String company_name, String company_website, String logo_url, String address, String description)
	{
		CompanyProfile companyProfile = getCompanyProfile(company_id);
		if(companyProfile==null) return companyProfile;
		if(company_email!=null) companyProfile.setCompany_email(company_email);
		if(password != null) companyProfile.setPassword(password);
		if(company_name!=null) companyProfile.setCompany_name(company_name);
		if(company_website!=null) companyProfile.setCompany_website(company_website);
		
		if(logo_url!=null) companyProfile.setLogo_url(logo_url);

		if(address!=null) companyProfile.setAddress(address);
		if(description!=null) companyProfile.setDescription(description);
		
		return companyProfileDao.updateCompanyProfile(companyProfile);
	}
	
	public void deleteCompanyProfile(int company_id)
	{	
		companyProfileDao.deleteCompanyProfile(company_id);;
	}
	
	
}
