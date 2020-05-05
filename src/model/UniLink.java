package model;

import model.exceptions.FormatException;
import model.exceptions.NameException;

import java.util.ArrayList;
import java.util.Collection;

public class UniLink {

    ArrayList<Post> allPosts=new ArrayList<Post>();


    public static boolean login(String CurrentSessionId) throws Exception {
            boolean success=false;

                if(CurrentSessionId.trim().length()==0) {
                    throw new FormatException("You must enter a text value");

                }

                System.out.println(CurrentSessionId.charAt(0));
                if (CurrentSessionId.charAt(0) != 's'&& CurrentSessionId.charAt(0) !='S') {
                    throw new NameException("Wrong user ID format");

                }


                success=true;
                return success;

        }


    public ArrayList<Post> getAllPosts() {
        return allPosts;
    }
}
