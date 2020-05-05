package model;

import model.utilities.Status;

import java.util.ArrayList;

public class Job extends Post {
private static int jobCount;
private double proposedPrice;
private double lowestOffer;

public Job(String userID, String title, String description, double proposedPrice) {
	super(userID, title, description);
	jobCount++;
	super.setID("JOB" + String.format("%0" + 3 + "d", jobCount));
	this.proposedPrice=proposedPrice;
	lowestOffer=this.proposedPrice;
}

//accessors+ mutators




//abstract methods
public void printDetailsforReply() {
	System.out.println("Name: "+ "\t"+ super.getTitle());
	System.out.println("Proposed price:"+ "\t"+ proposedPrice);
	System.out.println("Lowest offer:"+ "\t"+ lowestOffer);
}
public boolean handleReply(Reply reply) {
	
	if(reply.getPostValue()< lowestOffer && super.getStatus()== Status.OPEN) {
		super.getArrayReply().add(reply);
		lowestOffer=reply.getPostValue();
		System.out.println("Offer Accepted");
		return true;
	}
	else {
//		System.out.println("Offer not accepted");
		return false;
	}
	
}

public  String getReplyDetails() {
//	String details=null;
	String offerHistory="--Offer History--"+"\n";
	int historyCount=0;

	try {	
			if(super.getArrayReply().size()>0) {

				for (int i = super.getArrayReply().size()-1; i >=0; i--) {
					offerHistory+= super.getArrayReply().get(i).getResponderId()+":"+ super.getArrayReply().get(i).getPostValue()+"\n";
					historyCount++;
					if(historyCount==5) break;
				}
			} else {
				offerHistory+= "Empty";
			}
			
//			details += offerHistory;
		
	}catch (Exception e) {
		System.out.println("nothing");
		e.printStackTrace();
	}
	return offerHistory+"\n"+ "--------------";	
}



//overriden

public String getPostDetails() {
	return super.getPostDetails()+ "Proposed price: "+ this.proposedPrice+ "\n" + "Lowest offer: "+"\t"+  this.lowestOffer + "\n"+ "--------------";
}



//others




}
