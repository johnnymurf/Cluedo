package SoftwareEngineering2;


import javax.swing.*;

import java.util.ArrayList;

import static SoftwareEngineering2.Board.board;
import static SoftwareEngineering2.Board.playerIcons;
import static SoftwareEngineering2.InputOutput.textOut;
import static SoftwareEngineering2.Log.hiddenLog;
import static SoftwareEngineering2.Log.logOut;
import static SoftwareEngineering2.PlayerManager.getNumOfPlayers;

public class Question {
    private static Object[] charArray = {"Green", "Mustard", "Peacock", "Plum", "Scarlett", "White"};
    private static final Object[] wChoices = {"Rope", "Dagger", "Wrench", "Lead pipe", "Revolver", "Candlestick"};
    private static final Object[] rChoices = {"ballroom", "conservatory", "kitchen", "dining room", "lounge", "hall", "study", "library", "billiard room"};
    private static int playerAccused = 0;
    private static int hasCard;
    private static int currentPlayerToShowCards = 0;
    private static int iconNotControlled = 0;
    private static int playerIndexWhoAskedQuestion = 0;
    private static boolean playerFound = false;
    private static String character;
    private static String weapon;
    private static String room;
    private static String currentPlayerRoom;
    private static boolean accusationInProgress = false;
    private static String playerHadOneCard;
    private static int questionNumber = 0;
    private static int knockoutCount = 0;
    private static boolean someoneKnockedOut = false;
    private static ArrayList<Integer> array = new ArrayList<>();

    public static void askQuestion() {
        accusationInProgress = true;
        questionNumber++;
        character = (String) JOptionPane.showInputDialog(Board.mainFrame, "Who would you like to accuse?",
                "Characters", JOptionPane.QUESTION_MESSAGE, null, charArray, "Green");
        weapon = (String) JOptionPane.showInputDialog(Board.mainFrame, "What weapon was used?",
                "Weapons", JOptionPane.QUESTION_MESSAGE, null, wChoices, "Rope");

        currentPlayerRoom = getPlayer().getCurrentRoom();

        logOut.append("Question: " + questionNumber + "\n" +
                "Player " + getPlayer().getCharacterName() + " asked\n" +
                "if " + character + "\n" +
                "did it in the " + currentPlayerRoom + "\n" +
                "with the " + weapon + "\n");
 
        hiddenLog.append("You asked if\n"
                + character + " did it\n"
                + "with the " +  weapon + "\n"
                + "in the " + currentPlayerRoom + "\n\n" +
                "The following players answered\n\n");

        int accused = findPlayerWithCharacterAccused(character);
        if(accused >= 0){
            moveAccusedPlayerToRoom(accused, currentPlayerRoom);
        }
        else{
            getPlayerIconNotControlled(character);
            moveAccusedPlayerToRoom(-1, currentPlayerRoom);
        }

        Board.moveWeapon(weapon, currentPlayerRoom);
        playerIndexWhoAskedQuestion = PlayerManager.checkIsTurn();
        hasCard = 0;
        if(PlayerManager.checkIsTurn() == getNumOfPlayers() - 1){
            currentPlayerToShowCards = 0;
        }
        else{
            currentPlayerToShowCards = PlayerManager.checkIsTurn() + 1;
        }
        textOut.setText(null);
        showCardOrNextPlayer(character, weapon, currentPlayerRoom, currentPlayerToShowCards);
    }

