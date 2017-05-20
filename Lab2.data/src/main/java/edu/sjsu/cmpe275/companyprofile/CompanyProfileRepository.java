package edu.sjsu.cmpe275.companyprofile;
import org.springframework.data.repository.CrudRepository;

public interface CompanyProfileRepository extends CrudRepository<CompanyProfile,Integer>
{
	CompanyProfile findByEmail(String email);
}
