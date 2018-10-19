package SoftwareEngineering2;

import static SoftwareEngineering2.InputOutput.appendText;
import static SoftwareEngineering2.InputOutput.textOut;
/*Class handles the movement of the player with methods for movement on the x axis and y axis
* if, else if statements in the body of movement methods will provide error messages or perform actions
* depending on which player attributes have been set, e.g. if the players inRoom and justEntered attributes
 * are set to true they will receive a different output message then if inRoom and startInRoom are set*/
class MovementInput {
    private static final String newLine = "\n";
    /*movementY handles movement on the Y axis
    * @Param checkMove is an object of the FetchMoveAndCheck class, pre-checks players attempted move and gives an error if illegal
    * @Param input string taken from textIn w, a, s or d
    * @Param direction will be "up" or "down" depending on button pressed, only used to print last move player made
    * @Param y is the number to add or take from the y axis to move up or down*/
    void movementY(FetchMoveAndCheck checkMove, String input, String direction, int y){
        if (!checkMove.moveAllowed(input, characterIndex()) || !playerRolled() && !inRoom()) {
            textOut.append("Illegal move!" + newLine + newLine);
            appendText();
        }
        else if(inRoom() && justEntered()){
            printInRoomText();
        }
        else if(playerRolled() && inRoom() && !justEntered() && !usedSecretPassage()){
            RoomMovement.startTurnInRoom(characterIndex(), PlayerManager.checkIsTurn());
            appendText();
        }
        else if(playerRolled() && leftRoom()){
            textOut.append("Player pressed " + input + " and moved " + direction + " one space" + newLine + newLine);
            appendText();
            Board.movePlayerYAxis(characterIndex(), y, PlayerManager.checkIsTurn());
            setLeftRoomFalse();
        }
        else if(startInRoom()){
            textOut.append("You have started your turn in the " + currentRoom() + "\nplease roll the dice to continue\n\n");
            appendText();
        }
        else if(usedSecretPassage()){
            textOut.append("You used a secret passage and went to " + currentRoom() +
                    "\nno more moves left type done\n\n");
            appendText();
        }
        else {
            if(playerRolled() && movesLeft() != 0){
                textOut.append("Player pressed " + input + " and moved " + direction + " one space" + newLine + newLine);
                appendText();
                Board.movePlayerYAxis(characterIndex(), y, PlayerManager.checkIsTurn());
                RoomEntrance.isRoomEntrance(characterIndex(), PlayerManager.checkIsTurn(), leftRoom());
            }
            else{
                textOut.append("No more moves type done to finish move\n\n");
                appendText();
            }
        }
    }
    /*movementX handles movement on the X axis
    * @Param checkMove is an object of the FetchMoveAndCheck class, pre-checks players attempted move and gives an error if illegal
    * @Param input string taken from textIn w, a, s or d
    * @Param direction will be "left" or "right" depending on button pressed, only used to print last move player made
    * @Param x is the number to add or take from the x axis to move left or right*/
    void movementX(FetchMoveAndCheck checkMove, String input, String direction, int x){
        if (!checkMove.moveAllowed(input, characterIndex()) || !playerRolled() && !inRoom()) {
            textOut.append("Illegal move!" + newLine + newLine);
            appendText();
        }
        else if(inRoom() && justEntered()){
            printInRoomText();
        }
        else if(playerRolled() && inRoom() && !justEntered() && !usedSecretPassage()){
            RoomMovement.startTurnInRoom(characterIndex(), PlayerManager.checkIsTurn());
            appendText();
        }
        else if(playerRolled() && leftRoom()){
            textOut.append("Player pressed " + input + " and moved " + direction + " one space" + newLine + newLine);
            appendText();
            Board.movePlayerXAxis(characterIndex(), x, PlayerManager.checkIsTurn());
            setLeftRoomFalse();
        }
        else if(startInRoom()){
            textOut.append("You have started your turn in the " + currentRoom() + "\nplease roll the dice to continue\n\n");
            appendText();
        }
        else if(usedSecretPassage()){
            textOut.append("You used a secret passage and went to " + currentRoom() +
                    "\nno more moves left type done\n\n");
            appendText();
        }
        else {
            if(playerRolled() && movesLeft() != 0){
                textOut.append("Player pressed " + input + " and moved " + direction + " one space" + newLine + newLine);
                appendText();
                Board.movePlayerXAxis(characterIndex(), x, PlayerManager.checkIsTurn());
                RoomEntrance.isRoomEntrance(characterIndex(), PlayerManager.checkIsTurn(), leftRoom());
            }
            else{
                textOut.append("No more moves type done to finish move\n\n");
                appendText();
            }
        }
    }
    /*prints message to user depending on whic player attributes have been set*/
    private void printInRoomText(){
        if(usedSecretPassage()){
            textOut.append("You used a secret passage, no more turns left. Type done\n\n");
            appendText();
        }
        else if(justEntered()){
            textOut.append("You just entered a room, no more moves left. Type done\n\n");
            System.out.println(justEntered());
            appendText();
        }
    }
    /*@Return current players character index*/
    private int characterIndex(){
        return PlayerManager.getCharIndex();
    }
    /*@Return true if player rolled the dice already on this turn*/
    private boolean playerRolled(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isRolledDice();
    }
    /*@Return number of moves current player has left*/
    private int movesLeft(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).getMoves();
    }
    /*@Return true if player is in a room*/
    private boolean inRoom(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isInRoom();
    }
    /*@Return true if player has just left a room, if true stops player being asked to enter room just left*/
    private boolean leftRoom(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isJustLeftRoom();
    }
    /*sets leftRoom to false after first move outside room */
    private void setLeftRoomFalse(){
        PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).setJustLeftRoom(false);
    }
    /*@Return true if player has just entered a room*/
    private boolean justEntered(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isJustEntered();
    }
    /*@Return true if player has ended their turn in a room and will be starting their next turn in a room*/
    private boolean startInRoom(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isStartInRoom();
    }
    /*@Return the cuurent room player is in*/
    private String currentRoom(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).getCurrentRoom();
    }
    /*@Return true if player has just used a passage*/
    private boolean usedSecretPassage(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isUsedSecretPassage();
    }

}
