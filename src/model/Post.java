package model;

import model.utilities.Status;

import java.util.ArrayList;

public abstract  class Post {
    private String ID;
    private String title;
    private String description;
    private String creatorID;
    private ArrayList<Reply> replies= new ArrayList<Reply>();
    private Status status;

    public Post(String userID, String title, String description) {
        creatorID=userID;
        this.title=title;
        this.description=description;
        this.status= Status.OPEN;
    }

    //methods for overriden
    public String getPostDetails() {
        String details=  "\n"+ "ID: " + "\t" + "\t"+this.ID+ "\n"+ "Title: " + "\t" +"\t"+ this.title+ "\n" + "Description: "+ "\t"+ this.description+ "\n" + "Creator ID " +"\t"+  this.creatorID+ "\n" + "Status :"+ "\t"+ this.status+ "\n";
//	System.out.println(details);
        return details;
    }


    public void printDetails() {
        String details=this.getPostDetails();
        System.out.println(details);
    }

    //abstract methods
    public abstract void printDetailsforReply();
    public abstract boolean handleReply(Reply reply);
    public abstract String getReplyDetails();


    //accessors/mutators
    public ArrayList<Reply> getArrayReply() {
        return replies;
    }
    public Status getStatus() {
        return this.status;
    }
    public String getTitle() {
        return title;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID=ID;
    }

    public String getCreatorId() {
        return creatorID;
    }
    public String getDescription() {return description;}

    public void closePost() {
        this.status= Status.CLOSE;
    }
    public void openPost() {
        this.status=Status.OPEN;
    }
}