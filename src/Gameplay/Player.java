// Author : Stanley Urbanek
// Info   : Player interface to control player interactions

import java.util.*;
import java.util.stream.*;
import java.lang.*;

public class Player {

    //public List<Card> currentHand;
    public List<Card> currentHandL;// = new StandardDeck(new List<Card> cards);
    public String[] currentHandS;
    
    public Player() {
	//List<card> blank = new ArrayList<>();
	//this.currentHand = new ArrayList<>();

    }
    
    
    public void newHand() {

    }

    public void hit(Card card) {
	this.currentHandL.add(card);
    }

    public List<Card> getCurrentHand() {
	return this.currentHandL;
    }


}
