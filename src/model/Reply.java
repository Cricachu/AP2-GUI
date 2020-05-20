package model;

public class Reply {
    private String replyID;
    private static int count;
    private String postID;
    private double value;
    private String responderID;

    public Reply(String postID, double value, String responderID) {
        count++;
        this.setReplyID("REP" + String.format("%0" + 3 + "d", count));
        this.postID=postID;
        this.value=value;
        this.responderID=responderID;
    }
    public String getReplyID(){
        return this.replyID;
    }

    public String getPostId() {
        return this.postID;
    }

    public String getResponderID() {
        return this.responderID;
    }

    public double getPostValue() {
        return this.value;
    }

    public void setReplyID(String id) {
        this.replyID=id;
    }

    public int getReplyCount(){
       return count;
    }

    public void setReplyCount(int num) {
        count=num;
    }
}

