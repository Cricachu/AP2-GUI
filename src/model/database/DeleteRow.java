package model.database;

import java.sql.Connection;
import java.sql.Statement;

public class DeleteRow {

    public static void deleteAllPost(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "POST";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "DELETE FROM " + TABLE_NAME ;

            int result = stmt.executeUpdate(query);

            System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAllJob(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "JOB";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "DELETE FROM " + TABLE_NAME ;

            int result = stmt.executeUpdate(query);

            System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void deleteAllReply(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "REPLY";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "DELETE FROM " + TABLE_NAME ;

            int result = stmt.executeUpdate(query);

            System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteAllEvent(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "EVENT";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "DELETE FROM " + TABLE_NAME ;

            int result = stmt.executeUpdate(query);

            System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAllSale() {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "SALE";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "DELETE FROM " + TABLE_NAME ;

            int result = stmt.executeUpdate(query);

            System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
