package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import model.utilities.Status;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SaleDetailsController implements Initializable {
    @FXML private Button backToMainWindow;
    @FXML private ImageView photo;
    @FXML private Label postDetailsLabel;
    @FXML private TableView <Reply>replyTable;
    @FXML private TableColumn <Reply,String> studentColumn;
    @FXML private TableColumn <Reply, Double>offerColumn;
    @FXML private Button editDetails;
    @FXML private Button closePost;


    private Post salePost;
    private FileChooser fileChooser;
    private File filePath;
    private Image newPhoto;


    public void clickBackToMainWindow(ActionEvent actionEvent) {
        try {
            view1Controller.changeToMainWindow(actionEvent);
            salePost.setNotUpdated(); //change state of sale to false--> nothing to update

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

    public void closeButtonPushed(ActionEvent actionEvent) {
    }

    public void deleteButtonPushed(ActionEvent actionEvent) {
    }

    public void editButtonPushed(ActionEvent actionEvent) {
        try {
            openEditWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveButtonPushed(ActionEvent actionEvent) {

        try{
            //save new photo update
            if(this.newPhoto!=null) {
                this.salePost.setPhoto(newPhoto);
            }
            //save updated details
            if(salePost.getState()==true) { //if there's updated info (state=true) then update

                UniLink.updateSale((Sale)salePost); //update the sale
                salePost.setNotUpdated(); //reset the state back to false after updating

            }


            //back to main window
            view1Controller.changeToMainWindow(actionEvent);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    //get observableList of Reply objects for storing in table
    public ObservableList<Reply> getReplies(){
        ObservableList<Reply> reps= FXCollections.observableArrayList();
        ArrayList<Reply> allReps= salePost.getArrayReply();

        for(Reply reply:allReps) {
            reps.add(reply);
        }

        return reps;
    }

    //Open edit window
    public void openEditWindow(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/editSaleDetails.fxml"));
        Parent editWindow= loader.load();

        Scene editEvent= new Scene(editWindow);

        //access controller and call init method
        EditSaleDetailsController controller=loader.getController();
        controller.initData(postDetailsLabel,(Sale)salePost);

        //get parent stage
        Stage parent = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit Sale ");
        newWindow.setScene(editEvent);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(parent);

        //set position of new window
        newWindow.setX(parent.getX()+parent.getWidth()/4);
        newWindow.setY(parent.getY()+parent.getHeight()/4);

        //run
        newWindow.show();

    }


    //passing data from main window to this window
    public void initData(Post post) {
        salePost=post;

        postDetailsLabel.setText(salePost.getPostDetails()); //set post details

        //cannot edit post if there's reply already
        if(salePost.getArrayReply().size()>0) {
            editDetails.setDisable(true);
        }

        //disable close button if status is close
        if(salePost.getStatus()== Status.CLOSE) {
            closePost.setDisable(true);
        }

        //set photo
        photo.setImage(salePost.getPhoto());

        //setup table value
        replyTable.setItems(getReplies());

        //in order to setup setCellValueFactory, must create getter method in model class with correct syntax: getVariableName()
        //e.g: getResponderID, getValue
        studentColumn.setCellValueFactory(new PropertyValueFactory<Reply,String>("responderID"));
        offerColumn.setCellValueFactory(new PropertyValueFactory<Reply,Double>("value"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set alignment to table value to center
        studentColumn.setStyle("-fx-alignment: CENTER;");
        offerColumn.setStyle("-fx-alignment: CENTER;");
    }
}
