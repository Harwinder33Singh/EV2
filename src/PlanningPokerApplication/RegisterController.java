package PlanningPokerApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import application.Crypto;
//import application.FileEditor;
//import application.Role;
//import application.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class RegisterController implements Initializable{
	@FXML
	private Button create_button;
	@FXML
	private Button register_button;
	@FXML
	private TextField new_username_field;
	@FXML
	private PasswordField new_password_field;
	@FXML
	private PasswordField new_admin_password_field;
	@FXML
	private Button check_password;
	@FXML
	private ComboBox<String> roles_combo_box = new ComboBox<>();;
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    private String key = "YourSecretKey123";
    private Crypto crypto = new Crypto(key);
    private FileEditor userFileM = new FileEditor("users.txt");
    private List<User> users = userFileM.readUsers();
    private String[] roles = {"Administrator", "Software Developer", "Product Manager", "Technician"};
    private Role allRoles = new Role(roles);

    
    private String newUsername;
    private String newPassword;
    private String selectedRole;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        roles_combo_box.getItems().addAll(allRoles.getAllRoles());
        roles_combo_box.setValue(allRoles.getAllRoles().get(0));;
	}

/**
 * @author Minh Tran
 */
	@FXML
	public void create_button(ActionEvent event) throws IOException {
        newUsername = new_username_field.getText();
        newPassword = new_password_field.getText();
        selectedRole = roles_combo_box.getValue();
        SharedDataModel.setNewUsername(new_username_field.getText());
        SharedDataModel.setNewPassword(new_password_field.getText());
        SharedDataModel.setSelectedRole(roles_combo_box.getValue());

        if (selectedRole.equals("Administrator")) {
        	if (newUsername.isEmpty() || newPassword.isEmpty() || selectedRole.isEmpty()) {
                showAlert("Please enter values for all fields.");
                return;
            }
            // If the selected role is Administrator, prompt for the Administrator password
    	    root = FXMLLoader.load(getClass().getResource("administrator.fxml"));
    	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	    scene = new Scene(root);
    	    stage.setScene(scene);
    	    stage.show();
        } else if (validatePassword(newPassword)) {
            try {
                String encryptedPass = crypto.encryptString(newPassword);
                User newUser = new User(newUsername, encryptedPass, selectedRole, key);
                userFileM.writeUsers(newUser);
                users.add(newUser);
                showAlert("User Created with Username: " + newUsername + " and Role: " + selectedRole);
        	    root = FXMLLoader.load(getClass().getResource("login.fxml"));
        	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	    scene = new Scene(root);
        	    stage.setScene(scene);
        	    stage.show();            
        	    } catch (Exception e) {
                showAlert("Unexpected Error");
            }
        } else {
            showAlert("Password must be 8 characters long, contain a number, and include a special character.");
        }
	}
	
/**
 * @author Minh Tran
 */
	@FXML
	public void check_password(ActionEvent event) {
        String adminPassword = "AdminPassword";         
        if (new_admin_password_field.getText().equals(adminPassword)) {
            try {
                String encryptedPass = crypto.encryptString(SharedDataModel.getNewPassword());
                User newUser = new User(SharedDataModel.getNewUsername(), encryptedPass, "Administrator", key);
                userFileM.writeUsers(newUser);
                users.add(newUser);
                showAlert("User Created with Username: " + SharedDataModel.getNewUsername() + " and Role: Administrator");
        	    root = FXMLLoader.load(getClass().getResource("login.fxml"));
        	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	    scene = new Scene(root);
        	    stage.setScene(scene);
        	    stage.show();
            } catch (Exception e) {
                showAlert("Unexpected Error");
                System.out.print(e);
            }
        } else {
            showAlert("Incorrect Administrator Password");
        }
	}

/**
 * @author Minh Tran
 */
	@FXML
	public void switchToLogin(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("login.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}

/**
 * @author Minh Tran
 */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

/**
 * @author Minh Tran
 */
    private boolean validatePassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()].*");
    }
	
}
