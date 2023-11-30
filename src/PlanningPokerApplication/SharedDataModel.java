package PlanningPokerApplication;

public class SharedDataModel {
    private static String newUsername;
    private static String newPassword;
    private static String selectedRole;

    private SharedDataModel() {
        // Private constructor to prevent instantiation
    }

    public static String getNewUsername() {
        return newUsername;
    }

    public static void setNewUsername(String newUsername) {
        SharedDataModel.newUsername = newUsername;
    }

    public static String getNewPassword() {
        return newPassword;
    }

    public static void setNewPassword(String newPassword) {
        SharedDataModel.newPassword = newPassword;
    }

    public static String getSelectedRole() {
        return selectedRole;
    }

    public static void setSelectedRole(String selectedRole) {
        SharedDataModel.selectedRole = selectedRole;
    }
}
