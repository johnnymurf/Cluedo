package SoftwareEngineering2;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static SoftwareEngineering2.PlayerManager.getPlayerArrayList;

/**
 * Class that reads in Image files for the cards, picks the murder cards,
 * shuffles and deals the remaining, then sets up each players Clue Sheet
 * along with the frame for their cards
 * JFrame murderFrame = murderCards/Envelope
 * ArrayList<Card> rooms, suspects, weapons, cards = collections of cards to be
 * used to ascertain what type a card is i.e. room, suspect or weapon
 * Cards[] playersCards = array of cards with each element corresponding to
 * a singles players cards i.e. playersCards[1] = cards for player 1
 * Cards murderCards = as both the murder cards are universal we store them
 * here as opposed to a copy for each player
 * Cards surplusCards = stores the extra cards but a copy must be saved in each
 * players class to be displayed with their other cards
 * int numOfPlayers = number of players
 */
public class CardSetup  {
    private static final int CARD_WIDTH = 120, CARD_LENGTH = 150;
    private static JFrame murderFrame;
    private static ArrayList<Card> rooms, suspects, weapons, cards;
    private static Cards[] playersCards;
    private static Cards surplusCards, murderCards;
    private static int numOfPlayers;
    private ArrayList<Player> playerArray;

    public CardSetup(int numberOfPlayers){
        rooms = new ArrayList<>();
        suspects = new ArrayList<>();
        weapons = new ArrayList<>();
        cards = new ArrayList<>();
        playerArray = getPlayerArrayList();
        numOfPlayers = numberOfPlayers;
        playersCards= new Cards[numOfPlayers];
        surplusCards = new Cards(18%numOfPlayers);
        setupCards();
    }

