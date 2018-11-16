/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxapp;

import java.lang.Math;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Mg_lover
 */
public class TaxAppController implements Initializable
{
    final double deductibles[] = {12000.0, 24,000.0};
    final double[][] standardRates = new double[][]
    {
        {9525, 19050, .1},
        {38700, 77400, .12},
        {82500, 165000, .22},
        {157500, 315000, .24},
        {200000, 400000, .32},
        {500000, 600000, .35}
    };
    
    double totalWages = 0.0;
    double fedTaxWH = 0.0;
    int filingStatus = 0;
    double remBal = 0.0;
    double standardRate = 0.0;
    double deductible = 0.0;
    double taxableIncome = 0.0;
    double totalTaxOwed = 0.0;
    
    @FXML
    private Label refundLabel;
    
    @FXML
    private Label oweLabel;
    
    @FXML 
    private Label owe;
    
    @FXML
    private Label refund;
    
    @FXML
    public ComboBox statusCombobox;
    
    @FXML 
    public TextField income;
    
    @FXML
    public TextField fedTaxWithheld;
  
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
        
        filingStatus = statusCombobox.getSelectionModel().getSelectedIndex();
        totalWages = Double.parseDouble(income.getText());
        for(int i = 0; i < 6; ++i)
        {
            if(standardRates[i][filingStatus] >= totalWages)
            {
                standardRate = standardRates[i][2];
                i = 6;
            }
        }
        deductible = deductibles[filingStatus];
        taxableIncome = totalWages - deductible;
        if(taxableIncome < 0)
            taxableIncome = 0;
        totalTaxOwed = taxableIncome * standardRate;
        fedTaxWH = (Double.parseDouble(fedTaxWithheld.getText()) * 12);
        remBal = totalTaxOwed - fedTaxWH;
        if(remBal < 0.0)
        {
            remBal = Math.abs(remBal);
            oweLabel.setVisible(false);
            owe.setVisible(false);
            refund.setText("$" + Double.toString(remBal));
            refundLabel.setVisible(true);
            refund.setVisible(true);
        }
        else
        {
           refundLabel.setVisible(false);
           refund.setVisible(false);
           owe.setText("$" + Double.toString(remBal));
           oweLabel.setVisible(true);
           owe.setVisible(true);
        }
        
        System.out.print("Total Wages: " + totalWages + "\ndeductible:" + deductible + "\nTax Withheld: " + fedTaxWH + "\nRemaining balance: " + remBal + 
                "\nTotal tax owed: " + totalTaxOwed + "\nStandard rate: " + standardRate);
    }
    
    @FXML
    private void handleStatusAction(ActionEvent event) {
        System.out.println("You changed your status!`");
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       refundLabel.setVisible(false);
       oweLabel.setVisible(false);
       statusCombobox.setValue("Single");
    }    
    
}
