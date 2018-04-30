// Author: Stanley Urbanek
// Info:   Display GUI for BlackJack game
//         Uses CardPanel

// Sources : https://introcs.cs.princeton.edu/java/36inheritance/GUI.java.html
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html#SplitPaneDemo
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class BlackJackGUI implements ActionListener { 

    private final BufferedImage offscreenImage; // double buffered image
    private final BufferedImage onscreenImage;  // double buffered image
    private final Graphics2D offscreen;
    private final Graphics2D onscreen;
    private JFrame frame;                       // the top-level component
    private JPanel center = new JPanel();       // center panel
    protected JTextArea textArea;

    private JPanel west;
    private JScrollPane scrollPane;
    private JSplitPane splitPane;
    private JSplitPane otherSplitPlane;
    private JPanel rightPane;

    private CardPanel panel;// = CardPanel.getInstance();
    public GamePlay game;// = new GamePlay(3);// = new GamePlay();

    private int numRounds = 3;

    // create a GUI with a menu, some buttons, and a drawing window of size width-by-height
    public BlackJackGUI(int width, int height, int numRounds) {

	this.game = new GamePlay(numRounds);

        offscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        onscreenImage  = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        offscreen = (Graphics2D) offscreenImage.getGraphics();
        onscreen  = (Graphics2D) onscreenImage.getGraphics();

        // west panel of buttons
        this.west = new JPanel();
        this.west.setLayout(new BoxLayout(this.west, BoxLayout.PAGE_AXIS));
        JButton button1 = new JButton("Start Game");
        JButton button2 = new JButton("Hit");
        JButton button3 = new JButton("Stand");
	
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button1.setToolTipText("Click me");
        this.west.add(button1);
        this.west.add(button2);
        this.west.add(button3);


	/*
	JTextPane pane = new JTextPane();

	String counterText = "Hi-Lo Counting Strategy:\n\n~ With a full deck, runningCount = 0\n\n~ For every card played, change the running count by:\n     Hi Cards    10 J Q K A => RunningCount - 1 \n     Lo Cards     2 3 4 5 6 => RunningCount + 1\n     Zero Cards   7 8 9     => RunningCount + 0\n\n (The logic is that as the favorable Hi cards are used up, \n  there are fewer Hi cards for you to get later in the game. )\n\n ~ As runningCount increases, you are more likely to get favorable Hi cards\n\n ~ Knowing the runningCount, you can adjust your bet accordingly (higher runningCount, higher bet)";
	
        addColoredText(pane, counterText, Color.BLACK);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane.setPreferredSize(new Dimension(400, 400));
        frame.getContentPane().add(pane, BorderLayout.CENTER);
	
        frame.pack();
        frame.setVisible(true);
    }

        StyledDocument doc = pane.getStyledDocument();

        Style style = pane.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);

	doc.insertString(doc.getLength(), text, style);
	*/



	    
        //Create the text area used for output.  Request
        //enough space for 5 rows and 30 columns.
        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        this.scrollPane = new JScrollPane(textArea);

        this.panel = CardPanel.getInstance();
        //Put the editor pane and the text pane in a split pane.
        this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                              this.panel,
                                              this.scrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);

	
        this.rightPane = new JPanel(new GridLayout(1,0));
        this.rightPane.add(this.splitPane);
        this.rightPane.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("BlackJack Game - w Card Counting Trainer"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));

	this.frame = new JFrame();
	this.frame.setPreferredSize(new Dimension(900, 500));

	this.frame.add(this.west, BorderLayout.LINE_START);
	this.frame.add(this.rightPane, BorderLayout.CENTER);

	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);

        this.frame.pack();
	
        // give the focus to the center panel
        this.center.requestFocusInWindow();

        this.frame.setVisible(true);

	this.game = new GamePlay(numRounds);

    }

    public void updateCardDisplay() {
	
        this.panel = CardPanel.getInstance();
        //Put the editor pane and the text pane in a split pane.
        this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                              this.panel,
                                              this.scrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        this.frame.revalidate();
    }
    // draw picture (gif, jpg, or png) centered on (x, y)
    public void picture(int x, int y, String filename) {
        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage();
        offscreen.drawImage(image, x, y, null);
        show();
    }
    
    
    // display the drawing canvas on the screen
    public void show() {
        onscreen.drawImage(offscreenImage, 0, 0, null);
        frame.repaint();
    }
    
    // for buttons and menus
    public void actionPerformed(ActionEvent e) {
        Object cmd = e.getActionCommand();
	String description = null;

	// Handle each button
	if (cmd.equals("Start Game")) {
	    if (this.game.isBegun()) {
		displayResult("Game already started..");
	    }
	    else {
		description = "Starting Game";
		center.requestFocusInWindow();
		displayResult(description);
		this.game.isBegun = true;
		this.game.startNewRound();
		//System.out.println(this.game.myDeck.deckToString());
		//this.startGame();
	    }

	    
	}
        else if (cmd.equals("Hit")) {
	    System.out.println("Button 2 pressed");
	    center.requestFocusInWindow();
	    description = "Player 1 Hits";
	    displayResult(description);
	} 
        else if (cmd.equals("Stand")) {
	    System.out.println("Button 3 pressed");
	    center.requestFocusInWindow();
	    description = "Player 1 Stands";
	    displayResult(description);
	}
        else if (cmd.equals("button 4")) {
	    center.requestFocusInWindow();
	    displayResult(description);
	    System.out.println("Button 4 pressed");
	}
        else {
	    center.requestFocusInWindow();
	    displayResult(description);
	    System.out.println("Unknown action");
	}
	
    }

    protected void displayResult(String actionDescription) {
        textArea.append(actionDescription + '\n');
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    // test client
    public static void main(String[] args) {
        BlackJackGUI gui = new BlackJackGUI(1000, 700, 5);
        gui.picture(0, 0, "map.png");
        gui.show();
    }

}
