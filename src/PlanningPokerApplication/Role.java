package PlanningPokerApplication;

import java.util.ArrayList;
import java.util.List;

public class Role{
    private static List<String> allRoles = new ArrayList<>();
    
    public Role() {
    	allRoles.add("Administrator");
    	allRoles.add("User");
    }
    
    public Role(String[] newRoles) {
        for(int i = 0; i < newRoles.length; i++) {
        	allRoles.add(newRoles[i]);
        }
    }

    public static void addRole(String role, User adminUser) {
        if (adminUser.getRole().equals("Administrator")) {
            allRoles.add(role);
        }
    }

    public static void removeRole(String role, User adminUser) {
        if (adminUser.getRole().equals("Administrator")) {
            allRoles.remove(role);
        }
    }

    public List<String> getAllRoles() {
        return allRoles;
    }
}