    public static void makeFinalAccusation(){
        playerIndexWhoAskedQuestion = PlayerManager.checkIsTurn();
        array.clear();
        character = (String) JOptionPane.showInputDialog(Board.mainFrame, "Who would you like to accuse?",
                "Characters", JOptionPane.QUESTION_MESSAGE, null, charArray, "Green");
        weapon = (String) JOptionPane.showInputDialog(Board.mainFrame, "What weapon was used?",
                "Weapons", JOptionPane.QUESTION_MESSAGE, null, wChoices, "Rope");
        room = (String) JOptionPane.showInputDialog(Board.mainFrame, "What room did the murder take place?",
                "Weapons", JOptionPane.QUESTION_MESSAGE, null, rChoices, "ballroom");

        if(getMurderCards().get(0).equalsIgnoreCase(room) && getMurderCards().get(1).equalsIgnoreCase(weapon)  && getMurderCards().get(2).equalsIgnoreCase(character)){
            JOptionPane.showMessageDialog(Board.mainFrame, "You have found the murderer, you won the game!");
            InputOutput.quitGame();
        }
        else{
            JOptionPane.showMessageDialog(Board.mainFrame, "You failed to find the murderer, you are out of the game\n");
            PlayerManager.getPlayerArrayList().get(getPlayerIndexWhoAskedQuestion()).setKnockedOut(true);
            logOut.append("Player Controlling " + PlayerManager.getPlayerArrayList().get(getPlayerIndexWhoAskedQuestion()).getCharacterName() + " was knocked out!\n\n");

            knockoutCount++;
            System.out.println("Knocked out : " + PlayerManager.getPlayerArrayList().get(playerIndexWhoAskedQuestion).getCharacterName() + "  " + PlayerManager.getPlayerArrayList().get(playerIndexWhoAskedQuestion).isKnockedOut());
            System.out.println(playerIndexWhoAskedQuestion);
            System.out.println(getPlayerIndexWhoAskedQuestion());

            for(int i = 0; i < PlayerManager.getNumOfPlayers(); i++){
                if(!PlayerManager.getPlayerArrayList().get(i).isKnockedOut()){
                    System.out.println(PlayerManager.getPlayerArrayList().get(i).isKnockedOut());
                    getArray().add(i);
                }
            }
            moveKnockedOutPlayerToStart(getPlayer().getIndex());
            System.out.println("array : " + getArray().size());
        }

        if(knockoutCount == getNumOfPlayers() - 1){
            String winner = getCharacterNameOfWinner();
            JOptionPane.showMessageDialog(Board.mainFrame, "Their is only one player left, player controlling " + winner + " won the game!");
            InputOutput.quitGame();
        }
    }

    private static void showCardOrNextPlayer(String characterAccused, String weapon, String room, int player){
        hasCard = 0;
        checkIfPlayerHasCards(characterAccused, weapon, room, player);

        int yes = handScreenOver();

        if(yes == JOptionPane.YES_OPTION){

            int haveCardOrDone = haveCardOrDone();

            if(haveCardOrDone == JOptionPane.YES_OPTION){
                if (hasCard > 0){
                    playerHasCard();
                }
                else if(hasCard == 0) {
                    textOut.append("You did not have a card!\n");
                    if(currentPlayerToShowCards == playerIndexWhoAskedQuestion - 1){
                        noPlayersHadCard();
                    }
                    else if(currentPlayerToShowCards == getNumOfPlayers() - 1){
                        if(playerIndexWhoAskedQuestion == 0){
                            noPlayersHadCard();
                        }
                        else{
                            askNextPlayer(0);
                            showCardOrNextPlayer(character, weapon, room, currentPlayerToShowCards);
                        }
                    }
                    else{
                        askNextPlayer(currentPlayerToShowCards + 1);
                        showCardOrNextPlayer(character, weapon, room, currentPlayerToShowCards);
                    }
                }
            }
            else{
                if (hasCard > 0){
                    playerHasCard();
                }
                else if(hasCard == 0) {
                    textOut.append("You did not have a card!\n");
                    if(currentPlayerToShowCards == playerIndexWhoAskedQuestion - 1){
                        noPlayersHadCard();
                    }
                    else if(currentPlayerToShowCards == getNumOfPlayers() - 1){
                        if(playerIndexWhoAskedQuestion == 0){
                            noPlayersHadCard();
                        }
                        else{
                            askNextPlayer(0);
                            showCardOrNextPlayer(character, weapon, room, currentPlayerToShowCards);
                        }
                    }
                    else{
                        askNextPlayer(currentPlayerToShowCards + 1);
                        showCardOrNextPlayer(character, weapon, room, currentPlayerToShowCards);
                    }
                }
            }
        }
    }

