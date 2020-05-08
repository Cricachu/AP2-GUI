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
import model.Event;
import model.Post;
import model.Reply;
import model.utilities.Status;

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
    @FXML private Button closePost;
    @FXML private Button deletePost;
    @FXML private  Button saveButton;

    private Post eventt;
    private FileChooser fileChooser;
    private File filePath;
    private Image newPhoto;

    private String titleee;
    private String desc;
    private String venue;
    private String date;
    private int capa;
    private String tempDetails;

    //method to pass data from main window
    public void initData(Post post){
        eventt =post;
        //set post details
         eventDetailsLabel.setText(eventt.getPostDetails());
        //cannot edit details if there's reply already
        if(eventt.getArrayReply().size()>0) {
            editDetails.setDisable(true);
        }

        if(eventt.getStatus()== Status.CLOSE) {
            closePost.setDisable(true);
        }
        //set photo
        photo.setImage(eventt.getPhoto());

        //set value for table
        attendeeColumn.setCellValueFactory(new PropertyValueFactory<Reply,String>("responderID"));//value for column
        attendeeTable.setItems(getReplies()); //store array of Reply object to table

    }

//    //method to pass data from edit event window
//    public void initEditedData(String title,String desc,String venue,String date,int capa){
////        System.out.println("Im here");
//        this.titleee=title;
//        this.desc=desc;
//        this.venue=venue;
//        this.date=date;
//        this.capa=capa;
//
//        System.out.println(titleee);
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        attendeeColumn.setStyle("-fx-alignment: CENTER;");//set alignment for column content

    }

    //get observableList of Reply objects for storing in table
    public ObservableList<Reply> getReplies(){
        ObservableList<Reply> reps= FXCollections.observableArrayList();
        ArrayList<Reply> allReps= eventt.getArrayReply();

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

    //open second window for edit event details
    public void openEditWindow(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/editEventDetails.fxml"));
        Parent editWindow= loader.load();

        Scene editEvent= new Scene(editWindow);

        //access controller and call init method
        editEventDetailController controller=loader.getController();
        controller.initData(this.eventt,eventDetailsLabel,titleee,desc,venue,date,capa);

        //get parent stage
        Stage parent = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit Event ");
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

        newWindow.setOnCloseRequest(new EventHandler<WindowEvent>(){

                                     public void handle(WindowEvent we) {

                                     }});
    }


    //save new update
    public void saveButtonPushed(ActionEvent actionEvent) {
       try{
           //save new photo update
           if(this.newPhoto!=null) {
               this.eventt.setPhoto(newPhoto);
           }
           //back to main window
           view1Controller.changeToMainWindow(actionEvent);
       } catch(Exception e) {
           e.printStackTrace();
       }

    }

    public void editButtonPushed(ActionEvent actionEvent) {
        try {
            openEditWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeEveButtonPushed(ActionEvent actionEvent) {
        eventt.closePost();
        eventDetailsLabel.setText(eventt.getPostDetails());
    }

    public void deleteButtonPushed(ActionEvent actionEvent) {
        eventDetailsLabel.setText("deleted");
    }
}
