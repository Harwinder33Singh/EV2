package HarwinderPart;

import java.util.List;

//Singleton class to hold and manage a list of Card objects
public final class CardHolder {
		  private List<Card> card;
		  // Singleton instance of CardHolder
		  private final static CardHolder INSTANCE = new CardHolder();
		  
		  private CardHolder() {}
		  
		  public static CardHolder getInstance() {
		    return INSTANCE;
		  }
		  
		  public void setCard(List<Card> c) {
		    this.card = c;
		  }
		  
		  public List<Card> getCard() {
		    return this.card;
		  }
}
