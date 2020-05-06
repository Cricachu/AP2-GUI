package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Event;
import model.Post;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventDetailsController implements Initializable {


    @FXML private Button uploadImage;
    @FXML private ImageView photo;
    @FXML private Label eventDetailsLabel;
    @FXML private Button editDetails;
    @FXML private Button backToMainWindow;
    Post event;
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

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void clickBackToMainWindow(ActionEvent actionEvent) {
        try {
            view1Controller.changeToMainWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
