// Author: Stanley Urbanek
// Description: Phases interface is for GamePlay class

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

public interface Phases {

    public void playAllRounds();
    public void playOneRound();
    
    public void dealPhase();
    public void displayDealPhase();

    public void playerHitPhase();
    public void dealerHitPhase();
    public void displayHitPhase(String s);

    public void determineWinner();
    public void displayWinner(String s);

    public enum Dialogue {
	INTRO(1, "The BlackJack Card Counting Trainer\n ~ Uses Hi-Lo Counting Strategy\n ~ By Stan Urbanek\n\n "),
	RULES (2, "Quick BlackJack Rules:\n ~ The object of the game is to get as close to 21 without going over\n ~ Go over 21 and you bust (lose)\n ~ Each player is dealt 2 cards (Aces count as either 1 or 11\n ~ Players then choose between \n   ~ Hitting (drawing) a card from the deck\n   ~ Standing (staying) w current hand \n\n"),
	COUNTING (3, "Hi-Lo Counting Strategy:\n ~ For every card displayed\n     Hi Cards    10 J Q K A => RunningCount - 1 \n     Lo Cards     2 3 4 5 6 => RunningCount + 1\n     Zero Cards   7 8 9     => RunningCount + 0\n    (The logic is that as the favorable Hi cards are used up, there are fewer Hi cards for you to get later in the game)\n ~ Your bet size would be proportional to this running count"),
	ANALYSIS (4, "\n\n*True Count is used to determine how to bet. \n True Count = [Running Count] / [Decks left ~ rounded to nearest 1/2 deck] \n Decks left ~ 1 and 0.5 in our case\n^Bet is the recommended bet\n Bet = TrueCount - 1 ( Betting Unit)\n Assume 1 Betting Unit = $100\nSubtract 1 from the True Count to determine how many units to bet. Multiply the number of units to bet by your betting unit. \n\nFor Example, your betting unit is 100, running count is +10, true count is +5, then the optimal bet would be 4X100 which gives us 400");

	private final int value;
        private final String text;
        Dialogue(int value, String text) {
	    this.value = value;
	    this.text = text;
        }
        public int value() {return value;}
        public String text() {return text;}
    }
}

