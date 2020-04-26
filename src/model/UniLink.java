package model;

import model.exceptions.FormatException;
import model.exceptions.NameException;

public class UniLink {

    public boolean login(String CurrentSessionId) throws Exception {
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


}
