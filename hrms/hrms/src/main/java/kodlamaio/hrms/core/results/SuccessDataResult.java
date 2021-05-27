package kodlamaio.hrms.core.results;

public class SuccessDataResult<T> extends DataResult<T>{

	public SuccessDataResult(T data, String message) {
		super(data,true, message);
		// TODO Auto-generated constructor stub
	}
	
	public SuccessDataResult(T data) {
		super(data,true);
	}

	public SuccessDataResult() {
		super(null,true);
	}
	
	public SuccessDataResult(String message) {
		super(null,true,message);
	}
}
