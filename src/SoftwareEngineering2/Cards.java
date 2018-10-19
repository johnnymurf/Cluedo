package SoftwareEngineering2;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Cards class is used to hold a collection of cards
 * ArrayList<Card> cards = collection of cards
 * int numCard = number of cards add / can be used to get the index of last card added
 * JPanel pane = pane of cards added, used in showFrame() when creating frames
 */
public class Cards {
    private ArrayList<Card> cards; 
    private int numCards;
    private JPanel pane;
    private ArrayList<String> names;
    private static ClueSheet sheet;

    Cards(int num){
        cards = new ArrayList<>();
        numCards = num;
        pane = new JPanel(new GridLayout(1,num, 0, 0));
    }
    public void copyCard(Card card, int index){
        if(index != -1 && index != -2){
            PlayerManager.getPlayerArrayList().get(index).getStringCards().add(card.getCardName());
        }
        if(index == -2){
            Player.getStringMurderCards().add(card.getCardName());
        }
        pane.add(card);
        cards.add(card);
    }
    public JPanel getPane(){
        return pane;
    }

    public void setPane(JPanel panel){
        pane = panel;
    }

    public int getNumCards(){
        return numCards;
    }

    public void setPaneLayout(LayoutManager layout){
        pane = new JPanel(layout);
    }
    public void setClueSheet(ClueSheet clue){
        sheet = clue;
    }

    public ClueSheet getClueSheet(){
        return sheet;
    }

    public ArrayList<Card> getCards(){ return cards;}

    public void addCards(Object[] car){
        for(int i =0;i<car.length;i++){
            cards.add((Card)car[i]);
        }
    }

    public ArrayList<String> getNames(){
        return names;
    }
}
