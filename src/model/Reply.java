package model;


public class Reply {
    private String postID;
    private double value;
    private String responderID;

    public Reply(String postID, double value, String responderID) {
        this.postID=postID;
        this.value=value;
        this.responderID=responderID;
    }

    public String getPostId() {
        return this.postID;
    }

    public String getResponderId() {
        return this.responderID;
    }

    public double getPostValue() {
        return this.value;
    }
}


