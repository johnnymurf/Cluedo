package SoftwareEngineering2;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class takes an ArrayList of Card as Constructor argument and assigns them
 * to cards. readSheet() then reads in Clue Sheet (clue_sheet.jpg)
 *  An overridden paintComponent method then paints a red x on each cards
 *  respective position on the Clue Sheet for the player's cards and a red A
 *  on any extra cards if any exist.
 */

public class ClueSheet extends JPanel{

    private BufferedImage sheet;
    private ArrayList<Card> cards, extras, viewed;
    private int x, y, numPlayers;

    ClueSheet(ArrayList<Card> card, int num){
        viewed = new ArrayList<>();
        numPlayers = num;
        cards = card;
        readSheet();
    }
    public void readSheet(){
        try{
            sheet = ImageIO.read((this.getClass().getResource(PathConstants.CLUE_SHEET_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addToViewedCard(Card card){
        viewed.add(card);
    }

    public ArrayList<Card> getViewed(){
        return viewed;
    }

    public void addExtraCards(Cards card){
        extras = new ArrayList<>();
        extras.addAll(card.getCards());
    }

    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );
        g2.drawImage( sheet, null, 0, 0);
        g2.setColor(Color.RED);
        setOpaque(true);
        int i,num = 18/numPlayers;
        /* Player's Cards */
        for(i=0;i<num;i++) {
            y = cards.get(i).getCol()+5;
            x = cards.get(i).getRow()-110;
            g2.drawString("x", x, y);
        }
        i=0;
        /* Extra Cards */
        while (i<18%numPlayers) {
            y = extras.get(i).getCol() + 5;
            x = extras.get(i).getRow() - 110;
            g2.drawString("A", x, y);
            i++;
        }

        if(viewed.size()>0){
            Iterator<Card> it = viewed.iterator();
            while (it.hasNext()){
                Card c = it.next();
                y = c.getCol()+6;
                x = c.getRow() - 112;
                g2.drawString("V", x, y);
            }
        }
    }

    public BufferedImage getSheet(){
        return sheet;
    }
}
