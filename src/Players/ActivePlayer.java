// Author : Stanley Urbanek
// Info   : Active Player class to implement player interface
//          Manages player's hand and table interactions

import java.util.*;
import java.util.stream.*;
import java.lang.*;

public class ActivePlayer implements Player {

    private List<Card> currentHand = new ArrayList<Card>();
    private int currentHandTotal;
    private int numAces;
    private boolean doesStand;
    private boolean doesBust;
    
    public ActivePlayer() {
	clearHand();
    }

    public void clearHand() {
	currentHand.clear();
	currentHandTotal = 0;
	numAces = 0;
	doesStand = false;
	doesBust = false;
    }

    public void updateHand(Deck deck) {
	if (deck.size() == 2) { // Then adding a dealt hand
	    currentHand.add(deck.getCards().get(0));
	    currentHand.add(deck.getCards().get(1));

	    if (deck.getCards().get(0).getRank().text() == "Ace") {
		numAces = numAces + 1;
	    }
	    if (deck.getCards().get(1).getRank().text() == "Ace") {
		numAces = numAces + 1;
	    }
	} else {
	    currentHand.add(deck.getCards().get(0));
	    if (deck.getCards().get(0).getRank().text() == "Ace") {
		numAces = numAces + 1;
	    }
	}
    }

    public int calculateHand() {
	currentHandTotal = 0;
	for (int i = 0; i < currentHand.size(); i++) {
	    //System.out.println( (currentHand.get(i)).getRank().value());
	    currentHandTotal = (currentHand.get(i)).getRank().value() + currentHandTotal;
	}
	return currentHandTotal;
    }

    public int numAces() {
	return numAces;
    }
   
    public List<Card> getHand() {
	return this.currentHand;
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

    public void chooseStand() {
	doesStand = true;
    }

    public void chooseBust() {
	doesBust = true;
    }
}

