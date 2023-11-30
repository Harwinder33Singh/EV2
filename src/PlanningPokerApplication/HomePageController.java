package PlanningPokerApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import HarwinderPart.PlanningPokerApp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class HomePageController implements Initializable{
	@FXML
	private Button effortlogger_button;
	@FXML
	private Button planning_poker_button;
	@FXML
	private Button exit_button;

	
	private Stage stage;
	private Scene scene;
	private Parent root;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	@FXML
	public void switchToEL(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("/EfforLogConsole/LoggerConsoleView.fxml")); //add the name of the effortloger fxml
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	@FXML
	public void switchToPP(ActionEvent event) throws IOException {
		PlanningPokerApp planningPokerApp = new PlanningPokerApp();
        Scene planningPokerScene = planningPokerApp.createInitialScene();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(planningPokerScene);
        currentStage.show();
        
        planningPokerApp.start(currentStage);
	}
	
	@FXML
	public void switchToLogin(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("login.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
}
