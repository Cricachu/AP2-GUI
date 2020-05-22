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

        this.labelField=labell;//pass field to record event details from event details window
        this.eventtt =post;//pass post from event details window to this window

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

        //record value of original post before any new edit is made
        title=eventtt.getTitle();
        desc=eventtt.getDescription();
        venue=((Event)eventtt).getVenue();
        date=((Event) eventtt).getDate();
        capa=((Event) eventtt).getCapacity();

        try{
            //validate new value for each field
            UniLink.addTextInfo(titleField.getText());
            UniLink.addTextInfo(descriptionField.getText());
            UniLink.addTextInfo(venueField.getText());
            UniLink.addDate(dateField.getText());
            UniLink.addCapacity(capacityField.getText());

            //set the temporary post details (not yet saved)
            tempDetails="\n"+ "ID: " + "\t" + "\t"+ eventtt.getID()+
                    "\n"+ "Title: " + "\t" +"\t"+ titleField.getText()+
                    "\n" + "Description: "+ "\t"+ descriptionField.getText()+
                    "\n" + "Creator ID " +"\t"+  eventtt.getCreatorId()+
                    "\n" + "Status :"+ "\t"+ eventtt.getStatus()+
                    "\n"+ "Venue: "+ "\t"+"\t"+ venueField.getText()+
                    "\n"+ "Date: "+"\t"+"\t"+  dateField.getText()+
                    "\n"+ "Capacity: " +"\t"+ capacityField.getText()+
                    "\n"+ "Attendees: "+ "\t"+ ((Event) eventtt).getAttendeesCount()+
                    "\n"+ "--------------";


            //upon closing edit window:
            labelField.setText(tempDetails); //set post details text field (temporary) in the previous window

            if(detailUpdated()) {//if there's new change, set the old post values to the new ones

                eventtt.setUpdated();//set event state to updated (true)

                title=titleField.getText();
                desc=descriptionField.getText();
                venue=venueField.getText();
                 date=dateField.getText();
                 capa=Integer.parseInt(capacityField.getText());

            }
                //pass new updated details to uniLink
            UniLink.passNewEventInfo(title,desc,venue,date,capa);

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

    //check if there's any new change in post details
    public boolean detailUpdated(){
        if(title.compareTo(titleField.getText())!=0 ||
        desc.compareTo(descriptionField.getText())!=0 ||
        venue.compareTo(venueField.getText()) !=0 ||
        date.compareTo(dateField.getText())!=0 ||
        capa!=Integer.parseInt(capacityField.getText())) {
            return true;
        } else return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
