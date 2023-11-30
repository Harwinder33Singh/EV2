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
import javafx.scene.layout.BackgroundFill;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class AdminViewController implements Initializable{
	@FXML
	private Button login_button;
	@FXML
	private Button change_role;
	@FXML
	private ListView<String> user_list = new ListView<>();
	@FXML
	private ComboBox<String> roles_combo_box = new ComboBox<>();;
	@FXML
	private Button load_button;

	private Stage stage;
	private Scene scene;
	private Parent root;

    private String key = "YourSecretKey123";
    private Crypto crypto = new Crypto(key);
    private FileEditor userFileM = new FileEditor("users.txt");
    private List<User> users = userFileM.readUsers();
    private String[] roles = {"Administrator", "Software Developer", "Product Manager", "Technician"};
    private Role allRoles = new Role(roles);;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        roles_combo_box.getItems().addAll(allRoles.getAllRoles());
        roles_combo_box.setValue(allRoles.getAllRoles().get(0));;      
        for (User user : users) {
        	user_list.getItems().add(user.getUsername() + " - " + user.getRole());
        }
	}
	
	@FXML
	public void changing(ActionEvent event) throws IOException {
    	String selectedUser = user_list.getSelectionModel().getSelectedItem();
        String newRole = roles_combo_box.getValue();
        User userToChangeRole = findUserByUsername(selectedUser);
        if (userToChangeRole != null) {
            userToChangeRole.changeUserRole(newRole);
            deleteUser(userToChangeRole.getUsername());
            userFileM.writeUsers(userToChangeRole);
            users.add(userToChangeRole);
            showAlert("Role for " + selectedUser + " changed to " + newRole);
            refreshUserListView(user_list);
        } else {
            showAlert("User not found");
        }
	}
	
	@FXML
	public void switchToLogin(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("login.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
    private void refreshUserListView(ListView<String> userListView) {
        userListView.getItems().clear();
        for (User user : users) {
            userListView.getItems().add(user.getUsername() + " - " + user.getRole());
        }
    }
    
    private void deleteUser(String usernameToDelete) {
        User userToDelete = null;
        for (User user : users) {
            if (user.getUsername().equals(usernameToDelete)) {
                userToDelete = user;
                break;
            }
        }
        if (userToDelete != null) {
            users.remove(userToDelete);
        } else {
            return;
        }

        userFileM.deleteUser(usernameToDelete);
    }
    
    private User findUserByUsername(String username) {
    	String[] split = username.split(" ");
        for (User user : users) {
            if (user.getUsername().equals(split[0])) {
                return user;
            }
        }
        return null;
    }
	
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
