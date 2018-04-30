// Author: Stanley Urbanek
// Description: GamePlay class controls the gameplay
//              and directly feeds GUI what to update
// Uses:  StandardDeck, ActivePlayer, CardCounter



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
    private int currentRound = 0;
    
    private StandardDeck myDeck;
    private Map<Integer, Deck> tableCards;
    
    private ActivePlayer dealer  = new ActivePlayer();
    private ActivePlayer player1 = new ActivePlayer();
    //private Map<Integer, ActivePlayer> players; // For multiple players

    private CardCounter cardCounter;
    private static Scanner reader; //= new Scanner(System.in);
 
    public GamePlay(int numRounds) {
	
	this.numRounds = numRounds;
	myDeck = new StandardDeck();
	myDeck.shuffle();
	cardCounter = new CardCounter(numRounds);
	reader = new Scanner(System.in);
    }

    public void playAllRounds() {

	System.out.println(Dialogue.INTRO.text());
	System.out.println(Dialogue.RULES.text());
	System.out.println(Dialogue.COUNTING.text());

	System.out.println("We will now start a " + numRounds + " round game. You will be asked the running count at the end of every round.\n");
        while (currentRound < numRounds) {
	    playOneRound();
	}
        
	displayAnalysis();
	System.out.println(Dialogue.ANALYSIS.text());
    }
    
    public void playOneRound() {
	
	System.out.println(" ---- ROUND " + (int)(currentRound+1) + " ---- \n [Press ENTER to DEAL]");
	int p = reader.nextInt();

   
	dealPhase();      // Clear all hands, deal 2 cards
        playerHitPhase(); // Enter Hit/Bust-Stand interaction, need user input
	dealerHitPhase(); // Enter Hit/Bust-Stand interaction, automated
	determineWinner();// Determines winner of round
	
	cardCounter.logInfo(dealer, player1, currentRound); 
	System.out.println("What is the running count? : ");
	int cnt = reader.nextInt();
	cardCounter.updateUserCount(cnt, currentRound);
	if (cnt != cardCounter.getRunningCount()) {
	    System.out.println(" > The correct runningCount = " + cardCounter.getRunningCount());
	    System.out.println(Dialogue.HINT.text());
	}
	currentRound = currentRound + 1;
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
    }

    public void displayDealPhase() {
	// Displays deal phase, replace by GUI
	System.out.println("\n > Player 1 : " + player1.handToString());
	System.out.println(" > Dealer   : " + dealer.handToString() + "\n");
    }

    public void playerHitPhase() {
    // Controls player hit/bust-stand interaction, requires user input
        
	if ( (player1.calculateHand() == 11) && (player1.numAces() == 1) ) {
	    player1.chooseStand();
	    displayHitPhase("Player 1 has 21!");
	}
	// As long as we choose to hit
	while (!player1.doesStand()) {
	    System.out.println("Hit (0), Stand (1): ");
	    int n = reader.nextInt(); // Scans the next token of the input as an int.
	    
	    if (n==0) {
		System.out.println("Player 1 Hits!");
		tableCards = myDeck.deal(1,1);
		player1.updateHand( tableCards.get(1));
		myDeck.updateDeck( tableCards.get(2));
		displayDealPhase();
		if (player1.calculateHand()>21) {
		    System.out.println("Player 1 Busts");
		    player1.chooseStand();
		} else if (player1.calculateHand()==21) {
		    System.out.println("Player 1 Has 21!");
		    player1.chooseStand();
		} else { } // Else, choose to hit or stand again
		
	    } else {
		System.out.println("Player 1 : Stands");
		player1.chooseStand();
	    }
	}
    }

    public void dealerHitPhase() {
    // Controls player hit/bust-stand interaction, automatated hit on 17
	
        displayHitPhase("\nDealer reveals ");
	// First check for 21
	if ( (dealer.calculateHand() == 11) && (dealer.numAces() == 1) ) {
	    dealer.chooseStand();
	    displayHitPhase("Dealer has 21!");
	}
	while (!dealer.doesStand()) {
	    // Otherwise, as long as dealer chooses not to stand (17 or under)
	    if (dealer.calculateHand() <= 17) {
		// Then hit
		tableCards = myDeck.deal(1,1);
		dealer.updateHand(tableCards.get(1));
		myDeck.updateDeck(tableCards.get(2));
		displayHitPhase("Dealer hits ");
		displayDealPhase();
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
	displayWinner("Final Hands ~ \n > Player 1 : " + player1.handToString() + " \n > Dealer   : " + dealer.handToString() + "\n");
    }

    public void displayWinner(String s) {
	System.out.println(s);
    }

    public void displayAnalysis( ) {

	System.out.println(" ----- GAME OVER ------ \nNow for your post game analysis: \n");
        System.out.println(" Rounds: " + numRounds);

	System.out.println("\n Player 1 Cardlog" + cardCounter.getPlayerHistory());
        System.out.println("\n Dealer Cardlog " + cardCounter.getDealerHistory());
	/*
        print(' Player Cardlog ' + str(self.playerFaces))
        print('\n Dealer counts    ' + str(self.dealerCounts))
        print(' Player counts    ' + str(self.playerCounts))
        print('\n Running Count  ' + str(self.runningCount))
        print(' True Count*    ' + str(self.runningCount))
        print(' Bet^           ' + 'Will add later')
	*/

    }
    
    public static void main(String[] args) {
	//GamePlay game = new GamePlay(Integer.parseInt(args[0]));
	GamePlay game = new GamePlay(2);
	game.playAllRounds();

	//BlackJackGUI gui = new BlackJackGUI(1000, 700, 5);
	//gui.picture(0, 0, "map.png");
	//gui.show();
    }
    
}
