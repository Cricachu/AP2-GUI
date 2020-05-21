package model;

import javafx.scene.image.Image;
import model.exceptions.ValueException;
import model.utilities.Status;

import java.util.ArrayList;

public abstract  class Post {
    private String ID;
    private String title;
    private String description;
    private String creatorID;
    private ArrayList<Reply> replies;
    private Status status;
    private Image photo;
    private String imageName;
    private boolean updated=false;

    public Post(String userID, String title, String description) {
        creatorID=userID;
        this.title=title;
        this.description=description;
        this.status= Status.OPEN;
       replies = new ArrayList<Reply>();
       imageName="/images/default.jpeg";
       photo=new Image(imageName);
    }

    public Post(String userID, String title, String description, Image photo) {
        creatorID=userID;
        this.title=title;
        this.description=description;
        this.status= Status.OPEN;
        replies = new ArrayList<Reply>();
        this.photo=photo;
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
    public abstract boolean handleReply(Reply reply) throws ValueException, Exception;
    public abstract String getReplyDetails();


    //accessors/mutators
    public Image getPhoto(){
        return photo;
    }

    public void setPhoto(Image image) {
        this.photo=image;
    }

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
    public void setTitle(String str) {
        this.title=str;
    }

    public void setDescription(String str) {
        this.description=str;
    }

    public void closePost() {
        this.status= Status.CLOSE;
    }
    public void openPost() {
        this.status=Status.OPEN;
    }

    public boolean getState(){
        return updated;
    }
    public void setUpdated(){
        this.updated=true;
    }
    public void setNotUpdated(){
        this.updated=false;
    }
}