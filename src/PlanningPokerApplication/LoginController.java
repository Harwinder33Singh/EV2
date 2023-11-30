package PlanningPokerApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import PlanningPokerApplication.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class LoginController implements Initializable{
	@FXML
	private Button login_button;
	@FXML
	private Button register_button;
	@FXML
	private TextField username_field;
	@FXML
	private PasswordField password_field;
	
	//to switch to register
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    private Crypto crypto = new Crypto("YourSecretKey123");
    private FileEditor userFileM = new FileEditor("users.txt");
    private List<User> users = userFileM.readUsers();
    private int loginAttempts = 0;
    private boolean locked = false;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	@FXML
	public void login_button(ActionEvent event) throws IOException {

        String username = username_field.getText();
        String password = password_field.getText();

        if (isValidLogin(username, password) != null) {
        	if (isAdmin(username)) {
        	    root = FXMLLoader.load(getClass().getResource("administratorView.fxml"));
        	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	    scene = new Scene(root);
        	    stage.setScene(scene);
        	    stage.show();
            } else {
                loginAttempts = 0; // Reset login attempts
        	    root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	    scene = new Scene(root);
        	    stage.setScene(scene);
        	    stage.show();
            }
            loginAttempts = 0; // Reset login attempts
        } else {
            loginAttempts++;
            if (loginAttempts >= 3) {
                showAlert("Login Failed. Program is locked.");
                locked = true;
            } else {
                showAlert("Login Failed. Please enter a valid username and password.");
            }
        }
        username_field.clear();
        password_field.clear();
        
	}
	
	@FXML
	public void switchToRegister(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("register.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private boolean isAdmin(String username) {
   	 for (User user : users) {
   	        if (user.getUsername().equals(username) && user.getRole().equalsIgnoreCase("Administrator")) {
   	            return true;
   	        }
   	    }
   	    return false;
	}
    
    private User isValidLogin(String username, String password) {
        for (User user : users) {
        	try {
        		String encryptedPass = crypto.decryptString(user.getPassword());
                if (user.getUsername().equals(username) && encryptedPass.equals(password)) {
                    return user;
                }
        	} 
        	catch(Exception e) {
        		showAlert("Unexpected Error");
        	}
        }
        return null;
    }
	
}
