package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Job;

import java.io.IOException;

public class NewJobOfferController {

    @FXML private TextField OfferField;
    @FXML private Label messageLabel;
    private Job job;

    public void okButtonHandle(ActionEvent actionEvent) {
        String postId=job.getID();
        String userId=view1Controller.userId;
        String offer=OfferField.getText();
        Job post=job;

        try{
            //successfully made an offer
            view1Controller.uni.replyToJob(postId,offer,userId,post);
            closeThisWindow(actionEvent); //close this window

            //display new window showing success message
            openSaleReplyMessageWindow(actionEvent,"Successfully made an offer.");

        } catch(NumberFormatException e) {
            //when the input format is wrong
            messageLabel.setText("You must enter a number");
        } catch (Exception e) {
            //when offer is higher than highest offer
            messageLabel.setText(e.getMessage());
        }


    }

    public void cancelButtonHandle(ActionEvent actionEvent) {
        closeThisWindow(actionEvent);
    }


    //close this window
    public void closeThisWindow(ActionEvent actionEvent) {
        //get the stage information
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST)); //setup action when closing window: update main post listview
        window.close();
    }

    //passing data from main screen to this window
    public void initData(Job post) {
        job=post;
    }


    //open reply message window
    public void openSaleReplyMessageWindow(ActionEvent actionEvent,String message) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/SaleReplyMessage.fxml"));
        Parent messageWindow= loader.load();

        Scene messageView= new Scene(messageWindow);

        //get parent stage
        Stage parent = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

//        //get the controller and call the init method to pass message
        SaleReplyMessageController controller=loader.getController();
        controller.initData(message);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Reply Message");
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
    }

}
