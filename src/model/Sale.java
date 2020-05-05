package model;

import model.utilities.Status;

import java.util.ArrayList;
import java.util.*;

public class Sale extends Post {
private static int saleCount;	
private double askingPrice;
private double minimumRaise;
private double highestOffer;
private ArrayList<Double> allPrice= new ArrayList<Double>();
static private Comparator<Reply> offer ;

public Sale(String userID, String title, String description, double askingPrice, double minimumRaise) {
	super(userID, title, description);
	saleCount++;
	super.setID("SAL" + String.format("%0" + 3 + "d", saleCount));
	this.askingPrice=askingPrice;
	this.minimumRaise=minimumRaise;
	this.highestOffer=0;
}

//accessors/mutators
public double getAskingPrice() {
	return askingPrice;
}

public double getMinimumRaise() {
	return minimumRaise;
}

public ArrayList<Double> getAllPrice(){
	return allPrice;
}
//implement abstract methods
public boolean handleReply(Reply reply) {
	this.highestOffer=findMaxPrice(allPrice);
	double currentPrice=reply.getPostValue();
	
	if(currentPrice>askingPrice && super.getStatus()== Status.OPEN ) {
		super.getArrayReply().add(reply);
		highestOffer=currentPrice;
		allPrice.add(currentPrice);
		System.out.println("Congratulation. The "+ super.getTitle()+" has been sold to you");
		System.out.println("Please contact owner "+ super.getCreatorId()+" for more detail");
		super.closePost();
		return true;
	} else if (currentPrice>highestOffer &&super.getStatus()== Status.OPEN && (currentPrice-highestOffer)>= this.minimumRaise) {
		super.getArrayReply().add(reply);
		highestOffer=currentPrice;
		allPrice.add(currentPrice);
		System.out.println("Your offer has been submitted");
		System.out.println("However, your offer is below the asking price");
		System.out.println("The item is still on sale");
		return true;
	} else return false;
}

public String getReplyDetails() {
//		sortReply();

		String details=null;
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
				
				details="Asking price:" + "\t"+ this.askingPrice+ "\n";
				details += offerHistory;
			
		}catch (Exception e) {
			System.out.println("nothing");
			e.printStackTrace();
		}
		return details+"\n"+ "--------------";		
}

public void printDetailsforReply() {
	System.out.println("Name:" + super.getTitle());
	System.out.println("Highest offer:" + this.highestOffer);
	System.out.println("Minimum raise:" + this.minimumRaise);
	
}

//overriden methods
public String getPostDetails() {
	if(this.highestOffer==0) {
	return super.getPostDetails() + "Minimum raise: "+ "\t"+ this.minimumRaise+ "\n" + "Highest offer: "+"\t"+  "No Offer" + "\n"+ "--------------";
	}
	else 	{
		return super.getPostDetails() + "Minimum raise: "+ "\t"+ this.minimumRaise+ "\n" + "Highest offer: "+ "\t"+ this.highestOffer + "\n"+ "--------------";
	}
}


//others
public static double findMaxPrice(ArrayList<Double> list) {
	double highest=0;
	for (int i = 0; i < list.size(); i++) {
		if(list.get(i)>highest) {
			highest=list.get(i);
		}
	}
	return highest;
}

static {
	offer=  new Comparator<Reply>() {
		 public int compare(Reply rep1, Reply rep2){
             return Double.compare(rep1.getPostValue(), rep2.getPostValue());
         }
	
	};
}
public void sortReply () {
	Collections.sort(super.getArrayReply(), offer);
}

}