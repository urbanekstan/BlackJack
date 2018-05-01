// Author : Stanley Urbanek
// Info   : Counter interface for Card Counting trainer

import java.util.*;
import java.util.stream.*;
import java.lang.*;

public interface Counter {

    public void logInfo(Player dealer, Player player1, int currentRound);
    public void incrementCount(int cardValue); // Real running count
    public void updateUserCount(int userCount, int currentRound); // User running count
    
    public Map<Integer, List<Card>> getPlayerHistory();
    public Map<Integer, List<Card>> getDealerHistory();
    
    public int getRunningCount();
    public int[] getRunningCounts();
    public int[] getUserCounts();
}
