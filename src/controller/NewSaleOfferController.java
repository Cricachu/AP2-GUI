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
import model.Reply;
import model.Sale;
import model.exceptions.FormatException;
import model.exceptions.PriceException;
import model.exceptions.ValueException;

import java.io.IOException;
import java.util.ArrayList;

public class NewSaleOfferController {
    @FXML private TextField OfferField;
    @FXML private  Label messageLabel;
    private Sale sale;

    public void okButtonHandle(ActionEvent actionEvent) {

        String postId=sale.getID();
        String userId=view1Controller.userId;
        String offer=OfferField.getText();
        Sale post=sale;

        //print for testing
        System.out.println(post.getHighestOffer());
        System.out.println(post.getMinimumRaise());
        System.out.print("difference:");
//        System.out.println( Double.parseDouble(offer)-post.getHighestOffer());


        try{
            //successfully bought the item
            view1Controller.uni.replyToSale(postId,offer,userId,post);

            closeThisWindow(actionEvent); //close this window

            //display new window showing success message
            openSaleReplyMessageWindow(actionEvent,"This item has been sold to you! Contact the owner for more detail");

        }catch(ValueException e) {
            //when the priced entered is below the asking price:

            //close this window
            closeThisWindow(actionEvent);
            //display new window showing the message confirming the offer
            try {
                openSaleReplyMessageWindow(actionEvent,e.getReason());

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }catch(PriceException e) {
            //when the offer entered below minimum raise
            messageLabel.setText(e.getReason());

        }catch(NumberFormatException e) {
            //when the input format is wrong
            messageLabel.setText("You must enter a number");
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void cancelButtonHandle(ActionEvent actionEvent) {
        closeThisWindow(actionEvent);
    }

    //pass current post object from main window to this window
    public void initData (Sale post) {
        sale=post;

        //print for testing
        System.out.println(sale.getHighestOffer());
        System.out.println(sale.getMinimumRaise());
        ArrayList<Reply> allreps=sale.getArrayReply();
        for(Reply rep:allreps) {
            System.out.println(rep.getResponderID()+" "+ rep.getPostValue());
        }

    }

    //close this window
    public void closeThisWindow(ActionEvent actionEvent) {
        //get the stage information
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST)); //setup action when closing window: update main post listview
        window.close();
    }






    //open sale reply message window
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
