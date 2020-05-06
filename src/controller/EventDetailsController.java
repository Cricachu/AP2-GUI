package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Event;
import model.Post;
import model.Reply;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventDetailsController implements Initializable {


    @FXML private TableView<Reply> attendeeTable;
    @FXML private TableColumn <Reply,String> attendeeColumn;
    @FXML private Button uploadImage;
    @FXML private ImageView photo;
    @FXML private Label eventDetailsLabel;
    @FXML private Button editDetails;
    @FXML private Button backToMainWindow;
    private Post event;
    private FileChooser fileChooser;
    private File filePath;
    private Image newPhoto;

    public void initData(Post post){
        event=post;
        //set post details
         eventDetailsLabel.setText(event.getPostDetails());
        //cannot edit details if there's reply already
        if(event.getArrayReply().size()>0) {
            editDetails.setDisable(true);
        }

        //set photo
        photo.setImage(event.getPhoto());

        //set value for table
        attendeeColumn.setCellValueFactory(new PropertyValueFactory<Reply,String>("responderID"));//value for column
        attendeeTable.setItems(getReplies()); //store array of Reply object to table

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        attendeeColumn.setStyle("-fx-alignment: CENTER;");//set alignment for column content

    }

    //get observableList of Reply objects for storing in table
    public ObservableList<Reply> getReplies(){
        ObservableList<Reply> reps= FXCollections.observableArrayList();
        ArrayList<Reply> allReps= event.getArrayReply();

        for(Reply reply:allReps) {
            reps.add(reply);
        }

        return reps;
    }

    //go back to main Window
    public void clickBackToMainWindow(ActionEvent actionEvent) {
        try {
            view1Controller.changeToMainWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //upload image
    public void uploadImagePushed(ActionEvent actionEvent) {
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        fileChooser=new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath=fileChooser.showOpenDialog(stage);

        //update the image
        try{
            BufferedImage bufferedImage= ImageIO.read(filePath);
            newPhoto= SwingFXUtils.toFXImage(bufferedImage,null);
            this.photo.setImage(newPhoto); //set image view by newly selected photo (not yet saved)

        } catch (Exception e) {

        }
    }

    public void saveButtonPushed(ActionEvent actionEvent) {
       try{
           //save new photo update
           if(this.newPhoto!=null) {
               this.event.setPhoto(newPhoto);
           }
           //back to main window
           view1Controller.changeToMainWindow(actionEvent);
       } catch(Exception e) {
           e.printStackTrace();
       }

    }
}
