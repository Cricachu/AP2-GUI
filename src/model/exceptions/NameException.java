package model.exceptions;


public class NameException extends Exception {
    private String reason;

    public NameException (String reason) {
        this.reason=reason;
    }

    public String getReason() {
        return reason;
    }
}

