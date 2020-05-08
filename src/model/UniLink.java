package model;

import javafx.scene.image.Image;
import model.exceptions.FormatException;
import model.exceptions.NameException;

import java.util.ArrayList;
import java.util.Collection;

public class UniLink {

    private static String titleee;
    private static String desc;
    private static String venue;
    private static String date;
    private static int capa;
    private static String tempDetails;

    ArrayList<Post> allPosts = new ArrayList<Post>();

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

    public static void closePost(Post post) {
        post.closePost();
    }
    public ArrayList<Post> getAllPosts () {
        return allPosts;
    }
}