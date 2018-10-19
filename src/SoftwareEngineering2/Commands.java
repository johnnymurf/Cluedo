package SoftwareEngineering2;

/*
* Handles the commands that players input
* Methods are called from InputOutput actionPerformed
* */

import javax.swing.*;

public class Commands extends PlayerManager {
//     w, a, s ,d keys used for movement, see MovementInput.java for movement implementation
    protected static void wasd(String move){
        if(move.equals("w")){
            if (!isAccusationInProgress()) {
                if (rolledHigh()) {
                    movementInput.movementY(checkMove, move, "up", -23);
                } else
                    textOut.append("Type high roll to roll the dice for first turn. \nPlay will not continue otherwise\n\n");
                appendText();
            }
            else{
                textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
                appendText();
            }
        }
        if(move.equals("a")){
            if (!isAccusationInProgress()) {
                if (rolledHigh()) {
                    movementInput.movementX(checkMove, move, "left", -23);
                } else
                    textOut.append("Type high roll to roll the dice for first turn. \nPlay will not continue otherwise\n\n");
                appendText();
            }
            else{
                textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
                appendText();
            }
        }
        if(move.equals("s")){
            if (!isAccusationInProgress()) {
                if (rolledHigh()) {
                    movementInput.movementY(checkMove, move, "down", 23);
                } else
                    textOut.append("Type high roll to roll the dice for first turn. \nPlay will not continue otherwise\n\n");
                appendText();
            }
            else{
                textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
                appendText();
            }
        }
        if(move.equals("d")){
            if (!isAccusationInProgress()) {
                if (rolledHigh()) {
                    movementInput.movementX(checkMove, move, "right", 23);
                } else
                    textOut.append("Type high roll to roll the dice for first turn. \nPlay will not continue otherwise\n\n");
                appendText();
            }
            else{
                textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
                appendText();
            }
        }
    }

    protected static void weapon(){
        if (!isAccusationInProgress()) {
            if (rolledHigh()) {
                getWeaponChoice();
                getRoomChoice();
                Board.moveWeapon(getWeaponToMove(), getMoveToRoom()); // params are static variables initialized in getWeaponChoice and getRoomChoice
                appendText();
            } else
                textOut.append("Type high roll to roll the dice for first turn. \nPlay will not continue otherwise\n\n");
            appendText();
        }
        else{
            textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
            appendText();
        }
    }
    protected static void roll(){
        if (!isAccusationInProgress()) {
            if (rolledHigh()) {
                printPlayerDiceRoll(PlayerManager.checkIsTurn(), playerRolled());
                appendText();
            } else
                textOut.append("Type high roll to roll the dice for first turn. \nPlay will not continue otherwise\n\n");
            appendText();
        }
        else{
            textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
            appendText();
        }
    }
    protected static void done(){
        if (!isAccusationInProgress()) {
            if (rolledHigh()) {
                PlayerManager.storeLastMove();
                textOut.setText(null);
                finishPlayerTurn();
                appendText();
            } else
                textOut.append("Type high roll to roll the dice for first turn. \nPlay will not continue otherwise\n\n");
            appendText();
        }
        else{
            textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
            appendText();
        }
    }
    protected static void passage(){
        if (!isAccusationInProgress()) {
            if (rolledHigh()) {
                if (!passageInRoom()) {
                    textOut.append(currentRoom() + " does not have a secret passage\n\n");
                    appendText();
                } else if (usedSecretPassage()) {
                    textOut.append("You have already used a passage, type done\n\n");
                    appendText();
                } else if (passageInRoom() && !usedSecretPassage()) {
                    int usePassage = JOptionPane.showConfirmDialog(
                            Board.mainFrame,
                            "Are you sure you want to use the passage?",
                            "Enter Secret passage?",
                            JOptionPane.YES_NO_OPTION);
                    if (usePassage == JOptionPane.YES_OPTION) {
                        RoomMovement.takePassage(characterIndex(), PlayerManager.checkIsTurn());
                        appendText();
                    }
                }
            } else
                textOut.append("Type high roll to roll the dice for first turn. \nPlay will not continue otherwise\n\n");
            appendText();
        }
        else{
            textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
            appendText();
        }
    }
    protected static void highRoll(){
        if (!isAccusationInProgress()) {
            if (!rolledHigh()) {
                RollDiceForFirstTurn.fillDiceRolls(PlayerManager.getNumOfPlayers());
                RollDiceForFirstTurn.findHighestRoll(duplicateRolls);
                PlayerManager.assignCards();
                PlayerManager.setStringCardProperNames();
                PlayerManager.setStringMurderCards();
                appendText();
            } else
                textOut.append("Dice has already been rolled to choose first turn!\n\n");
            appendText();
        }
        else{
            textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
            appendText();
        }
    }
    protected static void notes(){
        if (PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isTurn()) {
            showNotes(PlayerManager.checkIsTurn());
        } else {
            textOut.append("You cannot look at your notes yet!\nType high roll to roll the dice for first turn. \n\n");
        }
        appendText();
    }
    public static void cheat(){
        if (!isAccusationInProgress() && rolledHigh()) {
            textOut.append("Player " + PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).getPlayerName() + " has cheated and revealed\n\n");
            JFrame murderFrame = PlayerManager.getCardSetup().getMurderFrame();
            murderFrame.setVisible(true);
            murderFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            appendText();
        }
        else{
            textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
            appendText();
        }
    }
    public static void help(){
        if (!isAccusationInProgress()) {
            if (rolledHigh()) {
                Help.helpInstructions();
                appendText();
            } else
                textOut.append("Type high roll to roll the dice for first turn. \nPlay will not continue otherwise\n\n");
            appendText();
        }
        else{
            textOut.append("Accusation in progress, enter the name of the card you want to show\n\n");
            appendText();
        }
    }
    public static void question(){
        if (!isAccusationInProgress() && getPlayer().isInRoom()) {
            Question.askQuestion();
            appendText();
        }
        else if(!getPlayer().isInRoom()){
            textOut.append("You are not in a room to make an accusation\n\n");
            appendText();
        }
        else{
            textOut.append("Accusation in progress, type show card\n\n");
            appendText();
        }
    }
    public static void showCard(){
        if(isAccusationInProgress()){
            String card;
            if(Question.getHasCard() == 1){
                card = Question.getPlayerHadOneCard();
            }
            else{
                card = moreThanOneCardToShow();
            }
            Question.showCard(card);
            appendText();
        }
        else{
            textOut.append("There is no accusation in progress!\n\n");
            appendText();
        }
    }


}
