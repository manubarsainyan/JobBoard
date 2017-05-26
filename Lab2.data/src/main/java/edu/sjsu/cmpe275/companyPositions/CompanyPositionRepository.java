package edu.sjsu.cmpe275.companyPositions;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.companyprofile.CompanyProfile;
import edu.sjsu.cmpe275.job.JobPosting;

public interface CompanyPositionRepository extends CrudRepository<CompanyPosition, Integer> {
	List<CompanyPosition> findAllByCompany(int company_id);
	
	@Query(value="Select * from company_position j,skills_needed s,company_profile c where ( j.position_title like %:text% or s.skill like %:skill% or c.company_name like %:company% ) and (j.company_id=c.company_id and j.position_id=s.position_id) GROUP BY j.position_id", nativeQuery = true)
	List<CompanyPosition> findAllByTitleOrSkill(@Param("text") String text, @Param("skill") String skill, @Param("company") String company);


	@Query(value = "Select * from company_position j, company_profile c where c.company_name in ( :query )  and j.company_id=c.company_id ", nativeQuery = true)
	List<CompanyPosition> findAllByCompany(@Param("query") String query);
	
	@Query(value="Select * from company_position j, company_profile c where c.location in ( :query )  and j.company_id=c.company_id ", nativeQuery = true)
	List<CompanyPosition> findAllByLocation(@Param("query") String query);
	
	@Query(value="Select * from company_position j, company_profile c where c.salary > ( :query )  and j.company_id=c.company_id ", nativeQuery = true)
	List<CompanyPosition> findAllByMinSal(@Param("query") String query);

	@Query(value = "Select * from company_position j, company_profile c where c.salary< in ( :query )  and j.company_id=c.company_id ", nativeQuery = true)
	List<CompanyPosition> findAllByMaxSal(@Param("query") String query);

	@Query(value = "Select DISTINCT office_location from company_position", nativeQuery = true)
	List<String> findAllJobLocations();
	
	@Query(value = "SELECT * FROM company_position where position_id in (select job_id from mark_jobs where applicant_id in ( :userId ));", nativeQuery = true)
	List<CompanyPosition> findAllMarkedJobs(@Param("userId") int userId);
	//Search Applied jobs by Status
	
	@Query(value = "select * from company_position where position_id in (select job_id from application where applicant_id=( :userId ) and status=( :status ));", nativeQuery = true)
	List<CompanyPosition> findAppliedJobs(@Param("userId") int userId,@Param("status") String status);

}
