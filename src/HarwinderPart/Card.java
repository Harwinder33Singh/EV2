package HarwinderPart;
public class Card {
    private int playerNumber;
    private String role;
    private int difficulty;

    public Card(int playerNumber, String role, int difficulty) {
        this.playerNumber = playerNumber;
        this.role = role;
        this.difficulty = difficulty;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getRole() {
        return role;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
