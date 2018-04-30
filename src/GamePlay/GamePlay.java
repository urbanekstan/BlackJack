// Author: Stanley Urbanek
// Description: GamePlay class controls the gameplay
//              and directly feeds GUI what to update (frames)
// Uses:  StandardDeck, Player, CardCounter, CardCounterGUI
// Feeds: BlackJack GUI

import java.util.*;
import java.util.stream.*;
import java.lang.*;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class GamePlay implements Phases {
    
    private int numRounds;    
    private int currentRound = 1;
    
    private StandardDeck myDeck;
    private Map<Integer, Deck> tableCards;
    
    private Dealer dealer  = new Dealer();
    private ActivePlayer player1 = new ActivePlayer();
    //private Map<Integer, ActivePlayer> players; // For multiple players

    public GamePlay(int numRounds) {
	
	this.numRounds = numRounds;
	myDeck = new StandardDeck();
	myDeck.shuffle();
    }

    public void playOneRound() {
	dealPhase();
        playerHitPhase();
	dealerHitPhase();
	determineWinner();
    }

    public void dealPhase() {

	// Clear hand and deal new
	dealer.clearHand();
	player1.clearHand();
	tableCards = myDeck.deal(2,2);

	// Update where cards are
	player1.updateHand( tableCards.get(1));
	dealer.updateHand( tableCards.get(2));
	myDeck.updateDeck( tableCards.get(3));
	displayDealPhase();
	/*
	System.out.println( (tableCards.get(1)).deckToString());
	System.out.println( (tableCards.get(2)).deckToString());
	System.out.println( (tableCards.get(3)).size());
	*/
    }

    public void displayDealPhase() {
	// Displays deal phase, replace by GUI
	System.out.println("Player 1 : " + player1.handToString());
	System.out.println("Dealer   : " + dealer.handToString());
    }

    public void playerHitPhase() {
	
	Scanner reader = new Scanner(System.in);  // Reading from System.in
	System.out.println("Hit (0), Stand (1): ");
	int n = reader.nextInt(); // Scans the next token of the input as an int.
	if (n==0) {
	    System.out.println("Player 1 Hits");
	    tableCards = myDeck.deal(1,1);
	    player1.updateHand( tableCards.get(1));
	    myDeck.updateDeck( tableCards.get(2));
	} else {
	    System.out.println("Player 1 Stands");
	}

	
	/*
	while (!player.doesStand()) {
	    

	}
	*/
	//once finished
	reader.close();
    }

    public void dealerHitPhase() {
        displayHitPhase("Dealer reveals");
	// First check for 21
	System.out.println("dealer has total: "+dealer.calculateHand());
	if (dealer.calculateHand() == 21) {
	    dealer.chooseStand();
	    displayHitPhase("Dealer has 21!");
	}
	
	while (!dealer.doesStand()) {
	    System.out.println("!doesStand() dealer : " + dealer.calculateHand());
	    // Otherwise, as long as dealer chooses not to stand (17 or under)
	    if (dealer.calculateHand() <= 17) {
		// Then hit
		tableCards = myDeck.deal(1,1);
		dealer.updateHand(tableCards.get(1));
		myDeck.updateDeck(tableCards.get(2));
		displayHitPhase("Dealer hits");
		System.out.println(dealer.handToString());
		// Did dealer bust?
		if (dealer.calculateHand() > 21) {
		    displayHitPhase("Dealer busts");
		    dealer.chooseStand();
		    dealer.chooseBust();
		}
		// Did dealer get 21?
		else if (dealer.calculateHand() == 21) {
		    displayHitPhase("Dealer has 21!");
		    dealer.chooseStand();
		}
		// Otherwise, dealer will loop again
		else { }
	    } else { // Else, dealer is between 17 and 21
		displayHitPhase("Dealer stands");
		dealer.chooseStand();
	    }
	}

	displayHitPhase("End hit-stand phase");
    }

    public void displayHitPhase(String s) {
	System.out.println(s);
    }

    public void determineWinner() {
	if (player1.doesBust()) {
	    displayWinner("Player 1 loses by bust");
	}
	else if (dealer.doesBust()) {
	    displayWinner("Player 1 wins bc dealer bust");
	}
	else if (dealer.calculateHand() < player1.calculateHand()) {
	    displayWinner("Player 1 wins!");
	}
	else if (dealer.calculateHand() == player1.calculateHand()) {
	    displayWinner("Player 1 ties w Dealer");
	}
	else {
	    displayWinner("Dealer wins");
	}
    }

    public void displayWinner(String s) {
	System.out.println(s);
    }
    public static void main(String[] args) {
	GamePlay game = new GamePlay(2);
	game.playOneRound();




	
	//BlackJackGUI gui = new BlackJackGUI(1000, 700, 5);
	//gui.picture(0, 0, "map.png");
	//gui.show();
    }
    
}
