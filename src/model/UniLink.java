package model;

import javafx.scene.image.Image;
import model.database.DeleteRow;
import model.database.InsertRow;
import model.exceptions.FormatException;
import model.exceptions.NameException;
import model.exceptions.PriceException;
import model.exceptions.ValueException;

import java.util.ArrayList;
import java.util.Collection;

public class UniLink {

    private static String titleee;
    private static String desc;
    private static String venue;
    private static String date;
    private static int capa;
    private static String tempDetails;


    private static String saleTitle;
    private static String saleDesc;
    private static double saleAskPrice;
    private static double saleMinPrice;


    static ArrayList<Post> allPosts = new ArrayList<Post>();

    //store updated information when user updates a Sale
    public static void passNewSaleInfor(String stitle,String sdec, double sask, double smin) {
        saleTitle=stitle;
        saleDesc=sdec;
        saleAskPrice=sask;
        saleMinPrice=smin;
    }

    //update the sale
    public static void updateSale(Sale post) {
        post.setTitle(saleTitle);
        post.setDescription(saleDesc);
        post.setAskingPrice(saleAskPrice);
        post.setMinimumRaise(saleMinPrice);
    }

    //store updated information when user updates an Event
    public static void passNewEventInfo(String ptit,String pdesc,String pvenue,String pdate,int pcaca){
        titleee=ptit;
        desc=pdesc;
        venue=pvenue;
        date=pdate;
        capa=pcaca;
    }
    public static void testInfo(Post post){
        System.out.println(titleee+capa+ "State:"+post.getState());
    }

    //update the event
    public static void updateEvent(Event post) {
        post.setTitle(titleee);
        post.setDescription(desc);
        post.setVenue(venue);
        post.setDate(date);
        post.setCapacity(capa);
    }

    //delete post
    public void deletePost(Post post) {
        allPosts.remove(post);
    }

    public static boolean login(String CurrentSessionId) throws Exception {
        boolean success = false;

        if (CurrentSessionId.trim().length() == 0) {
            throw new FormatException("You must enter a text value");

        }

        System.out.println(CurrentSessionId.charAt(0));
        if (CurrentSessionId.charAt(0) != 's' && CurrentSessionId.charAt(0) != 'S') {
            throw new NameException("Wrong user ID format");

        }


        success = true;
        return success;

    }

    public void replyToJob(String postId,String saleOffer, String userId,Job post) throws NumberFormatException,Exception {
        double offer=addPrice(saleOffer);
        Reply reply= new Reply(postId,offer,userId);
        if(!post.handleReply(reply)) {
            throw new Exception("Offer must be lower than lowest offer.");
        }
    }

    public void replyToSale(String postId,String saleOffer, String userId,Sale post) throws ValueException, PriceException,NumberFormatException {
        double offer= addPrice(saleOffer);
        Reply reply= new Reply(postId,offer,userId);
        post.handleReply(reply);

    }

    public void createNewJob(String userId, String title, String desc, String proposedPrice,Image photo) throws Exception{
        addTextInfo(title);
        addTextInfo(desc);
        double proposed=addPrice(proposedPrice);

        Job job = new Job(userId,title,desc,proposed);
        job.setPhoto(photo);
        allPosts.add(job);
    }

    public void createNewSale(String userId, String title, String desc, String askingPrice, String minRaise,Image photo) throws Exception {
        addTextInfo(title);
        addTextInfo(desc);
        double askPrice=addPrice(askingPrice);
        double minimumRaise=addPrice(minRaise);

        Sale sale= new Sale(userId,title,desc,askPrice,minimumRaise);
        sale.setPhoto(photo);
        allPosts.add(sale);
    }

    public void createNewEvent(String userId, String title, String desc, String venue, String date, String capacity, Image photo) throws Exception{

        addTextInfo(title);
        addTextInfo(desc);
        addTextInfo(venue);
        addDate(date);
        int capa=addCapacity(capacity) ;

        Event event= new Event(userId,title,desc,venue,date,capa);
        event.setPhoto(photo);
        allPosts.add(event);
    }

    public static double addPrice(String amount) throws NumberFormatException{
        double price=0;
        try{
            price=Double.parseDouble(amount);
        } catch(Exception e) {
            throw new NumberFormatException("Wrong format for pricing");
        }
        return price;
    }

    public static void addTextInfo(String info) throws Exception {

        if (info.trim().length() == 0) {
            throw new FormatException("The field must not be blank");
        }

    }

    public static int addCapacity(String cap) throws Exception {
        int capa=0;
        try {
            capa = Integer.parseInt(cap);

        } catch (Exception e) {
            throw new Exception("Wrong format for capacity");
        }
        return capa;
    }

    public static void addDate(String date) throws Exception {

        if (date.length() == 0) {
            throw new FormatException("You must enter a text value");
        }


        if (date.trim().length() == 0) {
            throw new FormatException("The field must not be blank");
        }

        if (!checkDateFormat(date)) {
            throw new FormatException("Date must be in correct format: dd/mm/yyyy");
        }

    }

