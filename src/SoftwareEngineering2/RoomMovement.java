package SoftwareEngineering2;

import javax.swing.*;

import static SoftwareEngineering2.Board.board;
import static SoftwareEngineering2.InputOutput.textOut;

/*Class handles all movementX when player starts a turn inside a room
* also handles the use of secret passageways*/
public class RoomMovement{
    /*startTurnInRoom() uses switch statement to check what room a player is currently in
    * depending on the case and if the player has rolled the dice they will be prompted with a dialog box
    * asking which exit they would like to take. 1 being the exit furthest on the left
    * Rooms with secret passageways have the option to take these instead of an exit
    * @Param character is the index value of the players chosen character
    * @Param player is the index value of the player who's turn it is*/
    public static void startTurnInRoom(int character, int player) {
        if (inRoom()) {
            switch (whichRoom().toLowerCase()) {
                case "dining room":
                    Object[] dinExit = {"1", "2"}; // option for room exit 1 is the exit furthest on the left
                    String exit = (String) JOptionPane.showInputDialog(Board.mainFrame,
                            "Pick an exit!", "Dining Room Exit",
                            JOptionPane.QUESTION_MESSAGE, null, dinExit, "1");
                    if (exit.equalsIgnoreCase("1")) {
                        board.add(Board.playerIcons.get(character)).setBounds(181, 393, 21, 21);
                        toCorridor(player);
                    } else {
                        board.add(Board.playerIcons.get(character)).setBounds(227, 301, 21, 21);
                        toCorridor(player);
                    }
                    break;
                case "lounge":
                    int oneExitRoom = JOptionPane.showConfirmDialog(
                            Board.mainFrame,
                            "Would you like to leave the Lounge?",
                            "Enter Corridor?",
                            JOptionPane.YES_NO_OPTION);
                    if(oneExitRoom == JOptionPane.YES_OPTION){
                        board.add(Board.playerIcons.get(character)).setBounds(181, 439, 21, 21);
                        exitSecretPassageRoom(player);
                    }
                    break;
                case "hall":
                    Object[] hallExit = {"1", "2", "3"};
                        exit = (String) JOptionPane.showInputDialog(Board.mainFrame,
                            "Pick an exit!", "Hall Exit",
                            JOptionPane.QUESTION_MESSAGE, null, hallExit, "1");
                    if (exit.equalsIgnoreCase("1")) {
                        board.add(Board.playerIcons.get(character)).setBounds(296, 416, 21, 21);
                        toCorridor(player);
                    } else if(exit.equalsIgnoreCase("2")){
                        board.add(Board.playerIcons.get(character)).setBounds(319, 416, 21, 21);
                        toCorridor(player);
                    }
                    else if(exit.equalsIgnoreCase("3")){
                        board.add(Board.playerIcons.get(character)).setBounds(388, 485, 21, 21);
                        toCorridor(player);
                    }
                    break;
                case "study":
                    oneExitRoom = JOptionPane.showConfirmDialog(
                            Board.mainFrame,
                            "Would you like to leave the Study?",
                            "Enter Corridor?",
                            JOptionPane.YES_NO_OPTION);
                    if(oneExitRoom == JOptionPane.YES_OPTION){
                        board.add(Board.playerIcons.get(character)).setBounds(434, 485, 21, 21);
                        exitSecretPassageRoom(player);
                    }
                    break;
                case "library":
                    Object[] libExit = {"1", "2"};
                        exit = (String) JOptionPane.showInputDialog(Board.mainFrame,
                            "Pick an exit!", "Library Exit",
                            JOptionPane.QUESTION_MESSAGE, null, libExit, "1");
                    if (exit.equalsIgnoreCase("1")) {
                        board.add(Board.playerIcons.get(character)).setBounds(411, 393, 21, 21);
                        toCorridor(player);
                    } else {
                        board.add(Board.playerIcons.get(character)).setBounds(503, 324, 21, 21);
                        toCorridor(player);
                    }
                    break;
                case "billiard":
                    Object[] billExit = {"1", "2"};
                    exit = (String) JOptionPane.showInputDialog(Board.mainFrame,
                            "Pick an exit!", "Billiard Exit",
                            JOptionPane.QUESTION_MESSAGE, null, billExit, "1");
                    if (exit.equalsIgnoreCase("1")) {
                        board.add(Board.playerIcons.get(character)).setBounds(434, 232, 21, 21);
                        toCorridor(player);
                    } else {
                        board.add(Board.playerIcons.get(character)).setBounds(549, 324, 21, 21);
                        toCorridor(player);
                    }
                    break;
                case "conservatory":
                    oneExitRoom = JOptionPane.showConfirmDialog(
                            Board.mainFrame,
                            "Would you like to leave the Conservatory?",
                            "Enter Corridor?",
                            JOptionPane.YES_NO_OPTION);
                    if(oneExitRoom == JOptionPane.YES_OPTION){
                        board.add(Board.playerIcons.get(character)).setBounds(457, 140, 21, 21);
                        exitSecretPassageRoom(player);
                    }
                    break;
                case "ballroom":
                    Object[] ballExit = {"1", "2", "3", "4"};
                    exit = (String) JOptionPane.showInputDialog(Board.mainFrame,
                            "Pick an exit!", "Hall Exit",
                            JOptionPane.QUESTION_MESSAGE, null, ballExit, "1");
                    if (exit.equalsIgnoreCase("1")) {
                        board.add(Board.playerIcons.get(character)).setBounds(204, 140, 21, 21);
                        toCorridor(player);
                    } else if(exit.equalsIgnoreCase("2")){
                        board.add(Board.playerIcons.get(character)).setBounds(250, 209, 21, 21);
                        toCorridor(player);
                    }
                    else if(exit.equalsIgnoreCase("3")){
                        board.add(Board.playerIcons.get(character)).setBounds(365, 209, 21, 21);
                        toCorridor(player);
                    }
                    else if(exit.equalsIgnoreCase("4")){
                        board.add(Board.playerIcons.get(character)).setBounds(411, 140, 21, 21);
                        toCorridor(player);
                    }
                    break;
                case "kitchen":
                    oneExitRoom = JOptionPane.showConfirmDialog(
                            Board.mainFrame,
                            "Would you like to leave the Kitchen?",
                            "Enter Corridor?",
                            JOptionPane.YES_NO_OPTION);
                    if(oneExitRoom == JOptionPane.YES_OPTION){
                        board.add(Board.playerIcons.get(character)).setBounds(135, 186, 21, 21);
                        exitSecretPassageRoom(player);
                    }
                    break;
                case "basement" :
                    oneExitRoom = JOptionPane.showConfirmDialog(
                            Board.mainFrame,
                            "Would you like to leave the Basement?",
                            "Enter Corridor?",
                            JOptionPane.YES_NO_OPTION);
                    if(oneExitRoom == JOptionPane.YES_OPTION){
                        board.add(Board.playerIcons.get(character)).setBounds(319, 416, 21, 21);
                        toCorridor(player);
                    }
            }
        }
    }
    /*return a boolean if player is in a room*/
    private static boolean inRoom() {
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isInRoom();
    }
    /*return a string corresponding to current player location used as condition in switch statement of startTurnInRoom()*/
    private static String whichRoom() {
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).getCurrentRoom();
    }
    /*Method handles the movement when a player selects the secret passageway in lounge
    Each character icon has there own coordinates in each room
    switch statement handles this by using character index to decide what coordinates they get sent to*/
    private static void loungeToConservatoryPassage(int character, int player) {
        switch (character) {
            case 0:
                toConservatory(character, player, 503, 94);
                break;
            case 1:
                toConservatory(character, player, 526, 94);
                break;
            case 2:
                toConservatory(character, player, 549, 94);
                break;
            case 3:
                toConservatory(character, player, 549, 71);
                break;
            case 4:
                toConservatory(character, player, 526, 71);
                break;
            case 5:
                toConservatory(character, player, 503, 71);
                break;
        }
    }
    /*Same as above except going from conservatory to lounge*/
    private static void conservatoryToLoungePassage(int character, int player) {
        switch (character) {
            case 0:
                toLounge(character, player, 89, 531);
                break;
            case 1:
                toLounge(character, player, 112, 531);
                break;
            case 2:
                toLounge(character, player, 135, 531);
                break;
            case 3:
                toLounge(character, player, 135, 508);
                break;
            case 4:
                toLounge(character, player, 112, 508);
                break;
            case 5:
                toLounge(character, player, 89, 508);
                break;
        }
    }

    private static void studyToKitchenPassage(int character, int player){
        switch(character){
            case 0 :
                toKitchen(character, player, 66, 117);
                break;
            case 1 :
                toKitchen(character, player, 89, 117);
                break;
            case 2 :
                toKitchen(character, player, 112, 117);
                break;
            case 3 :
                toKitchen(character, player, 112, 94);
                break;
            case 4 :
                toKitchen(character, player, 89, 94);
                break;
            case 5 :
                toKitchen(character, player, 66, 94);
                break;
        }
    }

    private static void kitchenToStudyPassage(int character, int player){
        switch(character){
            case 0 :
                toStudy(character, player, 480, 554);
                break;
            case 1 :
                toStudy(character, player, 503, 554);
                break;
            case 2 :
                toStudy(character, player, 526, 554);
                break;
            case 3 :
                toStudy(character, player, 526, 521);
                break;
            case 4 :
                toStudy(character, player, 480, 531);
                break;
            case 5 :
                toStudy(character, player, 503, 531);
                break;
        }
    }
    /*Method sets the boolean attributes associated with using a secret passage
    * @Param player is the index value of the current player
    * playerArrayList holds all current players in the game*/
    private static void setUsedPassageAttributes(int player){
        PlayerManager.getPlayerArrayList().get(player).setInRoom(true);
        PlayerManager.getPlayerArrayList().get(player).setMoves(0);
        PlayerManager.getPlayerArrayList().get(player).setUsedSecretPassage(true);
        PlayerManager.getPlayerArrayList().get(player).setSecretPassage(true);
    }
    /*Method called in loungeToConservatoryPassage() sends player to conservatory if secret passage used
    * @Param character is the index of current players character in playerIcons
    * @Param player current players index value in playerArrayList from PlayerManager
    * @Param x is the x coordinate to set player icon in conservatory because each player has a set tile for each room x and y will be different for each character
    * @Param y is the y coordinate to send current players character icon*/
    private static void toConservatory(int character, int player, int x, int y){
        board.add(Board.playerIcons.get(character)).setBounds(x, y, 21, 21);
        setUsedPassageAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("conservatory");
    }
    /*Works exactly the same as above except from conservatory to lounge*/
    private static void toLounge(int character, int player, int x, int y){
        board.add(Board.playerIcons.get(character)).setBounds(x, y, 21, 21);
        setUsedPassageAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("lounge");
    }
    /*From study to kitchen passage*/
    private static void toKitchen(int character, int player, int x, int y){
        board.add(Board.playerIcons.get(character)).setBounds(x, y, 21, 21);
        setUsedPassageAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("kitchen");
    }
    /*From kitchen to study*/
    private static void toStudy(int character, int player, int x, int y){
        board.add(Board.playerIcons.get(character)).setBounds(x, y, 21, 21);
        setUsedPassageAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("study");
    }
    /*Sets player attributes when they choose to exit a room with a passage without using the passage on that turn
    * @Param player current players index value in playerArrayList from PlayerManager*/
    private static void exitSecretPassageRoom(int player){
        PlayerManager.getPlayerArrayList().get(player).setInRoom(false);
        PlayerManager.getPlayerArrayList().get(player).setStartInRoom(false);
        PlayerManager.getPlayerArrayList().get(player).setJustLeftRoom(true);
        PlayerManager.getPlayerArrayList().get(player).setSecretPassage(false);
        PlayerManager.getPlayerArrayList().get(player).setInCorridor(true);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("Corridor");
    }
    /*sets player attributes when they exit a room to the corridor
    * @Param player current players index value in playerArrayList from PlayerManager
    * */
    private static void toCorridor(int player){
        PlayerManager.getPlayerArrayList().get(player).setInRoom(false);
        PlayerManager.getPlayerArrayList().get(player).setStartInRoom(false);
        PlayerManager.getPlayerArrayList().get(player).setJustLeftRoom(true);
        PlayerManager.getPlayerArrayList().get(player).setInCorridor(true);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("Corridor");
    }
    /*Package private method called in InputOutput class when user types passage
    * @Param player current players index value in playerArrayList from PlayerManager
    * @Param character is the index of current players character in playerIcons
    * sends player character to the room that the passage leads to in their current room*/
    static void takePassage(int character, int player){
        switch(whichRoom().toLowerCase()){
            case "kitchen":
                kitchenToStudyPassage(character, player);
                break;
            case "study":
                studyToKitchenPassage(character, player);
                break;
            case "lounge":
                loungeToConservatoryPassage(character, player);
                break;
            case "conservatory":
                conservatoryToLoungePassage(character, player);
                break;
            default:
                textOut.append("No secret passageway to take!\n\n");
        }
    }
}
