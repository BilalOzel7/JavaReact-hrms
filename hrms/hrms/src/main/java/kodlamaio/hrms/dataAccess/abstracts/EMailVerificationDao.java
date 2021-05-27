package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.EMailVerificationCode;

public interface EMailVerificationDao extends JpaRepository<EMailVerificationCode, Integer>{

}
