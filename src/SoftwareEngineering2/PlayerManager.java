package SoftwareEngineering2;

import javax.swing.*;
import java.util.*;
import java.util.List;

import static SoftwareEngineering2.Board.board;
import static SoftwareEngineering2.Board.playerIcons;
/*Class handles player setup and players throughout the game
 * Is a sub class of InputOutput*/
class PlayerManager extends InputOutput {
    private Object[] playerCount = {2, 3, 4, 5, 6};
    private static int numOfPlayers;
    private static int currentPlayer = 0;
    private static ArrayList<Player> playerArrayList;
    private static Object[] charArray = {"Green", "Mustard", "Peacock", "Plum", "Scarlett", "White"};
    private static List<String> characters = new ArrayList<>(Arrays.asList("Green", "Mustard", "Peacock", "Plum", "Scarlett", "White"));
    private static ArrayList<String> occupiedTile = new ArrayList<>();
    private static CardSetup cardSetup;
    private static int player2 = 0;
    private static int previous = 0;


    PlayerManager() {
        numOfPlayers = 0;
        playerArrayList = new ArrayList<>();
    }

    /*gives user choice of how many players from 2 to 6
     * uses an inputDialog to get selection
     * prints number of players selected*/
    void setNumOfPlayers() {
        numOfPlayers = (int) JOptionPane.showInputDialog(Board.mainFrame, "How Many Players?", "Number of PLayers", JOptionPane.QUESTION_MESSAGE, null, playerCount, "2");

        for (int i = 0; i < numOfPlayers; i++) {
            playerArrayList.add(new Player());
        }
        printNumberOfPlayers(numOfPlayers);
    }

    /*Uses inputDialogs to take each players name and character choice*/
    void fillPlayerDetails(int i) {

        String name = JOptionPane.showInputDialog(Board.mainFrame, "Please enter name");
        playerArrayList.get(i).setPlayerName(name);

        String charSelected;
        charSelected = (String) JOptionPane.showInputDialog(Board.mainFrame, "Choose a character?", "Character Selection", JOptionPane.QUESTION_MESSAGE, null, characters.toArray(), characters.get(0));

        characters.remove(charSelected);
        playerArrayList.get(i).setCharacterName(charSelected);
        setPlayerIndex(charSelected, i);
    }


    /*@Override superclass method printPlayerDetails
     * just calls superclass method*/
    @Override
    void printPlayerDetails() {
        super.printPlayerDetails();
    }

    /*@Return number of players for current game*/
    static int getNumOfPlayers() {
        return numOfPlayers;
    }

    /*@Return arrayList with tile coordinates for occupied tiles
     * coordinates added at the end of each players turn
     * coordinates removed when the player rolls the dice
     * stops players landing on the same tile
     * see FetchMoveAndCheck.java*/
    static ArrayList<String> getOccupiedTile() {
        return occupiedTile;
    }

    /*@Return playerArrayList holds all players for current game*/
    static ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    /*players and playerIcons are held in separate arrayLists
     * This method assigns the index value of the players selected character corresponding to the icons playerIcon index*/
    private void setPlayerIndex(String character, int i) {
        switch (character.toLowerCase()) {
            case "green":
                playerArrayList.get(i).setIndex(0);
                board.add(playerIcons.get(0)).setBounds(365, 25, 21, 21);
                charArray[0] = null;
                break;
            case "mustard":
                playerArrayList.get(i).setIndex(1);
                board.add(playerIcons.get(1)).setBounds(43, 416, 21, 21);
                charArray[1] = null;
                break;
            case "peacock":
                playerArrayList.get(i).setIndex(2);
                board.add(playerIcons.get(2)).setBounds(572, 163, 21, 21);
                charArray[2] = null;
                break;
            case "plum":
                playerArrayList.get(i).setIndex(3);
                board.add(playerIcons.get(3)).setBounds(572, 462, 21, 21);
                charArray[3] = null;
                break;
            case "scarlett":
                playerArrayList.get(i).setIndex(4);
                board.add(playerIcons.get(4)).setBounds(204, 577, 21, 21);
                charArray[4] = null;
                break;
            case "white":
                playerArrayList.get(i).setIndex(5);
                board.add(playerIcons.get(5)).setBounds(250, 25, 21, 21);
                charArray[5] = null;
                break;
            default:
                textOut.append("Something went wrong, try again\n");
        }
    }

