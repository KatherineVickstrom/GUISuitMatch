import java.awt.*;
import java.util.concurrent.Flow;
import javax.swing.*;
import javax.swing.border.*;

public class Assig5
{
    // static for the 57 icons and their corresponding labels
    // normally we would not have a separate label for each card, but
    // if we want to display all at once using labels, we need to.

    // 52 + 4 jokers + 1 back-of-card image
    static final int NUM_CARD_IMAGES = 57; 
    static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];
    static int NUM_CARDS_PER_HAND = 7;
    static int  NUM_PLAYERS = 2;
    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
    static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
    static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 

    /*
     * Build the file names. For each file name, read it in and use it to
     * instantiate each of the 57 Icons in the icon[] array.
     */
    static void loadCardIcons()
    {
       int indexTracker = 0;
       for (int j = 0; j < 4; j++)
       {
           for (int k = 0; k < 14; k++)
           {
               String fileName = "images/" + turnIntIntoCardValue(k) +
                       turnIntIntoCardSuit(j) + ".gif";
               icon[indexTracker++] = new ImageIcon(fileName);
           }
       }
       icon[indexTracker] = new ImageIcon("images/BK.gif");
    }

    // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
    static String turnIntIntoCardValue(int k)
    {
       String[] cardValue = { "A", "2", "3", "4", "5", "6", "7", "8", "9", 
             "T", "J", "Q", "K", "X" };
       if (k > -1 && k < 14)
          return cardValue[k];
       else
          return "";
    }

    // turns 0 - 3 into "C", "D", "H", "S"
    static String turnIntIntoCardSuit(int j)
    {
       String[] cardSuit = { "C", "D", "H", "S" };
       if (j > -1 && j < 4)
          return cardSuit[j];
       else
          return "";
    }

    /**
     * returns a new random card for the main to use in its tests
     */
    public static Card randomCardGenerator() {
        // Use the methods above to get a random card and assign to string
        String randomSuit = turnIntIntoCardSuit((int) (Math.random() * 3));
        String randomValue = turnIntIntoCardValue((int) (Math.random() * 13));
        // Determine the suit of the random card from the string 
        Card.Suit suit = null;
        switch(randomSuit) {
            case "C": suit = Card.Suit.CLUBS;
                break;
            case "D": suit = Card.Suit.DIAMONDS;
                break;
            case "H": suit = Card.Suit.HEARTS;
                break;
            case "S": suit = Card.Suit.SPADES;
                break;
            default:
                break;
        }
        // The string randomValue should only be 1 character at the first index
        return new Card(randomValue.charAt(0), suit);
    }

    // a simple main to throw all the JLabels out there for the world to see
    public static void main(String[] args)
    {
        int k;
        Icon tempIcon;

        // establish main frame in which program will run
        CardTable myCardTable 
           = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
        myCardTable.setSize(800, 600);
        myCardTable.setLocationRelativeTo(null);
        myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // CREATE LABELS ---------------------------------------------------
        GUICard.loadCardIcons();
        for (int i = 0; i < NUM_CARDS_PER_HAND; ++i) {
            humanLabels[i] = new JLabel(GUICard.getIcon(randomCardGenerator()));
        }
        for (int i = 0; i < NUM_CARDS_PER_HAND; ++i) {
            computerLabels[i] = new JLabel(GUICard.getBackCardIcon());
        }
        // ADD LABELS TO PANELS ----------------------------------------        
        for (int i = 0; i < NUM_CARDS_PER_HAND; ++i) {
            myCardTable.pnlHumanHand.add(humanLabels[i]);
        }
        for (int i = 0; i < NUM_CARDS_PER_HAND; ++i) {
            myCardTable.pnlComputerHand.add(computerLabels[i]);
        }

        // and two random cards in the play region (simulating a computer/hum ply)
        myCardTable.pnlPlayArea.add(
            new JLabel(GUICard.getIcon(randomCardGenerator())));
        myCardTable.pnlPlayArea.add(
            new JLabel(GUICard.getIcon(randomCardGenerator())));
        myCardTable.pnlPlayArea.add(new JLabel("Computer", JLabel.CENTER));
        myCardTable.pnlPlayArea.add(new JLabel("You", JLabel.CENTER));

        // show everything to the user
        myCardTable.setVisible(true);
    }
}