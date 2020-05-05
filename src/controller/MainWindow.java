package controller;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import model.Event;
import model.exceptions.FormatException;
import model.exceptions.NameException;

import static java.awt.Color.BLACK;

public class MainWindow implements Initializable {
    @FXML private Button logOutButton;
    @FXML private Button newEventbtn;
    @FXML private ListView postList;
    @FXML private ScrollPane scrollPaneView;
    @FXML private HBox titleCentre;
    @FXML private Label studentIDTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            studentIDTitle.setText("Welcome,student:"+view1Controller.userId);

           ArrayList<Post> allPosts= view1Controller.uni.getAllPosts();
            for(Post post:allPosts) {

                BorderPane bd= new BorderPane();

                //mockup image
                Rectangle rect = new Rectangle(180,180);
                rect.setFill(Color.GRAY);

                //set content of the post
                Label details= new Label();
                details.setText(post.getPostDetails());
                details.setPadding(new Insets(0,0,0,10));

                //set buttons
                HBox buttons= new HBox();
                buttons.setAlignment(Pos.CENTER);
                Button reply= new Button();
                reply.setText("Reply");

                Button moreDetails= new Button();
                moreDetails.setText("More Details");

                if(post instanceof Event) {
                    bd.setStyle("-fx-background-color: #FFFFFF;");
                    reply.setText("Join");
                    viewEventDetails(moreDetails,post); //click "more details" button to view Event details

                }else if(post instanceof Job) {
                    bd.setStyle("-fx-background-color: #F6DCD7;");

                } else {
                    bd.setStyle("-fx-background-color: #B5C5C5;");
                }

                buttons.getChildren().addAll(reply,moreDetails);
                buttons.setSpacing(20);
                buttons.setPadding(new Insets(50));



                //add all contents to border pane
                bd.setLeft(rect);
                bd.setCenter(details);
                BorderPane.setAlignment(details,Pos.CENTER_LEFT);
                bd.setRight(buttons);


                //add border pane to list item
                postList.getItems().add(bd);

            }


    }

    public void newEventHandle(ActionEvent actionEvent)  {
        try {
            openNewEventWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openNewEventWindow(ActionEvent event) throws IOException {
        Parent mainWindow= FXMLLoader.load(getClass().getResource("/view/NewEventForm.fxml"));
        Scene scene= new Scene(mainWindow);
        //get the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setY(10);
        window.setX(350);
        window.show();
    }

    public void logOutButtonHandle(ActionEvent actionEvent) {
        try {
            openLogInWindow(actionEvent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Open log in Window
    public void openLogInWindow(ActionEvent event) throws IOException {
        Parent mainWindow= FXMLLoader.load(getClass().getResource("/view/view_1.fxml"));
        Scene scene= new Scene(mainWindow);
        //get the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
//        window.setY(10);
//        window.setX(350);
        window.show();
    }


    //Click a button to open EventDetailsWindow
    public void viewEventDetails(Button button,Post post) {


        //Create handler for open event details button
        EventHandler<ActionEvent> evn = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                try {
                    openEventDetailsWindow(e,post);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        //set button on action
        button.setOnAction(evn);
    }



    //open Event Details Window
    public void openEventDetailsWindow(ActionEvent event,Post post) throws IOException {
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/EventDetails.fxml"));
    Parent eventWindow= loader.load();

    Scene eventView= new Scene(eventWindow);

         //access controller and call init method
        EventDetailsController controller=loader.getController();
        controller.initData(post);

        //get the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(eventView);
        window.show();
    }
}
