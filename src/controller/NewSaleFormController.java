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

public class NewSaleFormController implements Initializable {

    @FXML private  TextField askingPriceField;
    @FXML private TextField minRaiseField;

    @FXML private ImageView imageView;
    @FXML private Label messageLabel;
    @FXML private Button cancelButton;
    @FXML private Button createButton;

    @FXML private TextField descriptionField;
    @FXML private TextField titleField;
    private Image selectedPhoto;
    private FileChooser fileChooser;
    private File filePath;

    //upload image
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

    //confirm create new sale
    public void createEventButtonHandle(ActionEvent actionEvent) {
        String userId=view1Controller.userId;
        String title=titleField.getText();
        String desc=descriptionField.getText();
        String askingPrice=askingPriceField.getText();
        String minRaise=minRaiseField.getText();

        try{
            view1Controller.uni.createNewSale(userId,title,desc,askingPrice,minRaise,selectedPhoto);
            messageLabel.setText("Succesfully create new sale!");
            view1Controller.changeToMainWindow(actionEvent);
        }catch(FormatException e) {
            messageLabel.setText(e.getReason());
        }catch(Exception e) {
            messageLabel.setText(e.getMessage());
        }
    }

    //get back to main menu
    public void cancelButtonHandle(ActionEvent actionEvent) {
        try {
            view1Controller.changeToMainWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedPhoto=new Image("/images/default.jpeg");
    }
}
