package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Event;
import model.Post;
import model.UniLink;
import model.exceptions.FormatException;

import java.net.URL;
import java.util.ResourceBundle;

public class editEventDetailController implements Initializable {
    @FXML private Label messageLabel;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField venueField;
    @FXML private TextField dateField;
    @FXML private TextField capacityField;

    @FXML Label labelField;

    private String title;
    private String desc;
    private String venue;
    private String date;
    private int capa;
    private String tempDetails;

    Post eventtt;
    public void initData(Post post, Label labell,String etitle,String edesc,String evenue,String edate,int ecap) {
        this.labelField=labell;

        this.eventtt =post;
        this.title=etitle;
        this.desc=edesc;
        this.venue=evenue;
        this.date=edate;
        this.capa=ecap;



        titleField.setText(eventtt.getTitle());
        descriptionField.setText(eventtt.getDescription());
        venueField.setText( ((Event)post).getVenue());
        dateField.setText(((Event)post).getDate());
        capacityField.setText( Integer.toString(((Event)post).getCapacity()));
    }
    public void okButtonHandle(ActionEvent actionEvent) {
        title=titleField.getText();
        desc=descriptionField.getText();
        venue=venueField.getText();
        date=dateField.getText();

        try{
            //validate new value for each field
            UniLink.addTextInfo(title);
            UniLink.addTextInfo(desc);
            UniLink.addTextInfo(venue);
            UniLink.addDate(date);
            capa=UniLink.addCapacity(capacityField.getText());

            //set the temporary post details (not yet saved)
            tempDetails="\n"+ "ID: " + "\t" + "\t"+ eventtt.getID()+
                    "\n"+ "Title: " + "\t" +"\t"+ title+
                    "\n" + "Description: "+ "\t"+ desc+
                    "\n" + "Creator ID " +"\t"+  eventtt.getCreatorId()+
                    "\n" + "Status :"+ "\t"+ eventtt.getStatus()+
                    "\n"+ "Venue: "+ "\t"+"\t"+ venue+
                    "\n"+ "Date: "+"\t"+"\t"+  date+
                    "\n"+ "Capacity: " +"\t"+  capa+
                    "\n"+ "Attendees: "+ "\t"+ ((Event) eventtt).getAttendeesCount()+
                    "\n"+ "--------------";

//            //pass data back to Event Details window
//            FXMLLoader loader=new FXMLLoader();
//            loader.setLocation(getClass().getResource("/view/EventDetails.fxml"));
//            Parent editWindow= loader.load();
//
//            Scene editEvent= new Scene(editWindow);
//
//            //access controller and call init method
//            EventDetailsController controller=loader.getController();
//            controller.initEditedData(title,desc,venue,date,capa);



            //upon closing window
            labelField.setText(tempDetails); //set post details text

                //update post info
            eventtt.setTitle(title);
            eventtt.setDescription(desc);
            ((Event) eventtt).setVenue(venue);
            ((Event) eventtt).setDate(date);
            ((Event) eventtt).setCapacity(capa);

                //close window
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            //activate setOnclose request in event details view,
            //temporarily set changes to event details value
//            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
            window.close();



        } catch(FormatException e) {
            messageLabel.setText(e.getReason());
        }catch(Exception e) {
            messageLabel.setText(e.getMessage());
            e.printStackTrace();
        }


    }

    public void cancelButtonHandle(ActionEvent actionEvent) {
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