    public void setupCards(){
        readCardIcons();
        setMurderCards();
        shuffleCards();
        setPlayersCards();
        setClueSheets();
    }
    /** Reads all cards assigning them to their respective group **/
    public void readCardIcons(){
        try {
            for (int i = 0; i < 9; i++) {
                Image im = ImageIO.read(this.getClass().getResource(PathConstants.CARD_FILE_NAMES[i]));
                rooms.add(i, new Card(new ImageIcon(im), PathConstants.CARD_FILE_NAMES[i], 198, PathConstants.CARD_LOCATIONS[i]));
            }
            for (int i = 9; i < 15; i++) {
                Image im = ImageIO.read(this.getClass().getResource(PathConstants.CARD_FILE_NAMES[i]));
                suspects.add(i - 9, new Card(new ImageIcon(im), PathConstants.CARD_FILE_NAMES[i], 198, PathConstants.CARD_LOCATIONS[i]));
            }
            for (int i = 15; i < 21; i++) {
                Image im = ImageIO.read(this.getClass().getResource(PathConstants.CARD_FILE_NAMES[i]));
                weapons.add(i - 15, new Card(new ImageIcon(im), PathConstants.CARD_FILE_NAMES[i], 198, PathConstants.CARD_LOCATIONS[i]));
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    /** Picks the murder cards, stores them in murderCards and removes them for their respective group **/
    public void setMurderCards(){
        murderCards = new Cards(3);
        murderCards.setPaneLayout(new GridLayout(3,1, 0, 0));
        Random rand = new Random();
        int  n = rand.nextInt(9);
        murderCards.copyCard(rooms.remove(n), -2);
        n = rand.nextInt(6);
        murderCards.copyCard(suspects.remove(n), -2);
        n = rand.nextInt(6);
        murderCards.copyCard(weapons.remove(n), -2);
    }
    /** Shuffles the cards by adding them all to a List<Card> list then calling Collections.shuffle(list)
     *  The list is then added back to ArrayList<Card> cards **/
    public void shuffleCards(){
        Iterator<Card> iter;
        iter = rooms.iterator();
        do{
            Card lab = iter.next();
            cards.add(lab);
        }while(iter.hasNext());
        iter = suspects.iterator();
        do{
            Card lab = iter.next();
            cards.add(lab);
        }while(iter.hasNext());
        iter = weapons.iterator();
        do{
            Card lab = iter.next();
            cards.add(lab);
        }while(iter.hasNext());
        List<Card> list = cards;
        Collections.shuffle(list);
        cards = (ArrayList<Card>) list;
    }
    /** Assigns an even amount (18/numOfPlayers) cards to each player and adds
     * the remainder to surplusCards
     * NB if(j<numOfPlayers-1){ j++;
            }else { j=0; }
     The segment above alternates between each player dealing a card to each one at a time

     Next the cards and panes for each player are assigned and then setFrames() with
     player array (PlayerManager.getPlayerArrayList()) is called
     * **/
    public void setPlayersCards(){
        int i, j=0;
        int cardsPerPerson = 18/numOfPlayers;
        int extraCards = 18%numOfPlayers;

        Iterator<Card> it = cards.iterator();
        for(i=0;i<numOfPlayers;i++){
            playersCards[i] = new Cards(cardsPerPerson);
        }
        i=0;
        while(i<(cardsPerPerson*numOfPlayers)){
            i++;
            playersCards[j].copyCard(it.next(), j);
            if(j<numOfPlayers-1){ j++;
            }else { j=0; }
        }

        for(i=0;i<extraCards;i++){
            surplusCards = new Cards(cardsPerPerson);
        }
        while(extraCards>0){
            surplusCards.copyCard(it.next(), -1);
            extraCards--;
        }
        for(i=0;i<numOfPlayers;i++){
            getPlayerArrayList().get(i).setCards((playersCards[i].getCards()));
            getPlayerArrayList().get(i).getCards().setPane(playersCards[i].getPane());
        }
        setFrames(); 

    }

    /** This method first allocates the surplus cards (if there are any)
     *  to a frame this is then copied to each players class
     *  The cards are then assigned for each player
     *  Finally the murderFrame is set
     *  **/
    public void setFrames(){
        JFrame frame = new JFrame();
        Point p;
        Dimension dim;
        boolean ex = false;
        int count = 0;
        int surplus = 18%numOfPlayers;
        if(surplus>0){
            ex=true;
            count++;
            dim = new Dimension(surplus*CARD_WIDTH+20, CARD_LENGTH);
            p = new Point((CARD_WIDTH*18/numOfPlayers) ,0);
            frame = showFrame("Extra Cards", dim, surplusCards.getPane(), p);

        }
        count=0;
        while(count<numOfPlayers){
            if(ex) {
                playerArray.get(count).setExtras(frame, surplusCards);
            }
            dim = new Dimension((CARD_WIDTH*18/numOfPlayers), CARD_LENGTH);
            p = new Point(0,0);
            playerArray.get(count).setDeck(showFrame("Player "+(count+1), dim, playerArray.get(count).getCards().getPane(), p));
            count++;
        }
        dim = new Dimension(CARD_WIDTH, CARD_LENGTH*3+8);
        p = new Point(0,0);
        murderFrame = showFrame("Murder Cards", dim, murderCards.getPane(), p);
    }
    private JFrame showFrame(String title, Dimension dim, JPanel pane, Point location){
        JFrame frame = new JFrame(title);
        frame.setSize(dim);
        frame.getContentPane().add(pane);
        frame.setLocation(location);
        return frame;
    }

    public void setClueSheets(){
        ClueSheet clues;
        int i=0;
        while(i<numOfPlayers){
            clues = new ClueSheet(playersCards[i].getCards(), numOfPlayers);
            clues.addExtraCards(surplusCards);
            clues.repaint();
            Point p = new Point((CARD_WIDTH*18/numOfPlayers),0);
            Dimension dim = new Dimension(clues.getSheet().getWidth(), clues.getSheet().getHeight()+20);
            playerArray.get(i).setNotes(showFrame(playerArray.get(i).getPlayerName() +"'s Notes", dim, clues, p), clues);
            i++;
        }
    }

    public JFrame getMurderFrame() { return murderFrame; }

    public static void disposeMurderFrame(){
        murderFrame.dispose();
    }

}
