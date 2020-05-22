package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Job;
import model.UniLink;
import model.exceptions.FormatException;

public class EditJobDetailsController {
    @FXML private  Label messageLabel;
    @FXML private TextField titleField;
    @FXML private  TextField descriptionField;
    @FXML private  TextField proposedPriceField;

    @FXML Label labelField;
    Job job;

    private String title;
    private String desc;
    private double prosPrice;
    private String temp;

    public void okButtonHandle(ActionEvent event) {

        //record value of the orginal post (before edit)
        title=job.getTitle();
        desc=job.getDescription();
        prosPrice=job.getProposedPrice();

        try{
            //validate input data in the edited form
            UniLink.addTextInfo(titleField.getText());
            UniLink.addTextInfo(descriptionField.getText());
            UniLink.addPrice(proposedPriceField.getText());

            //update temp details (not yet saved, just to display only)
            temp="\n"+ "ID: " + "\t" + "\t"+ job.getID()+
                    "\n"+ "Title: " + "\t" +"\t"+ titleField.getText()+
                    "\n" + "Description: "+ "\t"+ descriptionField.getText()+
                    "\n" + "Creator ID " +"\t"+  job.getCreatorId()+
                    "\n" + "Status :"+ "\t"+ job.getStatus()+
                    "\n"+ "Proposed price: "+ "\t"+"\t"+ proposedPriceField.getText()+
                    "\n"+ "Lowest Offer: "+"\t"+"\t"+  proposedPriceField.getText()+ //lowest offer = proposed price initially
                    "\n"+ "--------------";

            //upon closing edit window:
            labelField.setText(temp); //set post details text field (temporary) in the previous window

            if(detailUpdated()) {
               job.setUpdated();

                title=titleField.getText();
                desc=descriptionField.getText();
                prosPrice=Double.parseDouble(proposedPriceField.getText());

            }
            //pass new updated details to uniLink
            UniLink.passNewJobInfo(title,desc,prosPrice);

            //close window
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.close();

        }catch(FormatException e) {
            messageLabel.setText(e.getReason());
        }catch(Exception e) {
            messageLabel.setText(e.getMessage());
        }

    }

    //check if there's any new change in post details
    public boolean detailUpdated(){
        if(title.compareTo(titleField.getText())!=0 ||
                desc.compareTo(descriptionField.getText())!=0 ||
                prosPrice!= Double.parseDouble(proposedPriceField.getText()))  {
            return true;
        } else return false;
    }

    public void cancelButtonHandle(ActionEvent event) {
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.close();
    }

    public void initData(Label postDetailsLabel, Job jobPost) {
        labelField=postDetailsLabel;
        job=jobPost;

        //setup existing data for fields
        titleField.setText(job.getTitle());
        descriptionField.setText(job.getDescription());
        proposedPriceField.setText(Double.toString(job.getProposedPrice()));
    }
}
