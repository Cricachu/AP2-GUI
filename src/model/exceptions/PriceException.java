package model.exceptions;

public class PriceException extends Exception {
    private String reason;

    public PriceException (String reason) {
        this.reason=reason;
    }

    public String getReason() {
        return reason;
    }
}