    public static void showCard(String cardShown){
        if(cardShown.equalsIgnoreCase(weapon)){

            hiddenLog.append("Player " + getCharacterName() + " had one card\n" +
                            "and showed you the " + weapon + " card\n" +
                            "type \"done\" to continue\n");

            logOut.append("Player " + PlayerManager.getPlayerArrayList().get(currentPlayerToShowCards).getCharacterName() +
                            " showed a relevant card\n\n");

            cardHasBeenShownSetLogAndAttributes(weapon);
        }
        else if(cardShown.equalsIgnoreCase(character)){

            hiddenLog.append("Player " + getCharacterName() + " had one card\n" +
                    "and showed you the " + character + " card\n" +
                    "type \"done\" to continue\n");

            logOut.append("Player " + PlayerManager.getPlayerArrayList().get(currentPlayerToShowCards).getCharacterName() +
                    " showed a relevant card\n\n");

            cardHasBeenShownSetLogAndAttributes(character);
        }
        else if(cardShown.equalsIgnoreCase(currentPlayerRoom)){

            hiddenLog.append("Player " + getCharacterName() + " had one card\n" +
                    "and showed you the " + currentPlayerRoom + " card\n" +
                    "type \"done\" to continue\n");

            logOut.append("Player " + PlayerManager.getPlayerArrayList().get(currentPlayerToShowCards).getCharacterName() +
                    " showed a relevant card\n\n");

            cardHasBeenShownSetLogAndAttributes(currentPlayerRoom);
        }
        else{
            textOut.append("\nYou selected the wrong card, please choose the correct one by typing show card\n\n");
        }
    }

    public static int handScreenOver(){
        Object[] options = {"Yes"};

       return JOptionPane.showOptionDialog(Board.mainFrame,
               "Has the screen been handed over?",
               "Hand Screen Over",
               JOptionPane.YES_NO_CANCEL_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               null,
               options,
               options[0]);
    }

    private static int haveCardOrDone(){
        Object[] a = {"Show Card", "Done"};
        return JOptionPane.showOptionDialog(Board.mainFrame,
                "Player " + PlayerManager.getPlayerArrayList().get(getPlayerIndexWhoAskedQuestion()).getCharacterName() + "  asked if\n"
                        + character + " did it\n"
                        + "with the " +  weapon + "\n"
                        + "in the " + currentPlayerRoom + "\n" +
                        "Do you have any relevant cards?",
                "Question?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                a,
                a[0]);
    }

    private static void playerHasCard(){
        textOut.append("You have a card which is relevant to the current accusation\n\n");

        if(hasCard == 1){
            textOut.append("You have one relevant card, type show card to show it to the player\n");
        }
        else{
            textOut.append("You had more than one relevant cards\n" +
                    "type show card to pick which one you would like to show\n");
        }
    }

    private static void noPlayersHadCard(){
        PlayerManager.getPlayerArrayList().get(currentPlayerToShowCards).setTurn(false);
        hiddenLog.append("Player " + getCharacterName() + " did not have any cards\n\n");
        PlayerManager.getPlayerArrayList().get(playerIndexWhoAskedQuestion).setTurn(true);
        hiddenLog.append("No Players had the accused cards\n\n");
        textOut.setText(hiddenLog.getText());
        hiddenLog.setText(null);
        setAccusationInProgress(false);
    }

    private static void askNextPlayer(int nextPlayer){
        PlayerManager.getPlayerArrayList().get(currentPlayerToShowCards).setTurn(false);
        hiddenLog.append("Player " + getCharacterName() + " did not have any cards\n\n");
        currentPlayerToShowCards = nextPlayer;
        PlayerManager.getPlayerArrayList().get(currentPlayerToShowCards).setTurn(true);
    }

    private static void cardHasBeenShownSetLogAndAttributes(String cardShown){
        InputOutput.showPlayerCard(cardShown);
        setPlayerTurnTrue();
        handScreenOver();
        textOut.setText(hiddenLog.getText());
        hiddenLog.setText(null);
        setAccusationInProgress(false);
    }

    private static void setPlayerTurnTrue(){
        PlayerManager.getPlayerArrayList().get(currentPlayerToShowCards).setTurn(false);
        PlayerManager.getPlayerArrayList().get(playerIndexWhoAskedQuestion).setTurn(true);
    }

