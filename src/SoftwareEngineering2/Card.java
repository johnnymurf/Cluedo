package SoftwareEngineering2;
import javax.swing.*;
import java.awt.*;

/**
 * Card class
 * ImageIcon icon = the image of the card
 * String name = name of the card
 * Point location = the cards respective checkbox position on the Clue Sheet
 */
public class Card extends JLabel {

    private final ImageIcon icon;
    private final String name;
    private Point location;

    Card(ImageIcon ic, String text, int x, int y){
        super(ic);
        icon = ic;
        name = text;
        location = new Point(x, y);
    }
    public final ImageIcon getCardIcon() { return icon; }

    public final String getCardName() { return name; }

    public final int getRow() { return location.x; }

    public final int getCol() { return location.y; }
}
