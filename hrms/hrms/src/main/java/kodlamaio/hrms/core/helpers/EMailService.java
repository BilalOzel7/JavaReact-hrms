package kodlamaio.hrms.core.helpers;

import kodlamaio.hrms.core.results.Result;

public interface EMailService  {

	 Result send(String receiver,String title, String message);
}
