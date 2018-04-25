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
    
    public int numRounds;
    public int currentRound = 1;
    public boolean isBegun = false;
    public boolean isPlayerPhase = false;

    public StandardDeck myDeck; // = new StandardDeck();
    //private Player dealer;
    //private Player player1;
    public Map<Integer, Deck> tableCards;
    
    
    public GamePlay(int numRounds) {
	this.numRounds = numRounds;
	this.myDeck = new StandardDeck();
	
    }


    
    public void startNewRound() {
	System.out.println(this.myDeck.deckToString());
	this.tableCards = this.myDeck.deal(2,2);
	System.out.println(this.tableCards);
	//this.dealer = new Player( this.tableCards.get(1) );
	//System.out.println(this.dealer.getCurrentHand());
	//this.player1 = new Player( this.tableCards.get(2) );
	//System.out.println(this.player1.getCurrentHand());
	//this.myDeck = this.tableCards.get(3);
	//System.out.println(this.myDeck.deckToString());
	//System.out.println(this.dealer.handToString()); 
    }


    public boolean isBegun() {
	return this.isBegun;
    }

    public boolean isPlayerPhase() {
	return this.isPlayerPhase;
    }
    public static void main(String[] args) {
	BlackJackGUI gui = new BlackJackGUI(1000, 700, 5);
	gui.picture(0, 0, "map.png");
	gui.show();
    }
    
}
