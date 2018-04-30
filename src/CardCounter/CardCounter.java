// Author : Stanley Urbanek
// Info   : Card Counting trainer, instantiated by GamePlay
//          Provides post game analysis

public class CardCounter implements Counter {

    private int totalRunningCount;
    private int[] runningCount;
    
    private String[] dealerHands;
    private int[] dealerRoundCount;

    private String[] player1Hands;
    private int[] playerRoundCount;

    public CardCounter(int numRounds) {
	this.totalRunningCount = 0;
	//this.runningCount
    }
    public void updateCounts(Player Dealer, Player Player1) {

	
    }
}
