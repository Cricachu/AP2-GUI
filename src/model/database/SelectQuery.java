package model.database;

import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQuery {


    public static int countPost() {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "EVENT";
        int count=-1;
        //use try-with-resources Statement
        try (
                Connection con = ConnectionTest.getConnection(DB_NAME);
                Statement stmt = con.createStatement();
        ) {
            String query = "SELECT COUNT(*) as COUNT FROM POST";

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    count=resultSet.getInt("COUNT");
                    System.out.println(count);
                    return count;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public static void selectFromEvent(){
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "EVENT";

        //use try-with-resources Statement
        try (
                Connection con = ConnectionTest.getConnection(DB_NAME);
                Statement stmt = con.createStatement();
        ) {
            String query = "SELECT * FROM POST " + "LEFT JOIN "+ TABLE_NAME+ " ON POST.id=EVENT.id";

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    System.out.printf("Id: %s | CreatorID: %s | Title: %s | Description: %s| Venue: %s| Date: %s| Capacity: %d| Status: %s\n",
                            resultSet.getString("id"), resultSet.getString("creatorID"),
                            resultSet.getString("title"), resultSet.getString("description"),
                            resultSet.getString("venue"), resultSet.getString("date"),
                            resultSet.getInt("capacity"),resultSet.getString("status")
                    );
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadPost(UniLink uni) {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "ALL";
        //use try-with-resources Statement
        try (
                Connection con = ConnectionTest.getConnection(DB_NAME);
                Statement stmt = con.createStatement();
        ) {
            String query =  "SELECT * FROM POST " + "LEFT JOIN "+ " EVENT"+ " ON POST.id=EVENT.id"+ " LEFT JOIN "+ "SALE" + " ON POST.id=SALE.id" + " LEFT JOIN "+ "JOB" + " ON POST.id=JOB.id";

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    String id=resultSet.getString("id");
                    String creatorId=resultSet.getString("creatorID");
                    String title=resultSet.getString("title");
                    String description=resultSet.getString("description");
                    String venue =resultSet.getString("venue");
                    String date =  resultSet.getString("date");
                    int capacity= resultSet.getInt("capacity");
                    String status=resultSet.getString("status");
                    double price =resultSet.getDouble("price");
                    double min =resultSet.getDouble("min");
                    double highest =resultSet.getDouble("highest");

                    double jprice=resultSet.getDouble("jprice");
                    double jlowest=resultSet.getDouble("jlowest");

                    int eveCount=resultSet.getInt("evecount");
                    int saleCount=resultSet.getInt("salecount");
                    int jobCount=resultSet.getInt("jobcount");


                    if(venue!=null) {
                        //create new event
                        Event event=new Event(creatorId,title,description,venue,date,capacity);
                        event.setID(id);
                        event.setEventCount(eveCount);

                        //add Reply to event
                        addReply(event);

                        //set status
                        if(status.toUpperCase().compareTo("OPEN")==0) {
                            event.openPost();
                        } else event.closePost();

                        //add event to arrayList allPosts
                        uni.getAllPosts().add(event);
                        System.out.println("Added EVENT " + id+" to UniLink");

                    } else if(price!=0) {
                        //create new sale
                        Sale sale= new Sale(creatorId,title,description,price,min);
                        sale.setID(id);
                        sale.setSaleCount(saleCount);

                        //add Reply to sale
                        addReply(sale);
                        sale.setHighestOffer(highest);

                        //update status
                        if(status.toUpperCase().compareTo("OPEN")==0) {
                            sale.openPost();
                        } else sale.closePost();

                        //add saleto arrayList allPosts
                        uni.getAllPosts().add(sale);
                        System.out.println("Added SALE " + id+" to UniLink");

                    } else if (jprice!=0) {
                        //create new job
                        Job job = new Job(creatorId,title,description,jprice);
                        job.setID(id);
                        job.setJobCount(jobCount);

                        //add Reply to job
                        addReply(job);
                        job.setLowestOffer(jlowest);

                        //update status
                        if(status.toUpperCase().compareTo("OPEN")==0) {
                            job.openPost();
                        } else job.closePost();

                        //add job to array
                        uni.getAllPosts().add(job);
                        System.out.println("Added JOB " + id+" to UniLink");
                    }

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createEventPost(UniLink uni) {

        final String DB_NAME = "testDB";
        final String TABLE_NAME = "EVENT";
        //use try-with-resources Statement
        try (
                Connection con = ConnectionTest.getConnection(DB_NAME);
                Statement stmt = con.createStatement();
        ) {
            String query =  "SELECT * FROM POST " + "LEFT JOIN "+ TABLE_NAME+ " ON POST.id=EVENT.id";

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    String id=resultSet.getString("id");
                    String creatorId=resultSet.getString("creatorID");
                    String title=resultSet.getString("title");
                    String description=resultSet.getString("description");
                    String venue =resultSet.getString("venue");
                    String date =  resultSet.getString("date");
                    int capacity= resultSet.getInt("capacity");
                    String status=resultSet.getString("status");

                    //create new event
                    Event event=new Event(creatorId,title,description,venue,date,capacity);
                    event.setID(id);
                    if(status.toUpperCase().compareTo("OPEN")==0) {
                        event.openPost();
                    } else event.closePost();

                    //add Reply to event
                    addReply(event);

                    //add event to arrayList allPosts
                    uni.getAllPosts().add(event);
                    System.out.println("Added EVENT " + id+" to UniLink");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createSalePost(UniLink uni) {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "SALE";
        //use try-with-resources Statement
        try (
                Connection con = ConnectionTest.getConnection(DB_NAME);
                Statement stmt = con.createStatement();
        ) {
            String query =  "SELECT * FROM POST " + "LEFT JOIN "+ TABLE_NAME+ " ON POST.id=SALE.id";

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    String id=resultSet.getString("id");
                    String creatorId=resultSet.getString("creatorID");
                    String title=resultSet.getString("title");
                    String description=resultSet.getString("description");
                    double price =resultSet.getDouble("price");
                    double min =resultSet.getDouble("min");
                    double highest =resultSet.getDouble("highest");
                    String status=resultSet.getString("status");

                    //create new sale
                    Sale sale= new Sale(creatorId,title,description,price,min);
                    sale.setID(id);
                    if(status.toUpperCase().compareTo("OPEN")==0) {
                        sale.openPost();
                    } else sale.closePost();
                    sale.setHighestOffer(highest);


                    //add Reply to sale
                    addReply(sale);

                    //add saleto arrayList allPosts
                    uni.getAllPosts().add(sale);
                    System.out.println("Added SALE " + id+" to UniLink");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void addReply(Post post) {
        final String DB_NAME = "testDB";
        final String TABLE_NAME = "REPLY";
        //use try-with-resources Statement
        try (
                Connection con = ConnectionTest.getConnection(DB_NAME);
                Statement stmt = con.createStatement();
        ) {
            String query =  "SELECT * FROM REPLY";

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    String replyId=resultSet.getString("id");
                    String postId=resultSet.getString("postId");
                    double value= resultSet.getDouble("value");
                    String responder=resultSet.getString("responderId");
                    int repCount=resultSet.getInt("repcount");

                    //add reply to the relevant post
                    if(post.getID().compareTo(postId)==0) {
                        Reply reply= new Reply(postId,value,responder);
                        reply.setReplyID(replyId);
                        reply.setReplyCount(repCount);

                        if (post instanceof Event) {
                            post.handleReply(reply);
                        } else post.getArrayReply().add(reply); //no need to check for exception again for sale and job
//

                        System.out.println("Successfully added reply no "+ reply.getReplyID());
                    }

                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}