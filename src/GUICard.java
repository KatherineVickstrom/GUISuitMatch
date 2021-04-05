import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * Represents a card object graphically
 */
public class GUICard {
    private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
    private static Icon iconBack;
    static boolean iconsLoaded = false;

    /**
     * Loads card icons into a 2D array. The card back icon
     * is stored separately.
     */
    static void loadCardIcons() {
        if (iconsLoaded) return;
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 14; k++) {
                String fileName = "images/" + Assig5.turnIntIntoCardValue(k) +
                        Assig5.turnIntIntoCardSuit(j) + ".gif";
                iconCards[k][j] = new ImageIcon(fileName);
            }
        }
        iconBack = new ImageIcon("images/BK.gif");
        iconsLoaded = true;
    }

    /**
     * Returns a card's icon.
     *
     * @return null on error
     */
    public static Icon getIcon(Card card) {
        if (card.errorFlag()) return null;
        int row = getRowFromChar(card.getValue());
        int col = suitAsInt(card.getSuit());
        if (iconCards[row][col] == null) return iconBack;
        else return iconCards[row][col];
    }

    /**
     * Returns the card back icon.
     */
    public static Icon getBackCardIcon() {
        return iconBack;
    }

    /**
     * Returns a card suit as an integer.
     */
    public static int suitAsInt(Card.Suit suit) {
        switch (suit) {
            case CLUBS:
                return 0;
            case DIAMONDS:
                return 1;
            case HEARTS:
                return 2;
            case SPADES:
                return 3;
            default:
                return -1;
        }
    }

    /**
     * Returns the correct row for a card.
     * @param character The card's value
     */
    public static int getRowFromChar(char character) {
        switch (character) {
            case 'A':
                return 0;
            case 'T':
                return 9;
            case 'J':
                return 10;
            case 'Q':
                return 11;
            case 'K':
                return 12;
            case 'X':
                return 13;
            default:
                int charToInt = character - '0';
                if (charToInt < 2 || charToInt > 9) return -1;
                return --charToInt;
        }
    }
}