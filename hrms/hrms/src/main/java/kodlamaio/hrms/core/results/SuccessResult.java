package kodlamaio.hrms.core.results;

public class SuccessResult extends Result{
	
	public SuccessResult() {
		 super(true);
	 }

	public SuccessResult(boolean success) {
		super(success);
		// TODO Auto-generated constructor stub
	}
	
	public SuccessResult(String message) {
        super(true, message);
    }

}