    public static boolean checkDateFormat(String date) {
        if (date.matches("\\d{2}+/\\d{2}+/\\d{4}")) {
            return true;
        } else {
            return false;
        }



    }

    public static void writeToDatabase() {
        DeleteRow.deleteAllEvent();
        DeleteRow.deleteAllSale();
        DeleteRow.deleteAllJob();
        DeleteRow.deleteAllReply();
        DeleteRow.deleteAllPost();

        for (Post post: allPosts)
        {
            String id= post.getID();
            String creatorId= post.getCreatorId();
            String title= post.getTitle();
            String description=post.getDescription();
            String status=post.getStatus().toString();
            String queryPost="INSERT INTO " + "POST"+
                    " VALUES ("+ "'"+id+"'"+","+
                    "'"+creatorId+"'"+","+
                    "'"+title+"'"+","+
                    "'"+description+"'"+","+
                    "'"+status+"'"+
                    ")";
            System.out.println(queryPost);
            InsertRow.toPost(queryPost);

            if (post instanceof Event) {

                String venue= ((Event)post).getVenue();
                String date= ((Event)post).getDate();
                int capacity= ((Event)post).getCapacity();
                int eveCount= ((Event)post).getEventCount();

                String queryEvent="INSERT INTO " + "EVENT"+
                        " VALUES ("+ "'"+id+"'"+","+
                        "'"+venue+"'"+","+
                        "'"+date+"'"+","+
                        "'"+capacity+"'"+","+
                        "'"+eveCount+"'"+")";

                System.out.println(queryEvent);
                InsertRow.toEvent(queryEvent);



                //write reply to database
                ArrayList<Reply> allReps= post.getArrayReply();
                for(Reply rep: allReps){
                    String replyID=rep.getReplyID();
                    String postID=rep.getPostId();
                    double value=rep.getPostValue();
                    String responder=rep.getResponderID();
                    int repCount=rep.getReplyCount();

                    String queryReply="INSERT INTO " + "REPLY"+
                            " VALUES ("+ "'"+replyID+"'"+","+
                            "'"+postID+"'"+","+
                            "'"+value+"'"+","+
                            "'"+responder+"'"+","+
                            "'"+repCount+"'"+ ")";

                    System.out.println(queryReply);
                    InsertRow.toReply(queryReply);
                }
            } else if(post instanceof Job) {
                double jprice= ((Job)post).getProposedPrice();
                double lowest= ((Job)post).getLowestOffer();
                int jobCount=((Job)post).getJobCount();
//					String status=post.getStatus().toString();

                String queryJob="INSERT INTO " + "JOB"+
                        " VALUES ("+ "'"+id+"'"+","+
                        "'"+jprice+"'"+","+
                        "'"+lowest+"'"+","+
                        "'"+jobCount+"'"+")";
                System.out.println(queryJob);
                InsertRow.toJob(queryJob);



                //write reply to database
                ArrayList<Reply> allReps= post.getArrayReply();
                for(Reply rep: allReps){
                    String replyID=rep.getReplyID();
                    String postID=rep.getPostId();
                    double value=rep.getPostValue();
                    String responder=rep.getResponderID();
                    int repCount=rep.getReplyCount();

                    String queryReply="INSERT INTO " + "REPLY"+
                            " VALUES ("+ "'"+replyID+"'"+","+
                            "'"+postID+"'"+","+
                            "'"+value+"'"+","+
                            "'"+responder+"'"+","+
                            "'"+repCount+"'"+ ")";

                    System.out.println(queryReply);
                    InsertRow.toReply(queryReply);
                }

            }else if(post instanceof Sale) {
                double price= ((Sale)post).getAskingPrice();
                double min= ((Sale)post).getMinimumRaise();
                double highest= ((Sale)post).getHighestOffer();
                int saleCount=((Sale)post).getSaleCount();
//					String status=post.getStatus().toString();

                String querySale="INSERT INTO " + "SALE"+
                        " VALUES ("+ "'"+id+"'"+","+
                        "'"+price+"'"+","+
                        "'"+min+"'"+","+
                        "'"+highest+"'"+","+
                        "'"+saleCount+"'"+")";
                System.out.println(querySale);
                InsertRow.toSale(querySale);



                //write reply to database
                ArrayList<Reply> allReps= post.getArrayReply();
                for(Reply rep: allReps){
                    String replyID=rep.getReplyID();
                    String postID=rep.getPostId();
                    double value=rep.getPostValue();
                    String responder=rep.getResponderID();
                    int repCount=rep.getReplyCount();

                    String queryReply="INSERT INTO " + "REPLY"+
                            " VALUES ("+ "'"+replyID+"'"+","+
                            "'"+postID+"'"+","+
                            "'"+value+"'"+","+
                            "'"+responder+"'"+","+
                            "'"+repCount+"'"+ ")";

                    System.out.println(queryReply);
                    InsertRow.toReply(queryReply);
                }
            }

        }
    }


    public static void closePost(Post post) {
        post.closePost();
    }
    public ArrayList<Post> getAllPosts () {
        return allPosts;
    }
}