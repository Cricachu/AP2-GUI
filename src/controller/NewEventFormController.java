package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.exceptions.FormatException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewEventFormController implements Initializable {
    @FXML private ImageView imageView;
    @FXML private Label messageLabel;
    @FXML private Button cancelButton;
    @FXML private Button createButton;

    @FXML private TextField capacityField;
    @FXML private TextField dateField;
    @FXML private TextField venueField;
    @FXML private TextField descriptionField;
    @FXML private TextField titleField;
    private Image selectedPhoto;
    private FileChooser fileChooser;
    private File filePath;


    public void createEventButtonHandle(ActionEvent actionEvent) {
        String userId=view1Controller.userId;
        String title=titleField.getText();
        String desc=descriptionField.getText();
        String venue=venueField.getText();
        String date=dateField.getText();
        String cap=capacityField.getText();

        try{
            view1Controller.uni.createNewEvent(userId,title,desc,venue,date,cap,selectedPhoto);
            messageLabel.setText("Succesfully create new event!");
            view1Controller.changeToMainWindow(actionEvent);
        }catch(FormatException e) {
            messageLabel.setText(e.getReason());
        }catch(Exception e) {
            messageLabel.setText(e.getMessage());
        }
    }


    public void cancelButtonHandle(ActionEvent actionEvent) {
        try {
            view1Controller.changeToMainWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadImageButtonPushed(ActionEvent actionEvent) {
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        fileChooser=new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath=fileChooser.showOpenDialog(stage);

        //update the image
        try{
            BufferedImage bufferedImage= ImageIO.read(filePath);
            selectedPhoto= SwingFXUtils.toFXImage(bufferedImage,null);
            this.imageView.setImage(selectedPhoto); //set image view by newly selected photo

        } catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedPhoto=new Image("/images/default.jpeg");
        imageView.setImage(selectedPhoto);
    }
}
