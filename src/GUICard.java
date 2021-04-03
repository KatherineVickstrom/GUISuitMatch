import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;

public class GUICard {
    private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
    private static Icon iconBack;
    static boolean iconsLoaded = false;
    static void loadCardIcons() {
        if (iconsLoaded) return;
        File dir = new File("images\\");
        for (File file : dir.listFiles()) {
            String fileName = file.getName();
            int row = getRowFromChar(fileName.charAt(0));
            int col = getColFromChar(fileName.charAt(1));
            if (row == -1) iconBack = new ImageIcon(file.getAbsolutePath());
            else if (col != -1) iconCards[row][col] = new ImageIcon(file.getAbsolutePath());
        }
        iconsLoaded = true;
    }
    public static Icon getIcon(Card card) {
        if (card.errorFlag()) return null;
        int row = getRowFromChar(card.getValue());
        int col = suitAsInt(card.getSuit());
        return iconCards[row][col];
    }
    public static Icon getBackCardIcon() {
        return iconBack;
    }
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
    public static int getColFromChar(char character) {
        switch (character) {
            case 'C':
                return 0;
            case 'D':
                return 1;
            case 'H':
                return 2;
            case 'S':
                return 3;
            default:
                return -1;
        }
    }
}