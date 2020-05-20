package model.database;

import java.sql.Connection;
import java.sql.Statement;

public class InsertRow {

    public static void toReply(String insertQuery) {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "REPLY";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = insertQuery;

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void toPost(String insertIntoPost) {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "POST";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = insertIntoPost;

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //insert to Sale table
    public static void toSale(String insertIntoSale) {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "SALE";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = insertIntoSale;

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //insert to Job
    public static void toJob(String jobquery) {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "JOB";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = jobquery;

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //insert to Event
    public static void toEvent(String insertIntoEvent) {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "EVENT";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = insertIntoEvent;

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
