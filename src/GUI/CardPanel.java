// Author : http://java.macteki.com/
// Edited : Stanley Urbanek
// Info   : Create card display

import java.awt.image.BufferedImage;
import java.awt.Font;
 
class CardPanel extends javax.swing.JPanel
{

    String[] dealer = {"  ","  ","  ","  ","  ","  "};
    String[] player = {"  ","  ","  ","  ","  ",""};
    String[] deck={
	"DA","D2","D3","D4","D5","D6","D7","D8","D9","DT","DJ","DQ","DK",  // diamond
	"HA","H2","H3","H4","H5","H6","H7","H8","H9","HT","HJ","HQ","HK", // heart
	"SA","S2","S3","S4","S5","S6","S7","S8","S9","ST","SJ","SQ","SK", // spade

	"CA","C2","C3","C4","C5","C6","C7","C8","C9","CT","CJ","CQ","CK" // club

    };
 
    private BufferedImage[] cardImage;//=createAllImage();
    
    private BufferedImage[] fullDisplay;

    public CardPanel() {
	this.cardImage=createAllImage();
    }

    // If doing more than 1 dealer and 1 player, adjust setPreferredSize 
    public static CardPanel getInstance() {
	CardPanel panel=new CardPanel();
	panel.setBackground(new java.awt.Color(255,255,128));
	panel.setPreferredSize(new java.awt.Dimension(500,200));
	return panel;
    }

    // If doing more than 1 dealer and 1 player, need to fix this
    public BufferedImage[] createAllImage() {
	BufferedImage[] imageArray = new BufferedImage[12];
	imageArray[0]=createLabelImage("Player 1");
	imageArray[6]=createLabelImage("Dealer");
	
	for (int i=1;i<6;i++) {
	    imageArray[i]=createImage(deck[i]);
	    imageArray[i+6]=createImage(dealer[i]);
	}
	
	return imageArray;
    }

    public BufferedImage createLabelImage(String whichPlayer) {

	// create a card image.
	int cardWidth=60, cardHeight=80;
	BufferedImage image=new BufferedImage(cardWidth, cardHeight, BufferedImage.TYPE_INT_ARGB);

	// get a graphics object of the image for drawing.
	java.awt.Graphics2D gr = (java.awt.Graphics2D) image.getGraphics();
 
	// draw a white playing card
	gr.setColor(java.awt.Color.WHITE);
	gr.fillRect(0,0,cardWidth,cardHeight);
 
	// draw the "three of Spade"
	Font font=new Font("Dialog",Font.PLAIN, 14);
	gr.setFont(font);
	
	//java.awt.Color color=java.awt.Color.BLACK;
	int x=5;
	gr.setColor(java.awt.Color.BLACK);
	/////////////
	gr.drawString(whichPlayer,x,45);
 
	return image;  
    }
    // sample : createImage("S3");    
    public BufferedImage createImage(String card) {

	// create a card image.
	int cardWidth=60, cardHeight=80;
	BufferedImage image=new BufferedImage(cardWidth, cardHeight, BufferedImage.TYPE_INT_ARGB);

	if (card.isEmpty()) {
	    return image;
	}
	// get a graphics object of the image for drawing.
	java.awt.Graphics2D gr = (java.awt.Graphics2D) image.getGraphics();
 
	// draw a white playing card
	gr.setColor(java.awt.Color.WHITE);
	gr.fillRect(0,0,cardWidth,cardHeight);
	// with black border
	gr.setColor(java.awt.Color.BLACK);  
	gr.drawRect(0,0,cardWidth-1,cardHeight-1);
 
	// draw the "three of Spade"
	Font font=new Font("Dialog",Font.PLAIN, 28);
	gr.setFont(font);
 
	String prefix=card.substring(0,1);  // first character
	String postfix=card.substring(1,2); // second character
	String suit="";
	java.awt.Color color=java.awt.Color.BLACK;
	if (prefix.equals("S")) {
	    suit="\u2660";   // unicode for the "spade" character
	}
	else if (prefix.equals("H")) {
	    suit="\u2665";   // unicode for the "heart" character
	    color=java.awt.Color.RED;
	}
	else if (prefix.equals("C")) {
	    suit="\u2663";
	}
	else if (prefix.equals("D")) {
	    suit="\u2666";   // unicode for the "diamond" character
	    color=java.awt.Color.RED;
	}
	else {
	    suit=" ";
	}
	
	String point=postfix;
	int x=5;
	if (postfix.equals("T")) {
	    x=1;
	    point="10";  // special handling for "ten"
	}
  
	gr.setColor(color);
	gr.drawString(suit+point,x,45);
 
	return image;    
    }
 
    // override the paint method
    public void paintComponent(java.awt.Graphics graphics) {
	super.paintComponent(graphics);  // paint background 
 
	// get panel dimemsion
	int w=getWidth();  
	int h=getHeight(); 
	
	// create a Graphics2D object for drawing shape
	java.awt.Graphics2D gr=(java.awt.Graphics2D) graphics;
      
	int y=0;
	int x=0;
	for (int i=0;i<12;i++) {      
	    gr.drawImage(cardImage[i],x,y,this);  // draw a card
	    x+=61;
	    if ((i+1)%6==0) {y+=81; x=0;}
	}
	
    }


     /**
     * Create the GUI and show it. As with all GUI code, this must run
     * on the event-dispatching thread.
        */
    private static void createAndShowGUI() {
        //Create and set up the window.
        javax.swing.JFrame frame = new javax.swing.JFrame("BlackJack");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	
	// create graphics panel and add it to the frame
	CardPanel panel=CardPanel.getInstance();    


	frame.add(panel);  // panel size is 800x400, see getInstance()
	frame.pack();  // this will correctly set the size of frame 
	
        //Display the window.
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
