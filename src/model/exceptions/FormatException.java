package model.exceptions;


public class FormatException extends Exception {
    private String reason;

    public FormatException (String reason) {
        this.reason=reason;
    }

    public String getReason() {
        return reason;
    }
}
