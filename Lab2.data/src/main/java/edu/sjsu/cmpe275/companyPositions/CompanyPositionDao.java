package edu.sjsu.cmpe275.companyPositions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CompanyPositionDao 
{
	
	@Autowired
	private CompanyPositionRepository companyPositionRepository;
	
	public CompanyPosition addCompanyPosition(CompanyPosition a)
	{
		return companyPositionRepository.save(a);
	}
	
	public List<CompanyPosition> findAllCompanyPosition()
	{
		List<CompanyPosition> companyPositionList = new ArrayList<CompanyPosition>();
		companyPositionRepository.findAll().forEach(companyPositionList::add);
		return companyPositionList;
	}
	
	public List<String> findAllJobLocations(){
		
		List<String> al=companyPositionRepository.findAllJobLocations();
		return al;
	}
	
	
	public CompanyPosition findCompanyPosition(int position_id){
		return companyPositionRepository.findOne(position_id);
	}
	
	public CompanyPosition updateCompanyPosition(CompanyPosition companyPosition){
		return companyPositionRepository.save(companyPosition);
	}
	public List<CompanyPosition> findByCompany(int company_id)
	{
		return companyPositionRepository.findAllByCompany(company_id);
	}
	
	void deleteCompanyPosition(int position_id){
		companyPositionRepository.delete(position_id);
	}

}
