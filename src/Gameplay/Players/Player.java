// Author : Stanley Urbanek
// Info   : Player interface to control player interactions

import java.util.*;
import java.util.stream.*;
import java.lang.*;

public interface Player {

    
    public List<Card> getHand();
    public int calculateHandTotal();
    public void updateHand(Deck deck);
    public void clearHand();
    public String handToString();

    public boolean doesStand();
    public boolean doesBust();
    
}
