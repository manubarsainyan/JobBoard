package edu.sjsu.cmpe275.companyprofile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyProfileDao 
{

	@Autowired
	private CompanyProfileRepository companyProfileRepository;
	
	public CompanyProfile addCompanyProfile(CompanyProfile a)
	{
		return companyProfileRepository.save(a);
	}
	
	public List<CompanyProfile> findAllCompanyProfile(){
		List<CompanyProfile> companyProfileList = new ArrayList<CompanyProfile>();
		companyProfileRepository.findAll().forEach(companyProfileList::add);
		return companyProfileList;
	}
	
	public CompanyProfile findCompanyProfile(int company_id){
		return companyProfileRepository.findOne(company_id);
	}
	
	
	
	public CompanyProfile updateCompanyProfile(CompanyProfile companyProfile){
		return companyProfileRepository.save(companyProfile);
	}
	
	void deleteCompanyProfile(int company_id){
		companyProfileRepository.delete(company_id);
	}
	
	public CompanyProfile findByEmail(String company_email){
		return companyProfileRepository.findByEmail(company_email);
	}
	
	
}
