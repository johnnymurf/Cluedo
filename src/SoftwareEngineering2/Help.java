package SoftwareEngineering2;

import javax.swing.*;

import static SoftwareEngineering2.Board.mainFrame;

/* Class Help has one main method which depending on the current players situation brings up an option dialog box depending on PLayer attributes set
* Dialog will pop up when player types help*/

public class Help {

    public static void helpInstructions(){
        if(getPlayer().isInCorridor() && !getPlayer().isRolledDice()){ // current player is in the corridor and has not yet rolled the dice
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(mainFrame,
                    "You are currently in the corridor\n" +
                            "You can \"roll\" the dice to move\n" +
                            "You can check your \"notes\"\n" +
                            "You can end your turn \"done\"\n" +
                            "Or you can \"quit\" the game","Help",
                    JOptionPane.YES_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
        else if(getPlayer().isInCorridor() && getPlayer().isRolledDice() && getPlayer().getMoves() == 0){ // if current player is in the corridor, has rolled the dice and has no moves left
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(mainFrame,
                    "You are currently in the corridor\n" +
                            "You have rolled the dice and used all your moves\n" +
                            "You can check your \"notes\"\n" +
                            "You can end your turn \"done\"\n" +
                            "Or you can \"quit\" the game","Help",
                    JOptionPane.YES_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
        else if(getPlayer().isInCorridor() && getPlayer().isRolledDice() && getPlayer().getMoves() > 0){ // if current player is in the corridor, has rolled the dice and still has moves left
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(mainFrame,
                    "You are currently in the corridor and have already rolled the dice\n" +
                            "You still have " + getPlayer().getMoves() + " moves left\n" +
                            "You can check your \"notes\"\n" +
                            "You can end your turn \"done\"\n" +
                            "Or you can \"quit\" the game","Help",
                    JOptionPane.YES_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
        else if(getPlayer().isInRoom() && getPlayer().isJustEntered()){ // if current player is in a room and has just entered
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(mainFrame,
                    "You just entered a room\n" +
                            "You can check your \"notes\"\n" +
                            "You can end your turn \"done\"\n" +
                            "Or you can \"quit\" the game","Help",
                    JOptionPane.YES_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
        else if(getPlayer().isInRoom() && getPlayer().isStartInRoom() && !getPlayer().isSecretPassage()){ // if current player has started their turn in room and there is no secret passage
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(mainFrame,
                    "You started your turn in a room\n" +
                            "You can \"roll\" the dice and move to pick an exit\n" +
                            "You can check your \"notes\"\n" +
                            "You can end your turn \"done\"\n" +
                            "Or you can \"quit\" the game","Help",
                    JOptionPane.YES_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
        else if(getPlayer().isStartInRoom() && getPlayer().isSecretPassage() && !getPlayer().isUsedSecretPassage()){ // if current player starts in a room that has a secret passage
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(mainFrame,
                    "You started your turn in a room with a secret passage\n" +
                            "You can \"roll\" the dice and move to pick an exit\n" +
                            "You can use the \"passage\"\n" +
                            "You can check your \"notes\"\n" +
                            "You can end your turn \"done\"\n" +
                            "Or you can \"quit\" the game","Help",
                    JOptionPane.YES_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
        else if(getPlayer().isInRoom() && getPlayer().isSecretPassage() && getPlayer().isUsedSecretPassage()){ // if current player is in a room and has just used a secret passage
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(mainFrame,
                    "You have used a secret passage\n" +
                            "You can check your \"notes\"\n" +
                            "You can end your turn \"done\"\n" +
                            "Or you can \"quit\" the game","Help",
                    JOptionPane.YES_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
    }

    private static Player getPlayer(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn());
    }
}