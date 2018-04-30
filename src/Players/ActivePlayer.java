// Author : Stanley Urbanek
// Info   : Active Player class to implement player interface
//          Manages player's hand and table interactions

import java.util.*;
import java.util.stream.*;
import java.lang.*;

public class ActivePlayer implements Player {

    private List<Card> currentHand;// = new StandardDeck(new List<Card> cards);
    private int currentHandTotal;
    private int numAces;
    private boolean doesStand;
    private boolean doesBust;
    
    public ActivePlayer() {
	clearHand();
    }
    
    public List<Card> getHand() {
	return this.currentHand;
    }

    public int calculateHandTotal() {
	for (int i = 0; i < currentHand.size(); i++) {
	    System.out.println( (currentHand.get(i)).getRank().value());
	    currentHandTotal = (currentHand.get(i)).getRank().value() + currentHandTotal;
	}
	return currentHandTotal;
    }

    public void updateHand(Deck deck) {
	this.currentHand = deck.getCards();
	System.out.println("End hand total: " + calculateHandTotal());
    }

    public void clearHand() {
	currentHand = new ArrayList<Card>();
	currentHandTotal = 0;
	doesStand = false;
	doesBust = false;
    }

    public String handToString() {
        return this.currentHand
            .stream()
            .map(Card::toString)
            .collect(Collectors.joining(", "));
    }

    public boolean doesStand() {
	return doesStand;
    }

    public boolean doesBust() {
	return doesBust;
    }
    
}