    /*called in InputOutput.java finishPlayerTurn
     * handles the transfer of turns between players
     * when called current player isTurn set to false
     * next player isTurn set to true, current player increments 1
     * if current player is the last player current player is set to 0
     * and player at index 0 isTurn set true*/
    public static void nextPlayerTurn(int player) {
//        playerArrayList.get(player).setTurn(false);
        if (player > getNumOfPlayers() - 1) {
            player = 0;
        }
        player2 = player;
        switch (player2) {
            case 0:
                System.out.println("case 0\n");
                if (!playerArrayList.get(0).isKnockedOut()) {
                    System.out.println("if true\n");
                    playerArrayList.get(getNumOfPlayers() - 1).setTurn(false);
                    playerArrayList.get(0).setTurn(true);
                } else {
                    System.out.println("if false\n");
                    playerArrayList.get(0).setTurn(false);
                    nextPlayerTurn(1);
                }
                break;
            case 1:
                System.out.println("case 1\n");
                if (!playerArrayList.get(1).isKnockedOut()) {
                    System.out.println("if true\n");
                    playerArrayList.get(0).setTurn(false);
                    playerArrayList.get(1).setTurn(true);
                } else {
                    System.out.println("if false\n");
                    playerArrayList.get(1).setTurn(false);
                    nextPlayerTurn(2);
                }
                break;
            case 2:
                System.out.println("case 2\n");
                if (!playerArrayList.get(2).isKnockedOut()) {
                    System.out.println("if true\n");
                    playerArrayList.get(1).setTurn(false);
                    playerArrayList.get(2).setTurn(true);
                } else {
                    System.out.println("if false\n");
                    playerArrayList.get(2).setTurn(false);
                    nextPlayerTurn(3);
                }
                break;
            case 3:
                System.out.println("case 3\n");
                if (!playerArrayList.get(3).isKnockedOut()) {
                    System.out.println("if true\n");
                    playerArrayList.get(2).setTurn(false);
                    playerArrayList.get(3).setTurn(true);
                } else {
                    System.out.println("if false\n");
                    playerArrayList.get(3).setTurn(false);
                    nextPlayerTurn(4);
                }
                break;
            case 4:
                System.out.println("case 4\n");
                if (!playerArrayList.get(4).isKnockedOut()) {
                    System.out.println("if true\n");
                    playerArrayList.get(3).setTurn(false);
                    playerArrayList.get(4).setTurn(true);
                } else {
                    System.out.println("if false\n");
                    playerArrayList.get(4).setTurn(false);
                    nextPlayerTurn(5);
                }
                break;
            case 5:
                System.out.println("case 5\n");
                if (!playerArrayList.get(5).isKnockedOut()) {
                    System.out.println("if true\n");
                    playerArrayList.get(4).setTurn(false);
                    playerArrayList.get(5).setTurn(true);
                } else {
                    System.out.println("if false\n");
                    playerArrayList.get(5).setTurn(false);
                    nextPlayerTurn(0);
                }
                break;
            default:
                System.out.println("Something went wrong\n");
                break;
        }
    }

    /*@Return the index value of player who has isTurn set to true*/
    static int checkIsTurn() {
        int i = 0;
        int j = 0;
        while (i<numOfPlayers) {
            if(playerArrayList.get(i).isTurn()){
                j = i;
            }
            i++;
        }
        return j;
    }

    /*@Return character index assigned to player*/
    static int getCharIndex() {
        return playerArrayList.get(checkIsTurn()).getIndex();
    }

    /*Method stores the coordinates of the last tile a player lands on occupiedTile arrayList
     * Called in the "done" case of actionPerformed switch statement in InputOutput.java*/
    static void storeLastMove() {
        if (getPlayerMoves() == 0 && !getIsInRoom()) {
            occupiedTile.add(getXY());
        }
    }

    /*Removes current players last stored coordinates from occupiedTile arrayList when the player rolls the dice
     * Called in printPlayerDiceRoll() in InputOutput.java*/
    static void removeLastMove() {
        if (!getIsInRoom() && !occupiedTile.isEmpty()) {
            occupiedTile.remove(getXY());
        }
    }

    /*gets current players character icon x and y coordinates
     * @Return x and y coordinates as a String
     * called in storeLastMove and removeLastMove*/
    private static String getXY() {
        int index = playerArrayList.get(checkIsTurn()).getIndex();

        int x = Board.playerIcons.get(index).getX();
        int y = Board.playerIcons.get(index).getY();
        return x + " " + y;
    }

    /*@Return current players number of moves left*/
    private static int getPlayerMoves() {
        return playerArrayList.get(checkIsTurn()).getMoves();
    }

    /*@Return boolean isInRoom true if current player is in a room*/
    private static boolean getIsInRoom() {
        return playerArrayList.get(checkIsTurn()).isInRoom();
    }
    /* Creates new CardSetup class which selects, shuffles and deals cards
     *  then allocates them to players. Each player's Clue Sheet is the marked
     *  for each card with the frames for the cards and Clue Sheet assigned
     *  to each player */
    public static void assignCards() {
        cardSetup = new CardSetup(numOfPlayers);
    }
    public static CardSetup getCardSetup() { return cardSetup; }

    public static void setStringCardProperNames(){
        for(int i = 0; i < PlayerManager.getPlayerArrayList().size(); i++){
            for(int j = 0; j < PlayerManager.getPlayerArrayList().get(i).getStringCards().size(); j++){
                checkStringValue(i, j);
            }
        }
    }

    public static void setStringMurderCards(){
        for(int i = 0; i < Player.getStringMurderCards().size(); i++){
            checkStringMurderValue(i);
        }
    }

    //    ImagePaths and names Lists will have corresponding index used in the following methods
    private static List<String> imagePaths = new ArrayList<>(Arrays.asList("/ballroom.png","/billiard room.png",
            "/conservatory.png","/dining room.png", "/hall.png","/kitchen.png","/library.png","/lounge.png","/study.png",
            "/candlestick.png","/dagger.png","/lead pipe.png","/revolver.png","/rope.png","/wrench.png","/Green.png","/Mustard.png",
            "/Peacock.png","/Plum.png","/Scarlett.png","/White.png"));
    private static List<String> names = new ArrayList<>(Arrays.asList("ballroom","billiard room","conservatory",
            "dining room", "hall","kitchen","library","lounge","study","Candlestick","Dagger","Lead pipe","Revolver",
            "Rope","Wrench","Green","Mustard","Peacock","Plum","Scarlett","White"));

    private static void checkStringValue(int i, int j){
        String tempCardName = getPlayerArrayList().get(i).getStringCards().get(j);
        if(imagePaths.contains(tempCardName)){
            int index = imagePaths.indexOf(tempCardName);
            getPlayerArrayList().get(i).getStringCards().set(j, names.get(index));
        }
    }
    public static void checkStringMurderValue(int i){
        String tempCardName = Player.getStringMurderCards().get(i);

        if(imagePaths.contains(tempCardName)){
            int index = imagePaths.indexOf(tempCardName);
            Player.getStringMurderCards().set(i,names.get(index));
        }
    }
}
