package kodlamaio.hrms.dataAccess.abstracts;




import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import kodlamaio.hrms.entities.concretes.JobTitle;


public interface JobTitleDao extends JpaRepository<JobTitle,Integer>{

    //List<JobTitle> findJobTitles(@Param("title") String title);
	
	//Optional<JobTitle> findOneByTitle(@Param("title") String title);
	
	List<JobTitle> findAllByTitle(String title);
}
