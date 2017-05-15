package edu.sjsu.cmpe275.job;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface JobPostingRepository extends CrudRepository<JobPosting,Integer>{

	@Query(value="Select * from Job_Posting j,Skills_Needed s,Company_Profile c where ( j.title like %:text% or s.skill like %:skill% or c.company_name like %:company% ) and (j.company_id=c.company_id and j.job_id=s.jobid) GROUP BY j.job_id", nativeQuery = true)
	List<JobPosting> findAllByTitleOrSkill(@Param("text") String text, @Param("skill") String skill, @Param("company") String company);



	@Query(value="Select * from Job_Posting j, Company_Profile c where c.company_name in ( :query )  and j.company_id=c.company_id ", nativeQuery = true)
	List<JobPosting> findAllByCompany(@Param("query") String query);
	
	@Query(value="Select * from Job_Posting j, Company_Profile c where c.location in ( :query )  and j.company_id=c.company_id ", nativeQuery = true)
	List<JobPosting> findAllByLocation(@Param("query") String query);
	
	@Query(value="Select * from Job_Posting j, Company_Profile c where c.salary > ( :query )  and j.company_id=c.company_id ", nativeQuery = true)
	List<JobPosting> findAllByMinSal(@Param("query") String query);
	
	@Query(value="Select * from Job_Posting j, Company_Profile c where c.salary< in ( :query )  and j.company_id=c.company_id ", nativeQuery = true)
	List<JobPosting> findAllByMaxSal(@Param("query") String query);
}
