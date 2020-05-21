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
import model.Post;
import model.exceptions.FormatException;
import model.exceptions.ValueException;

import java.io.IOException;

public class NewSaleOfferController {
    @FXML private TextField OfferField;
    @FXML private  Label messageLabel;
    private Post sale;

    public void okButtonHandle(ActionEvent actionEvent) {
//        messageLabel.setText(sale.getDescription() );
        String postId=sale.getID();
        String userId=view1Controller.userId;
        String offer=OfferField.getText();
        Post post=sale;

        try{
            view1Controller.uni.replyToSale(postId,offer,userId,post);
            closeThisWindow(actionEvent);


        }catch(ValueException e) {

            closeThisWindow(actionEvent);
            try {
                openReplyMessageWindow(actionEvent,e.getReason());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
//            messageLabel.setText(e.getReason());
        }catch(Exception e) {
            messageLabel.setText(e.getMessage());
        }
    }

    public void cancelButtonHandle(ActionEvent actionEvent) {
        closeThisWindow(actionEvent);
    }

    //pass current post object from main window to this window
    public void initData (Post post) {
        sale=post;

    }

    //close this window
    public void closeThisWindow(ActionEvent actionEvent) {
        //get the stage information
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST)); //setup action when closing window: update main post listview
        window.close();
    }

    //open reply message window
    public void openReplyMessageWindow(ActionEvent actionEvent,String message) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ReplyMessage.fxml"));
        Parent messageWindow= loader.load();

        Scene messageView= new Scene(messageWindow);

        //get parent stage
        Stage parent = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        //get the controller and call the init method to pass message
        ReplyMessage controller=loader.getController();
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
