// Author : Stanley Urbanek
// Info   : Card Counting trainer, instantiated by GamePlay
//          Provides post game analysis

public class CardCounter {

    public int totalRunningCount;
    public int[] runningCount;
    
    public String[] dealerHands;
    public int[] dealerRoundCount;

    public String[] player1Hands;
    public int[] playerRoundCount;

    public CardCounter(int numRounds) {
	this.totalRunningCount = 0;
	//this.runningCount
    }
    public void updateCounts(Player Dealer, Player Player1) {

	
    }
}