    private static void moveKnockedOutPlayerToStart(int playerKnockedOut) {
        switch (playerKnockedOut) {
            case 0:
                board.add(playerIcons.get(0)).setBounds(365, 25, 21, 21);
                break;
            case 1:
                board.add(playerIcons.get(1)).setBounds(43, 416, 21, 21);
                break;
            case 2:
                board.add(playerIcons.get(2)).setBounds(572, 163, 21, 21);
                break;
            case 3:
                board.add(playerIcons.get(3)).setBounds(572, 462, 21, 21);
                break;
            case 4:
                board.add(playerIcons.get(4)).setBounds(204, 577, 21, 21);
                break;
            case 5:
                board.add(playerIcons.get(5)).setBounds(250, 25, 21, 21);
                break;
            default:
                textOut.append("Something went wrong, try again\n");
        }
    }

    private static void getPlayerIconNotControlled(String character){
        switch(character.toLowerCase()){
            case "green":
                iconNotControlled = 0;
                break;
            case "mustard":
                iconNotControlled = 1;
                break;
            case "peacock":
                iconNotControlled = 2;
                break;
            case "plum":
                iconNotControlled = 3;
                break;
            case "scarlett":
                iconNotControlled = 4;
                break;
            case "white":
                iconNotControlled = 5;
                break;

        }
    }

    public static ArrayList<Integer> getArray() {
        return array;
    }

    private static ArrayList<String> getMurderCards(){
        return Player.getStringMurderCards();
    }

    private static String getCharacterName(){
        return PlayerManager.getPlayerArrayList().get(getCurrentPlayerToShowCards()).getCharacterName();
    }

    public static int getCurrentPlayerToShowCards() {
        return currentPlayerToShowCards;
    }

    public static int getPlayerIndexWhoAskedQuestion() {
        return playerIndexWhoAskedQuestion;
    }

    public static int getHasCard() {
        return hasCard;
    }

    public static String getPlayerHadOneCard() {
        return playerHadOneCard;
    }

    public static boolean isSomeoneKnockedOut() {
        return someoneKnockedOut;
    }

    private static void checkIfPlayerHasCards(String character, String weapon, String room, int player){

        for(int j = 0; j < PlayerManager.getPlayerArrayList().get(player).getStringCards().size(); j++){
            if(getStringCards(player, j).equalsIgnoreCase(character) || getStringCards(player, j).equalsIgnoreCase(weapon) || getStringCards(player, j).equalsIgnoreCase(room)){
                System.out.println("find cards\n");
                playerHadOneCard = getStringCards(player, j);
                hasCard += 1;
            }
        }
    }

    private static String getStringCards(int i, int j){
        return PlayerManager.getPlayerArrayList().get(i).getStringCards().get(j);
    }

    private static int findPlayerWithCharacterAccused(String characterName) {
        int i = 0;
        while (i < PlayerManager.getPlayerArrayList().size()) {
            System.out.println(characterName);
            System.out.println(PlayerManager.getPlayerArrayList().get(i).getCharacterName());
            if (characterName.equals(PlayerManager.getPlayerArrayList().get(i).getCharacterName())) {
                playerAccused = i;
                playerFound = true;
            }
            else if(!playerFound){
                playerAccused = -1;
            }
            i++;
        }
        System.out.println("PlayerAccused: " + playerAccused);
        playerFound = false;
        return playerAccused;
    }

