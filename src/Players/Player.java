// Author : Stanley Urbanek
// Info   : Player interface to control player interactions

import java.util.*;
import java.util.stream.*;
import java.lang.*;

public interface Player {

    public void clearHand();
    public void updateHand(Deck deck);
    public int calculateHand();
    public int numAces();
    
    public List<Card> getHand();
    public String handToString();

    public boolean doesStand();
    public boolean doesBust();
    public void chooseStand();
    public void chooseBust();
    
}
