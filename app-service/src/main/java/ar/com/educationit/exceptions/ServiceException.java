package ar.com.educationit.exceptions;

public class ServiceException extends Exception {

	private String msj;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}	
	
	public void setMsj(String msj) {
		this.msj = msj;
	}
	
	public String getMsj() {
		return this.msj;
	}
}