    private static void moveAccusedPlayerToRoom(int accusedPlayer, String currentPlayerRoom) {
        switch (currentPlayerRoom.toLowerCase()) {
            case "dining room":
                if(accusedPlayer == -1) {
                    moveToDiningRoom(iconNotControlled, accusedPlayer);
                }
                else{
                    moveToDiningRoom(getCharacterIndex(accusedPlayer), accusedPlayer);
                }
                break;
            case "lounge":
                if(accusedPlayer == -1) {
                    moveToLounge(iconNotControlled, accusedPlayer);
                }
                else{
                    moveToLounge(getCharacterIndex(accusedPlayer), accusedPlayer);
                }
                break;
            case "hall":
                if(accusedPlayer == -1) {
                    moveToHall(iconNotControlled, accusedPlayer);
                }
                else{
                    moveToHall(getCharacterIndex(accusedPlayer), accusedPlayer);
                }
                break;
            case "study":
                if(accusedPlayer == -1) {
                    moveToStudy(iconNotControlled, accusedPlayer);
                }
                else{
                    moveToStudy(getCharacterIndex(accusedPlayer), accusedPlayer);
                }
                break;
            case "library":
                if(accusedPlayer == -1) {
                    moveToLibrary(iconNotControlled, accusedPlayer);
                }
                else{
                    moveToLibrary(getCharacterIndex(accusedPlayer), accusedPlayer);
                }
                break;
            case "billiard":
                if(accusedPlayer == -1) {
                    moveToBilliard(iconNotControlled, accusedPlayer);
                }
                else{
                    moveToLibrary(getCharacterIndex(accusedPlayer), accusedPlayer);
                }
                break;
            case "conservatory":
                if(accusedPlayer == -1) {
                    moveToConservatory(iconNotControlled, accusedPlayer);
                }
                else{
                    moveToConservatory(getCharacterIndex(accusedPlayer), accusedPlayer);
                }
                break;
            case "ballroom":
                if(accusedPlayer == -1) {
                    moveToBallroom(iconNotControlled, accusedPlayer);
                }
                else{
                    moveToBallroom(getCharacterIndex(accusedPlayer), accusedPlayer);
                }
                break;
            case "kitchen":
                if(accusedPlayer == -1) {
                    moveToKitchen(iconNotControlled, accusedPlayer);
                }
                else{
                    moveToKitchen(getCharacterIndex(accusedPlayer), accusedPlayer);
                }
                break;
        }
    }

