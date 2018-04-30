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
}
