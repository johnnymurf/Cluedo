package SoftwareEngineering2;

import javax.swing.*;
import java.util.ArrayList;

class Player {
    private String playerName;
    private String characterName;
    private static int moves; //holds number of moves player rolled on dice
    private boolean rolledDice; // set to true if player rolled the dice
    private boolean inRoom; // set to true when a player enters a room
    private boolean usedSecretPassage; // set to true when a player used a secret passage
    private boolean secretPassage; // set to true if a player enters a room with a secret passage
    private boolean justLeftRoom; // set to true when player has just left a room, stops player being asked to enter room just left from
    private boolean justEntered; // set to true when player just entered a room. error messages depend on wheter player just entered or started turn in a room
    private boolean startInRoom; // set to true if player starts turn in a room
    private boolean inCorridor;
    private boolean accused;
    private boolean  knockedOut;
    private boolean seenCard; //true if any cards have been shown by another player
    private String currentRoom; // current room player is in, set to corridor at start of game and when leaving a room
    private boolean isTurn; //states whether its players turn or not
    private int index; // index correlates to the correct image in playerIcon list, depending on selection
    private Cards cards, allCards, viewed;
    private JFrame deck, extras, notes, seen;
    private static ArrayList<String> stringMurderCards = new ArrayList<>();
    private ArrayList<String> stringCards;
    private ClueSheet clues;

    /*constructor for Player
    * Player objects are created in PlayerManager class*/
    Player() {
        moves = 0;
        knockedOut = false;
        inRoom = false;
        rolledDice = false;
        isTurn = false;
        justLeftRoom = false;
        startInRoom = false;
        usedSecretPassage = false;
        secretPassage = false;
        justEntered = false;
        inCorridor = true;
        accused = false;
        currentRoom = "corridor";
        stringCards = new ArrayList<>();
        cards = new Cards(PlayerManager.getNumOfPlayers());
    }

    public ArrayList<String> getStringCards() {
        return stringCards;
    }

    /*All methods below are basic Getter and Setter methods
            * used to set data or retrieve it*/
    String getPlayerName() {
        return playerName;
    }

    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isKnockedOut() {
        return knockedOut;
    }

    public void setKnockedOut(boolean knocked) {
        this.knockedOut = knocked;
    }

    public static ArrayList<String> getStringMurderCards() {
        return stringMurderCards;
    }

    public void setAccused(boolean accused) {
        this.accused = accused;
    }

    String getCurrentRoom() {
        return currentRoom;
    }

    void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    boolean isStartInRoom() {
        return startInRoom;
    }

    void setStartInRoom(boolean startInRoom) {
        this.startInRoom = startInRoom;
    }
 
    public boolean isInCorridor() {
        return inCorridor;
    }

    public void setInCorridor(boolean inCorridor) {
        this.inCorridor = inCorridor;
    }

    boolean isJustEntered() {
        return justEntered;
    }

    void setJustEntered(boolean justEntered) {
        this.justEntered = justEntered;
    }

    boolean isJustLeftRoom() {
        return justLeftRoom;
    }

    void setJustLeftRoom(boolean justLeftRoom) {
        this.justLeftRoom = justLeftRoom;
    }

    boolean isUsedSecretPassage() {
        return usedSecretPassage;
    }

    void setUsedSecretPassage(boolean usedSecretPassage) {
        this.usedSecretPassage = usedSecretPassage;
    }

    boolean isSecretPassage() {
        return secretPassage;
    }

    void setSecretPassage(boolean secretPassage) {
        this.secretPassage = secretPassage;
    }

    boolean isRolledDice() {
        return rolledDice;
    }

    void setRolledDice(boolean rolledDice) {
        this.rolledDice = rolledDice;
    }

    boolean isInRoom() {
        return inRoom;
    }

    void setInRoom(boolean inRoom) {
        this.inRoom = inRoom;
    }

    String getCharacterName() {
        return characterName;
    }

    void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    int getMoves() {
        return moves;
    }

    void setMoves(int moves) {
        Player.moves = moves;
    }

    void setTurn(boolean turn) {
        isTurn = turn;
    }

    boolean isTurn() {
        return isTurn;
    }

    int getIndex() {
        return index;
    }

    void setIndex(int index) {
        this.index = index;
    }
    /*playerMoved decrements moves of the player when they move after rolling the dice*/
    void playerMoved(){
        moves -= 1;
    }

    void setCards(ArrayList<Card> arr){
        allCards = new Cards(arr.size());
        cards.addCards(arr.toArray());
        allCards.addCards(arr.toArray());
    }
    void setExtras(JFrame frame, Cards ex) {
        allCards.addCards(ex.getCards().toArray());
        this.extras = frame;
    }


    void setNotes(JFrame frame, ClueSheet clue) {
        clues = clue;
        this.notes = frame;
    }

    void setViewed(ArrayList<Card> view) {
        viewed = new Cards(view.size());
        seenCard = true;
        viewed.addCards(view.toArray());
        allCards.addCards(view.toArray());
    }

    public Cards getCards(){ return cards;}

    public void setExtras(JFrame frame) { this.extras = frame; }

    public void setDeck(JFrame frame) { this.deck = frame; }

    public JFrame getDeck(){ return deck;}

    public JFrame getExtras(){ return extras;}

    public JFrame getNotes(){ return notes;}

    public ClueSheet getClues(){ return clues;}

    boolean hasSeenCard(){ return seenCard;}

    Cards getViewed(){ return viewed;}
}