    private static void moveToDiningRoom(int character, int player) {
        if (character == 0) {
            if(player == -1){
                board.add(Board.playerIcons.get(character)).setBounds(89, 324, 21, 21);
            }
            else{
                board.add(Board.playerIcons.get(character)).setBounds(89, 324, 21, 21);
                setAccusedToDiningRoom(player);
            }

        } else if (character == 1) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(112, 324, 21, 21);
            }
            else{
                board.add(Board.playerIcons.get(character)).setBounds(112, 324, 21, 21);
                setAccusedToDiningRoom(player);
            }

        } else if (character == 2) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(135, 324, 21, 21);
            }
            else{
                board.add(Board.playerIcons.get(character)).setBounds(135, 324, 21, 21);
                setAccusedToDiningRoom(player);
            }

        } else if (character == 3) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(135, 301, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(135, 301, 21, 21);
                setAccusedToDiningRoom(player);
            }
        } else if (character == 4) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(112, 301, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(112, 301, 21, 21);
                setAccusedToDiningRoom(player);
            }
        } else if (character == 5) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(89, 301, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(89, 301, 21, 21);
                setAccusedToDiningRoom(player);
            }
        }
    }

    private static void moveToLounge(int character, int player) {
        if (character == 0) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(89, 531, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(89, 531, 21, 21);
                setAccusedToLounge(player);
            }
        } else if (character == 1) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(112, 531, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(112, 531, 21, 21);
                setAccusedToLounge(player);
            }
        } else if (character == 2) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(135, 531, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(135, 531, 21, 21);
                setAccusedToLounge(player);
            }
        } else if (character == 3) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(135, 508, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(135, 508, 21, 21);
                setAccusedToLounge(player);
            }
        } else if (character == 4) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(112, 508, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(112, 508, 21, 21);
                setAccusedToLounge(player);
            }
        } else if (character == 5) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(89, 508, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(89, 508, 21, 21);
                setAccusedToLounge(player);
            }
        }
    }

    private static void moveToHall(int character, int player) {
        if (character == 0) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(296, 485, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(296, 485, 21, 21);
                setAccusedToHall(player);
            }
        } else if (character == 1) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(319, 485, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(319, 485, 21, 21);
                setAccusedToHall(player);
            }
        } else if (character == 2) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(319, 508, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(319, 508, 21, 21);
                setAccusedToHall(player);
            }
        } else if (character == 3) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(296, 531, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(296, 531, 21, 21);
                setAccusedToHall(player);
            }
        } else if (character == 4) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(319, 531, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(319, 531, 21, 21);
                setAccusedToHall(player);
            }
        } else if (character == 5) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(296, 508, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(296, 508, 21, 21);
                setAccusedToHall(player);
            }
        }
    }

    private static void moveToStudy(int character, int player) {
        if (character == 0) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(480, 554, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(480, 554, 21, 21);
                setAccusedToStudy(player);
            }
        } else if (character == 1) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(503, 554, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(503, 554, 21, 21);
                setAccusedToStudy(player);
            }
        } else if (character == 2) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(526, 554, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(526, 554, 21, 21);
                setAccusedToStudy(player);
            }
        } else if (character == 3) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(526, 531, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(526, 531, 21, 21);
                setAccusedToStudy(player);
            }
        } else if (character == 4) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(480, 531, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(480, 531, 21, 21);
                setAccusedToStudy(player);
            }
        } else if (character == 5) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(503, 531, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(503, 531, 21, 21);
                setAccusedToStudy(player);
            }
        }
    }

    private static void moveToLibrary(int character, int player) {
        if (character == 0) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(480, 416, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(480, 416, 21, 21);
                setAccusedToLibrary(player);
            }
        } else if (character == 1) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(503, 416, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(503, 416, 21, 21);
                setAccusedToLibrary(player);
            }
        } else if (character == 2) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(526, 416, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(526, 416, 21, 21);
                setAccusedToLibrary(player);
            }
        } else if (character == 3) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(526, 393, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(526, 393, 21, 21);
                setAccusedToLibrary(player);
            }
        } else if (character == 4) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(480, 393, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(480, 393, 21, 21);
                setAccusedToLibrary(player);
            }
        } else if (character == 5) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(503, 393, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(503, 393, 21, 21);
                setAccusedToLibrary(player);
            }
        }
    }

    private static void moveToBilliard(int character, int player) {
        if (character == 0) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(503, 278, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(503, 278, 21, 21);
                setAccusedToBilliard(player);
            }
        } else if (character == 1) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(526, 278, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(526, 278, 21, 21);
                setAccusedToBilliard(player);
            }
        } else if (character == 2) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(549, 278, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(549, 278, 21, 21);
                setAccusedToBilliard(player);
            }
        } else if (character == 3) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(549, 255, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(549, 255, 21, 21);
                setAccusedToBilliard(player);
            }
        } else if (character == 4) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(503, 255, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(503, 255, 21, 21);
                setAccusedToBilliard(player);
            }
        } else if (character == 5) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(526, 255, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(526, 255, 21, 21);
                setAccusedToBilliard(player);
            }
        }
    }

    private static void moveToConservatory(int character, int player) {
        if (character == 0) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(503, 94, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(503, 94, 21, 21);
                setAccusedToConservatory(player);
            }
        } else if (character == 1) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(526, 94, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(526, 94, 21, 21);
                setAccusedToConservatory(player);
            }
        } else if (character == 2) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(549, 94, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(549, 94, 21, 21);
                setAccusedToConservatory(player);
            }
        } else if (character == 3) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(549, 71, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(549, 71, 21, 21);
                setAccusedToConservatory(player);
            }
        } else if (character == 4) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(526, 71, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(526, 71, 21, 21);
                setAccusedToConservatory(player);
            }
        } else if (character == 5) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(503, 71, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(503, 71, 21, 21);
                setAccusedToConservatory(player);
            }
        }
    }

    private static void moveToBallroom(int character, int player) {
        if (character == 0) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(273, 117, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(273, 117, 21, 21);
                setAccusedToBallRoom(player);
            }
        } else if (character == 1) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(296, 117, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(296, 117, 21, 21);
                setAccusedToBallRoom(player);
            }
        } else if (character == 2) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(319, 117, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(319, 117, 21, 21);
                setAccusedToBallRoom(player);
            }
        } else if (character == 3) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(342, 117, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(342, 117, 21, 21);
                setAccusedToBallRoom(player);
            }
        } else if (character == 4) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(319, 94, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(319, 94, 21, 21);
                setAccusedToBallRoom(player);
            }
        } else if (character == 5) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(296, 94, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(296, 94, 21, 21);
                setAccusedToBallRoom(player);
            }
        }
    }

    private static void moveToKitchen(int character, int player) {
        if (character == 0) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(66, 117, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(66, 117, 21, 21);
                setAccusedToKitchen(player);
            }
        } else if (character == 1) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(89, 117, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(89, 117, 21, 21);
                setAccusedToKitchen(player);
            }
        } else if (character == 2) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(112, 117, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(112, 117, 21, 21);
                setAccusedToKitchen(player);
            }
        } else if (character == 3) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(112, 94, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(112, 94, 21, 21);
                setAccusedToKitchen(player);
            }
        } else if (character == 4) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(89, 94, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(89, 94, 21, 21);
                setAccusedToKitchen(player);
            }
        } else if (character == 5) {
            if(player == -1) {
                board.add(Board.playerIcons.get(character)).setBounds(66, 94, 21, 21);
            }
            else {
                board.add(Board.playerIcons.get(character)).setBounds(66, 94, 21, 21);
                setAccusedToKitchen(player);
            }
        }
    }

    private static String getCharacterNameOfWinner(){
        int i = 0;
        boolean found = false;
        String name = "noName";
        do{
            if(!PlayerManager.getPlayerArrayList().get(i).isKnockedOut()){
                name = PlayerManager.getPlayerArrayList().get(i).getCharacterName();
                found = true;
            }
            i++;
        }while(!found);
        return name;
    }

    private static Player getPlayer() {
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn());
    }

    public static boolean isAccusationInProgress() {
        return accusationInProgress;
    }

    public static void setAccusationInProgress(boolean accusationInProgress) {
        Question.accusationInProgress = accusationInProgress;
    }

    private static int getCharacterIndex(int player) {
        return PlayerManager.getPlayerArrayList().get(player).getIndex();
    }

    private static void setAccusedAttributes(int player){
        PlayerManager.getPlayerArrayList().get(player).setInRoom(true);
        PlayerManager.getPlayerArrayList().get(player).setAccused(true);
        PlayerManager.getPlayerArrayList().get(player).setInCorridor(false);
        PlayerManager.getPlayerArrayList().get(player).setMoves(0);
    }
    /*Sets player attributes on entry to a room with a secret passage
    * when secret passage is set to true player wil be notified at the start of their next turn they have the option to use a passage
    * @Param player is the index of current player*/
    private static void setAccusedAttributesSecret(int player){
        PlayerManager.getPlayerArrayList().get(player).setInRoom(true);
        PlayerManager.getPlayerArrayList().get(player).setAccused(true);
        PlayerManager.getPlayerArrayList().get(player).setSecretPassage(true);
        PlayerManager.getPlayerArrayList().get(player).setInCorridor(false);
        PlayerManager.getPlayerArrayList().get(player).setMoves(0);
    }
    /*called when a player enters the dining room
    * calls setAccusedAttributes
    * sets current player room attribute to dining room*/
    public static void setAccusedToDiningRoom(int player){
        setAccusedAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("dining room");
    }

    public static void setAccusedToLounge(int player){
        setAccusedAttributesSecret(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("lounge");
    }

    public static void setAccusedToHall(int player){
        setAccusedAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("hall");
    }

    public static void setAccusedToStudy(int player){
        setAccusedAttributesSecret(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("study");
    }

    public static void setAccusedToLibrary(int player){
        setAccusedAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("library");
    }

    public static void setAccusedToBilliard(int player){
        setAccusedAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("billiard");
    }

    public static void setAccusedToConservatory(int player){
        setAccusedAttributesSecret(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("conservatory");
    }

    public static void setAccusedToBallRoom(int player){
        setAccusedAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("ballroom");
    }

    public static void setAccusedToKitchen(int player){
        setAccusedAttributesSecret(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("kitchen");
    }
}
