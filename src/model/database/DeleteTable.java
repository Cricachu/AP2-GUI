package model.database;

import java.sql.Connection;
import java.sql.Statement;

public class DeleteTable {

    public static void deleteReply(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "REPLY";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("DROP TABLE REPLY");

            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been deleted successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " was not deleted");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void deletePost(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "POST";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("DROP TABLE POST");

            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been deleted successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " was not deleted");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteEvent(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "EVENT";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("DROP TABLE EVENT");

            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been deleted successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " was not deleted");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteSale() {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "SALE";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("DROP TABLE SALE");

            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been deleted successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " was not deleted");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteJob(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "JOB";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("DROP TABLE JOB");

            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been deleted successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " was not deleted");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
