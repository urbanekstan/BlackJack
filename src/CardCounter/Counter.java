// Author : Stanley Urbanek
// Info   : Counter interface for Card Counting trainer

public interface Counter {

    public void logInfo(Player dealer, Player player1, int currentRound);
    public void incrementCount(int cardValue); // Real running count
    public void updateUserCount(int userCount, int currentRound); // User running count
    
    public int getRunningCount();
    public int[] getRunningCounts();
}
