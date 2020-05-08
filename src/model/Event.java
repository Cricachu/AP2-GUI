package model;

import model.utilities.Status;

import java.awt.*;
import java.util.ArrayList;


public class Event extends Post {
	private static int eventCount;
	
	private String venue;
	private String date;
	private int capacity;
	private ArrayList<String> attendees=new ArrayList<String>();
	private int attendeesCount;
	
	public Event(String userID, String title, String description, String venue, String date, int capacity) {
		super(userID, title, description);
		eventCount++;
		super.setID("EVE" + String.format("%0" + 3 + "d", eventCount));
		this.attendeesCount=0;
		this.date=date;
		this.venue=venue;
		this.capacity=capacity;
	}


	
	//implement abstract methods
	public  boolean handleReply(Reply reply) {
		
		String studentId=reply.getResponderID();
		if(!attendees.contains(studentId) && attendeesCount<capacity &&this.getStatus()== Status.OPEN) {
			super.getArrayReply().add(reply);
			attendees.add(studentId);
			attendeesCount++;
			if (attendeesCount==capacity) {
				super.closePost();
			}
			return true;
		} else if (attendees.contains(studentId)){
			System.out.println("You have already joined");
			return false;
		} else {
			return false;
		}	
	
	}
	

	public void printDetailsforReply() {
		System.out.println("Name:" +super.getTitle());
		System.out.println("Venue:"+ this.venue);
		System.out.println("Capacity:" + this.capacity);
		
		
	}
	public String getReplyDetails() {
		String replyDetails="Attendee List:";
		for (int i = 0; i < attendees.size(); i++) {
			replyDetails+= attendees.get(i)+ ",";
		}
		return replyDetails+ "\n"+ "--------------";
	}

	//overriden methods
	public String getPostDetails() {
		return super.getPostDetails() + "Venue: "+ "\t"+"\t"+ this.venue+ "\n"+ "Date: "+"\t"+"\t"+  this.date+ "\n"+ "Capacity: " +"\t"+  this.capacity+ "\n"+ "Attendees: "+ "\t"+ this.attendeesCount+ "\n"+ "--------------";
		
	}
	

	
	//mutators/accessors
	public void setAttendeesCount(int number) {
		this.attendeesCount=number;
	}
	
	public ArrayList<String> getAttendees(){
		return attendees;
	}

	public String getVenue() {
		return venue;
	}

	public String getDate() {
		return date;
	}

	public int getCapacity() {
		return capacity;
	}
	public int getAttendeesCount() {
		return attendeesCount;
	}

	public void setVenue(String str) {
		this.venue=str;
	}
	public void setDate(String str) {
		this.date=str;
	}

	public void setCapacity(int num) {
		this.capacity=num;
	}
}
