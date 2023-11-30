package PlanningPokerApplication;

import java.io.*;
import java.util.ArrayList;

public class FileEditor {
	
	private String filename;
	
	public FileEditor(String file) {
        this.filename = file;
    }
    // Writer method to write user data to a text file
    public void writeUsers(ArrayList<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)) /* append mode */) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "," + user.getKey());
                writer.newLine(); // Add a new line after each user
            }
            System.out.println("Data has been written to the text file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Overloaded method to write a single user to a text file
    public void writeUsers(User user) {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        writeUsers(users); // Reuse the existing writeUsers method
    }

    // Reader method to read user data from a text file
    public ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    users.add(new User(parts[0], parts[1],parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public void deleteUser(String username) {
        ArrayList<User> users = readUsers();
        ArrayList<User> usersToKeep = new ArrayList<>();

        for (User user : users) {
            if (!user.getUsername().equals(username)) {
                usersToKeep.add(user);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (User user : usersToKeep) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "," + user.getKey());
                writer.newLine();
            }
            System.out.println("User deleted from the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        // Sample usage
//        String filename = "users.txt";
//        FileEditor f = new FileEditor(filename);
//
//        // Creating some User objects
//        ArrayList<User> userList = new ArrayList<>();
//        userList.add(new User("user1", "password1"));
//        userList.add(new User("user2", "password2"));
//        userList.add(new User("user3", "password3"));
//
//        // Writing multiple users to the text file
//        f.writeUsers(userList);
//
//        // Writing a single user to the text file
//        User singleUser = new User("user4", "password4");
//        f.writeUsers(singleUser);
//
//        // Reading from the text file
//        ArrayList<User> readUsers = f.readUsers();
//
//        // Displaying the read data
//        for (User user : readUsers) {
//            System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());
//        }
//    }
}
