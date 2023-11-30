package application;

public class DiscussionThoughts {
	private int number;
	private String role;
	private String discussion;
	
	public DiscussionThoughts(int number, String role, String discussion) {
        this.number = number;
        this.role = role;
        this.discussion = discussion;
    }

    // Getters
    public int getnumber() {
        return number;
    }

    public String getRole() {
        return role;
    }

    public String getDiscussion() {
        return discussion;
    }

    // Setters
    public void setnumber(int number) {
        this.number = number;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }
	
}
