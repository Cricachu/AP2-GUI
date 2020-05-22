package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Event;
import model.Sale;
import model.UniLink;
import model.exceptions.FormatException;

public class EditSaleDetailsController {
    @FXML private Label messageLabel;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField askingPriceField;
    @FXML private TextField minRaiseField;

    @FXML Label labelField;
    Sale sale;

    private String title;
    private String desc;
    private double askPrice;
    private double minRaise;
    private String temp;

    public void okButtonHandle(ActionEvent actionEvent) {
        //record value of the orginal post (before edit)
        title=sale.getTitle();
        desc=sale.getDescription();
        askPrice=sale.getAskingPrice();
        minRaise=sale.getMinimumRaise();

        try {

            //validate input data in the edited form
            UniLink.addTextInfo(titleField.getText());
            UniLink.addTextInfo(descriptionField.getText());
            UniLink.addPrice(askingPriceField.getText());
            UniLink.addPrice(minRaiseField.getText());

            //update temp details (not yet saved, just to display only)
            temp="\n"+ "ID: " + "\t" + "\t"+ sale.getID()+
                    "\n"+ "Title: " + "\t" +"\t"+ titleField.getText()+
                    "\n" + "Description: "+ "\t"+ descriptionField.getText()+
                    "\n" + "Creator ID " +"\t"+  sale.getCreatorId()+
                    "\n" + "Status :"+ "\t"+ sale.getStatus()+
                    "\n"+ "Minimum Raise: "+ "\t"+"\t"+ minRaiseField.getText()+
                    "\n"+ "Asking Price (test purpose): "+"\t"+"\t"+  askingPriceField.getText()+
                    "\n"+ "--------------";

            //upon closing edit window:
            labelField.setText(temp); //set post details text field (temporary) in the previous window

            if(detailUpdated()) {
                sale.setUpdated();

                title=titleField.getText();
                desc=descriptionField.getText();
                askPrice=Double.parseDouble(askingPriceField.getText());
                minRaise=Double.parseDouble(minRaiseField.getText());


            }
            //pass new updated details to uniLink
            UniLink.passNewSaleInfor(title,desc,askPrice,minRaise);

            //close window
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.close();

        } catch(FormatException e) {
            messageLabel.setText(e.getReason());
        }catch(Exception e) {
            messageLabel.setText(e.getMessage());
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
                askPrice!= Double.parseDouble(askingPriceField.getText()) ||
                minRaise!= Double.parseDouble(minRaiseField.getText())) {
            return true;
        } else return false;
    }


    public void initData(Label label, Sale spost) {
        labelField=label;
        sale=spost;

        //setup existing data for fields
        titleField.setText(sale.getTitle());
        descriptionField.setText(sale.getDescription());
        askingPriceField.setText(Double.toString(sale.getAskingPrice()));
        minRaiseField.setText(Double.toString(sale.getMinimumRaise()));

    }
}
