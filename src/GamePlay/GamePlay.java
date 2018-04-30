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

public class GamePlay {
    
    private int numRounds;    
    private int currentRound = 1;
    
    private StandardDeck myDeck; // = new StandardDeck();
    private Dealer dealer  = new Dealer();
    private ActivePlayer player1 = new ActivePlayer();
    //private Map<Integer, ActivePlayer> players;
    private Map<Integer, Deck> tableCards;



    public GamePlay(int numRounds) {
	
	// Start new GamePlay by instantiation of objects involved
	this.numRounds = numRounds;
	myDeck = new StandardDeck();
	myDeck.shuffle();
	
    }

    public void playOneRound() {
	dealPhase();
	hitOrStandPhase();
    }

    
    public void dealPhase() {

	// Clear hand and deal new
	// CLELLER
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
	// Displays deal phase by
	//System.out.println( (tableCards.get(1).getCards())[0]);
	System.out.println("Player 1 : " + player1.handToString());
	System.out.println("Dealer   : " + dealer.handToString());
    }

    public void hitOrStandPhase() {
	
	Scanner reader = new Scanner(System.in);  // Reading from System.in
	System.out.println("Hit (0), Stand (1): ");
	int n = reader.nextInt(); // Scans the next token of the input as an int.
	if (n==0) {
	    System.out.println("HIT ");
	} else {
	    System.out.println("STAND");
	}

	
	/*
	while (!player.doesStand()) {
	    

	}
	*/
	//once finished
	reader.close();
    }

    public void displayHitStandPhase() {
	System.out.println(" ");

    }
    public static void main(String[] args) {
	GamePlay game = new GamePlay(2);
	game.playOneRound();




	
	//BlackJackGUI gui = new BlackJackGUI(1000, 700, 5);
	//gui.picture(0, 0, "map.png");
	//gui.show();
    }
    
}
