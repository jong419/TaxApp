/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxapp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Mg_lover
 */
public class TaxApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(TaxApp.class.getResource("TaxApp.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("TaxApp");
            primaryStage.show(); 
        } 
        catch (Exception ex) {
            Logger.getLogger(TaxApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
