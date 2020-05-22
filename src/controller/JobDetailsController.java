package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import model.*;
import model.utilities.Status;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class JobDetailsController implements Initializable {
    @FXML
    private ImageView photo;
    @FXML private Label postDetailsLabel;
    @FXML private TableView<Reply> replyTable;
    @FXML private TableColumn<Reply,String> studentColumn;
    @FXML private TableColumn <Reply, Double>offerColumn;
    @FXML private Button editDetails;
    @FXML private Button closePost;

    private Post jobPost;
    private FileChooser fileChooser;
    private File filePath;
    private Image newPhoto;

    public void clickBackToMainWindow(ActionEvent event) {
        try {
            view1Controller.changeToMainWindow(event);
           jobPost.setNotUpdated(); //change state of job to false--> nothing to update

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadImagePushed(ActionEvent event) {
        Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
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

    public void closeButtonPushed(ActionEvent event) {
        //close post
        UniLink.closePost(jobPost);

        //update Text area
        postDetailsLabel.setText(jobPost.getPostDetails());
    }

    public void deleteButtonPushed(ActionEvent event) {
        view1Controller.uni.deletePost(jobPost);

        try {
            deletionConfirmationMessage(event,jobPost);
//            view1Controller.changeToMainWindow(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //open new window to show message after delete event
    public void deletionConfirmationMessage(ActionEvent actionEvent,Post post) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/DeleteConfirmation.fxml"));
        Parent messageWindow= loader.load();

        Scene messageView= new Scene(messageWindow);

        //get parent stage
        Stage parent = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

//        //get the controller and call the init method to pass message
        DeleteConfirmationController controller=loader.getController();
        controller.initData(post);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Delete Confirm");
        newWindow.setScene(messageView);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(parent);

        //set position of new window
        newWindow.setX(parent.getX()+parent.getWidth()/4);
        newWindow.setY(parent.getY()+parent.getHeight()/4);
        //run
        newWindow.show();

        newWindow.setOnCloseRequest(new EventHandler<WindowEvent>(){

            //change to main window if select "ok" to delete
            public void handle(WindowEvent we) {
                if(view1Controller.uni.getChangeToMainWindowStatus()) {
                    try {
                        view1Controller.changeToMainWindow(actionEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    view1Controller.uni.setChangeToMainWindowtoFalse();
                }
            }});
    }



    public void editButtonPushed(ActionEvent event) {
        try {
            openEditWindow(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //open edit window

    public void openEditWindow(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/editJobDetails.fxml"));
        Parent editWindow= loader.load();

        Scene editEvent= new Scene(editWindow);

        //access controller and call init method
        EditJobDetailsController controller=loader.getController();
        controller.initData(postDetailsLabel,(Job)jobPost);

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




    public void saveButtonPushed(ActionEvent event) {
        try{
            //save new photo update
            if(this.newPhoto!=null) {
                this.jobPost.setPhoto(newPhoto);
            }
            //save updated details
            if(jobPost.getState()==true) { //if there's updated info (state=true) then update

                UniLink.updateJob((Job)jobPost);//update Job
               jobPost.setNotUpdated(); //reset the state back to false after updating

            }

            //back to main window
            view1Controller.changeToMainWindow(event);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void initData(Post post) {
        jobPost=post;
        postDetailsLabel.setText(jobPost.getPostDetails()); //set post details

        //cannot edit post if there's reply already
        if(jobPost.getArrayReply().size()>0) {
            editDetails.setDisable(true);
        }

        //disable close button if status is close
        if(jobPost.getStatus()== Status.CLOSE) {
            closePost.setDisable(true);
        }

        //set photo
        photo.setImage(jobPost.getPhoto());

        //setup table value
        replyTable.setItems(getReplies());

        //in order to setup setCellValueFactory, must create getter method in model class with correct syntax: getVariableName()
        //e.g: getResponderID, getValue
        studentColumn.setCellValueFactory(new PropertyValueFactory<Reply,String>("responderID"));
        offerColumn.setCellValueFactory(new PropertyValueFactory<Reply,Double>("value"));

    }


    //get observableList of Reply objects for storing in table
    public ObservableList<Reply> getReplies(){
        ObservableList<Reply> reps= FXCollections.observableArrayList();
        ArrayList<Reply> allReps= jobPost.getArrayReply();

        for(Reply reply:allReps) {
            reps.add(reply);
        }

        return reps;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set alignment to table value to center
        studentColumn.setStyle("-fx-alignment: CENTER;");
        offerColumn.setStyle("-fx-alignment: CENTER;");
    }
}
