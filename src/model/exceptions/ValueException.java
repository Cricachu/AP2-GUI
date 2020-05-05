package model.exceptions;

public class ValueException extends Exception {
private String reason;
	
	public ValueException (String reason) {
		this.reason=reason;
	}
	
	public String getReason() {
			return reason;
			}
}
