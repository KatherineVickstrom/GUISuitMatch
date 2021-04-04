import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class CardTable extends JFrame {
    static int MAX_CARDS_PER_HAND = 56;
    static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games

    private int numCardsPerHand;
    private int numPlayers;

    public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;
    //Filters input, adds panels to JFrame, establishes layouts
    public CardTable(String title, int numCardsPerHand, int numPlayers) {
        //Validation
        super(title);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        if (numCardsPerHand < 1) numCardsPerHand = 1;
        else if (numCardsPerHand > MAX_CARDS_PER_HAND) numCardsPerHand = MAX_CARDS_PER_HAND;
        if (numPlayers < 1) numPlayers = 1;
        else if (numPlayers > MAX_PLAYERS) numPlayers = MAX_PLAYERS;
        //Initialization

        this.numCardsPerHand = numCardsPerHand;
        this.numPlayers = numPlayers;
        pnlComputerHand = new JPanel();
        pnlHumanHand = new JPanel();
        pnlPlayArea = new JPanel();
        pnlComputerHand.setLayout(new BoxLayout(pnlComputerHand, BoxLayout.LINE_AXIS));
        pnlComputerHand.setLayout(new GridLayout());
        pnlPlayArea.setLayout(new GridLayout(2, 2));
        pnlHumanHand.setLayout(new GridLayout());
        pnlComputerHand.setBorder(BorderFactory.createTitledBorder("Computer Hand"));
        pnlPlayArea.setBorder(BorderFactory.createTitledBorder("Playing Area"));
        pnlHumanHand.setBorder(BorderFactory.createTitledBorder("Your Hand"));
        add(pnlComputerHand);
        add(pnlPlayArea);
        add(pnlHumanHand);
    }
    //Accessors
    public int getNumCardsPerHand() { return numCardsPerHand; }
    public int getNumPlayers() { return numPlayers; }
}
