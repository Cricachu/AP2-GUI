package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Post;
import model.UniLink;

import java.io.IOException;

public class DeleteConfirmationController {

    Post deletedPost;
    public void okButtonHandle(ActionEvent event) {


        try {
            view1Controller.uni.deletePost(deletedPost);
            view1Controller.uni.setChangeToMainWindowtoTrue();//after click ok, change back to main window (without displaying post details window)

            //close this window
            //get the stage information
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST)); //setup action when closing window: change back to main window if ok
            window.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelButtonHandle(ActionEvent event) {
        //get the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.close();
    }

    public void initData(Post post) {
        deletedPost=post;
    }
}
