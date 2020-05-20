package controller;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.*;
import model.database.CreateTable;
import model.database.DeleteTable;
import model.database.SelectQuery;
import model.exceptions.FormatException;
import model.exceptions.NameException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class view1Controller implements Initializable {

    @FXML private TextField nameField;
    @FXML private Label outputLabel;


    static UniLink uni;
    public static String userId;


    public void loginButtonHandle(ActionEvent actionEvent) {
        userId= nameField.getText();
        try{
            if(uni.login(userId)) {
//                outputLabel.setText("Welcome,"+ a);
                changeToMainWindow(actionEvent);


            }
        }catch(FormatException fe) {
               outputLabel.setText(fe.getReason());

            }catch (NameException name) {
            outputLabel.setText(name.getReason());
            } catch (Exception e) {
            outputLabel.setText(e.getMessage());
            e.printStackTrace();
            }


    }


    public void textFieldHandle(ActionEvent actionEvent) {

    }

    public static void changeToMainWindow(ActionEvent event) throws IOException {
        Parent mainWindow= FXMLLoader.load(view1Controller.class.getResource("/view/MainWindow.fxml"));
        Scene scene= new Scene(mainWindow,800,800);

        //get the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setY(10);
        window.setX(350);
        window.setTitle("UniLink dashboard");
        window.show();
    }

    public void initData(UniLink uni) {
        this.uni=uni;
//        hardcoded for testing
//        Event e1= new Event("s1", "Concert", "My chemical Romance", "Melbourne showground", "01/04/2020", 50);
//         uni.getAllPosts().add(e1);
//
//
//        Sale s1= new Sale("s1", "Piano", "Brand new ", 200, 10);
//        uni.getAllPosts().add(s1);
//
//        Sale s2= new Sale("s1", "Violin", "Used ", 200, 10);
//        uni.getAllPosts().add(s2);
//
////
//        Job j1= new Job("s1", "Tree cutting", "Need someone to help trimming my garden", 200);
//        uni.getAllPosts().add(j1);
//
//        Job j2= new Job("s1", "House moving", "Need someone to help moving house", 200);
//        uni.getAllPosts().add(j2);
//
//        Event e3= new Event("s3", "Concert", "Bowling for Soup", "Melbourne showground", "01/04/2022", 900);
//        uni.getAllPosts().add(e3);


//        for(Post post: uni.getAllPosts()) {
//            post.printDetails();
//        }



            //delete tables
//        DeleteTable.deleteReply();
//        DeleteTable.deleteEvent();
//        DeleteTable.deleteJob();
//        DeleteTable.deleteSale();
//        DeleteTable.deletePost();
//
//
////        create tables
//        CreateTable.newPost();
//        CreateTable.newEvent();
//          CreateTable.newReply();
//            CreateTable.newSale();
//            CreateTable.newJob();

        //load data
        SelectQuery.loadPost(uni);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
