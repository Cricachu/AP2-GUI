package model.database;

import model.Post;
import model.database.ConnectionTest;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {

    public static void newPost() {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "POST";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("CREATE TABLE POST ("
                    + "id VARCHAR(6) NOT NULL,"
                    + "creatorID VARCHAR(10) NOT NULL,"
                    + "title VARCHAR (50) NOT NULL,"
                    + "description VARCHAR(500) NOT NULL,"
                    +"status VARCHAR(6) NOT NULL,"
                    + "PRIMARY KEY (id))");
            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void newReply() {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "REPLY";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("CREATE TABLE REPLY ("
                    + "id VARCHAR(6) NOT NULL,"
                    + "postId VARCHAR(6) NOT NULL,"
                    + "value DOUBLE NOT NULL,"
                    +"responderId VARCHAR(10) NOT NULL,"
                    + "repcount INT NOT NULL,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (postId) REFERENCES POST(id))");
            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void newSale() {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "SALE";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("CREATE TABLE SALE ("
                    + "id VARCHAR(6) NOT NULL,"
                    + "price DOUBLE NOT NULL,"
                    + "min DOUBLE NOT NULL,"
                    + "highest DOUBLE NOT NULL,"
                    + "salecount INT NOT NULL,"
//                    +"status VARCHAR(6) NOT NULL,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (id) REFERENCES POST(id))");
            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void newJob() {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "JOB";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("CREATE TABLE JOB ("
                    + "id VARCHAR(6) NOT NULL,"
                    + "jprice DOUBLE NOT NULL,"
//                    + "jmin DOUBLE NOT NULL,"
                    + "jlowest DOUBLE NOT NULL,"
                    + "jobcount INT NOT NULL,"
//                    +"status VARCHAR(6) NOT NULL,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (id) REFERENCES POST(id))");
            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void newEvent(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "EVENT";

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("CREATE TABLE EVENT ("
                    + "id VARCHAR(6) NOT NULL,"
                    + "venue VARCHAR(20) NOT NULL,"
                    + "date VARCHAR(11) NOT NULL,"
                    + "capacity INT NOT NULL,"
                    + "evecount INT NOT NULL,"
//                    +"status VARCHAR(6) NOT NULL,"
                    + "PRIMARY KEY (id),"
                    + "FOREIGN KEY (id) REFERENCES POST(id))");
            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




}
