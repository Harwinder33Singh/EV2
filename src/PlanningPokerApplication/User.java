package PlanningPokerApplication;

import java.io.Serializable;

public class User implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    private String role;
    private String key;
    
    User(String username, String password, String role, String key) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getRole() {
    	return role;
    }
    
    public String getKey() {
    	return key;
    }
    
    public void changeUserRole(String newRole) {
            this.role = newRole;
    }
    
}