package controller;

import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import java.awt.*;
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
import model.exceptions.FormatException;
import model.exceptions.NameException;

import static java.awt.Color.BLACK;

public class MainWindow implements Initializable {
    @FXML private ListView postList;
    @FXML private ScrollPane scrollPaneView;
    @FXML private HBox titleCentre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

           ArrayList<Post> allPosts= view1Controller.uni.getAllPosts();
            for(Post post:allPosts) {
                HBox hbox = new HBox();
                hbox.setSpacing(15);

                //mockup image
                Rectangle rect = new Rectangle(180,180);
                rect.setFill(Color.GRAY);

                //set content of the post
                Label details= new Label();
                details.setText(post.getPostDetails());

                //set buttons
                Button reply= new Button();
                reply.setText("Reply");
                Button moreDetails= new Button();
                moreDetails.setText("More Details");

                //add content to hbox
                hbox.getChildren().add(rect);
                hbox.getChildren().add(details);
                hbox.getChildren().add(reply);
                hbox.getChildren().add(moreDetails);

                //add hbox to list item
                postList.getItems().add(hbox);
            }

//        //load content to scroll Pane
//        //Add vbox to store all elements
//        VBox vbox = new VBox();
//        vbox.setSpacing(10);
//        vbox.setPadding(new Insets(50));
//
//        //add data to the Vbox
//        for (int i = 0; i <50 ; i++) {
//            //wrap each data chunks in a hbox
//            HBox postPanel= new HBox();
//            postPanel.setPadding(new Insets(10,10,10,10));
//            postPanel.setSpacing(10);
//            Label label = new Label("Welcome!");
//            TextField postDes= new TextField();
//            postPanel.getChildren().addAll(label,postDes);
//            postPanel.setAlignment(Pos.CENTER);
//            //add the collection of hbox to vbox
//            vbox.getChildren().add(postPanel);
//        }
//        scrollPaneView.setContent(vbox);

    }
}
