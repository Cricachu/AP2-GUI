package model.exceptions;

public class PostIDException extends Exception{
private String reason;
	
	public PostIDException(String reason) {
		this.reason=reason;
	}
	
	public String getReason() {
			return reason;
			}
}
