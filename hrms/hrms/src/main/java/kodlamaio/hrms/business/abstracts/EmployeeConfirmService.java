package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.results.DataResult;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.EmployeeConfirm;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployeeConfirmService {
	
	DataResult<EmployeeConfirm> confirmEmployer(Employee employee, Employer confirmedEmployer);

}
