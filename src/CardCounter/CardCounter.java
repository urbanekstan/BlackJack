// Author : Stanley Urbanek
// Info   : Card Counting trainer, instantiated by GamePlay
//          Provides post game analysis

import java.util.*;
import java.util.stream.*;
import java.lang.*;

public class CardCounter implements Counter {

    private Map<Integer, List<Card>> playerHistory = new HashMap<>();
    private Map<Integer, List<Card>> dealerHistory = new HashMap<>();
    
    private int totalRunningCount;
    private int[] runningCounts; // Running count at the end of each round
    private int[] userCounts;    // User guesses at running count through game
    
    public CardCounter(int numRounds) {
	totalRunningCount = 0;
	runningCounts = new int[numRounds];
	userCounts = new int[numRounds];
    }
    
    public void logInfo(Player dealer, Player player1, int currentRound) {
    // Stores player and dealer hands, running counts
	
	playerHistory.put(currentRound, player1.getHand());
	dealerHistory.put(currentRound, dealer.getHand());
	System.out.println("log info " + player1.getHand());
	System.out.println("         " + dealer.getHand());
	for (int i=0; i<player1.getHand().size();i++) {
	    int cardValue = player1.getHand().get(i).getRank().value();
	    incrementCount(cardValue);
	}
	for (int i=0; i<dealer.getHand().size();i++) {
	    int cardValue = dealer.getHand().get(i).getRank().value();
	    incrementCount(cardValue);
	}
	runningCounts[currentRound] = totalRunningCount;
	System.out.println("RC IS " + totalRunningCount);
    }

    public void incrementCount(int cardValue) {
    // Increments Running Count based on card value
	
	if ((cardValue==10) || (cardValue==1)) { // Then Hi-Card
	    totalRunningCount = totalRunningCount - 1;
	}
	else if ((cardValue==7) || (cardValue==8) || (cardValue==9)) { // Then 0 Card
	    totalRunningCount = totalRunningCount;
	}
	else { // Then Lo-Card
	    totalRunningCount = totalRunningCount + 1;
	}
    }

    public void updateUserCount(int userCount, int currentRound) {
	this.userCounts[currentRound] = userCount;
    }
    
    public int getRunningCount() {
	return totalRunningCount;
    }

    public int[] getRunningCounts() {
	return runningCounts;
    }
}
