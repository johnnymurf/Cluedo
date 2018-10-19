package SoftwareEngineering2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InputOutput extends JPanel implements ActionListener {
    static JTextField textIn;
    static TextArea textOut;
    private static  String weaponToMove;
    private static String moveToRoom;
    private static final Object[] wChoices = {"rope", "dagger", "wrench", "lead pipe", "revolver", "candlestick"};
    private static final Object[] rChoices = {"ballroom", "conservatory", "kitchen", "dining room", "lounge", "hall", "study", "library", "billiard room"};
    private static final String newLine = "\n";
    protected static FetchMoveAndCheck checkMove;
    protected static ArrayList<Integer> duplicateRolls = new ArrayList<>();
    protected static MovementInput movementInput = new MovementInput();


    InputOutput() {
        checkMove = new FetchMoveAndCheck();
        textIn = new JTextField();
        textOut = new TextArea("Type \"roll\" to roll the dice\n" +
                "Type \"weapon\" to move a weapon.\n" +
                "'u' to move up \n'd' to move down\n'l' to move left \n'r' to move right\n" +
                "Type \"help\" for possible moves you can make\n" +
                "Type \"notes\" to see your notes\n" +
                "Type \"cheat\" to reveal envelope cards\n" +
                "Type \"done\" to finish a turn\n" +
                "Type \"log\" to see the question log\n" +
                "Type \"question\" in a room to ask a question\n" +
                "Type \"accuse\" when in the basement to make a final accusation\n" +
                "Type \"quit\" to end the game\n\n");
        textOut.setEditable(false);
        setTextStyle();
        textIn.addActionListener(this);
    }

    /*appendText() saves on repeat code, selectAll() selects all current text in textIn, selected text is printed to textOut
    setText() resets textIn when user hits enter, both methods would need to be called for each case in actionPerformed*/
    static void appendText() {
        textIn.selectAll();
        textIn.setText("");
    }

    /*actionPerformed takes input from textIn when the enter button is pressed
    * Calls Command class methods depending on input for implementation
    **/
    @Override
    public void actionPerformed(ActionEvent event) {
        String input = textIn.getText().toLowerCase();

        if(input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") ){
            Commands.wasd(input);
            return;
        }
        if(input.equals("weapon")){
            Commands.weapon();
            return;
        }
        if(input.equals("roll")){
            Commands.roll();
            return;
        }
        if(input.equals("done")){
            Commands.done();
            return;
        }
        if(input.equals("passage")){
            Commands.passage();
            return;
        }
        if (input.equals("high roll")){
            Commands.highRoll();
            return;
        }
        if(input.equals("notes")){
            Commands.notes();
            return;
        }
        if(input.equals("cheat")){
            Commands.cheat();
            return;
        }
        if(input.equals("help")){
            Commands.help();
            return;
        }
        if(input.equals("question")){
            Commands.question();
            return;
        }
        if(input.equals("show card")){
            Commands.showCard();
            return;
        }
        if(input.equals("accuse")){
            Question.makeFinalAccusation();
            return;
        }
        if(input.equals("log")){
            Log.setLogVisible();
            appendText();
            return;
        }
        if(input.equals("quit")){
            quitGame();
        }
        else{
            textOut.append("Player input \"" + input + "\" Illegal, please enter appropriate command" + newLine + newLine);
            appendText();
        }
    }

    public static void showPlayerCard(String card){
        boolean found = false;
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(PlayerManager.getPlayerArrayList().get(Question.getCurrentPlayerToShowCards()).getCards().getCards());
        Iterator<Card> iterator = cards.iterator();
        System.out.println("showPlayerCard outside loop\n" + Question.getCurrentPlayerToShowCards());
        do{
            Card c = iterator.next();
            System.out.println("/"+card+".png");
            System.out.println(c.getCardName());
            if(("/"+card+".png").equalsIgnoreCase(c.getCardName())) {
                getPlayerToShowCard().getClues().addToViewedCard(c);
                getPlayerToShowCard().setViewed(getPlayerToShowCard().getClues().getViewed());
                System.out.println("showPlayerCard: " + getPlayerToShowCard().getCharacterName());
                found = true;
            }
        }while(iterator.hasNext() && !found);
        System.out.println("showPlayerCard after loop\n");
    }

    protected static Player getPlayerToShowCard(){
        return PlayerManager.getPlayerArrayList().get(Question.getPlayerIndexWhoAskedQuestion());
    }

    protected static String moreThanOneCardToShow(){

        Object[] playerCards = new Object[PlayerManager.getPlayerArrayList().get(Question.getCurrentPlayerToShowCards()).getStringCards().size()];

        for(int i = 0; i < PlayerManager.getPlayerArrayList().get(Question.getCurrentPlayerToShowCards()).getStringCards().size(); i++){
            playerCards[i] = getStringCards().get(i);
        }

        return (String) JOptionPane.showInputDialog(Board.mainFrame,
                "Pick a card!", "Card Choice",
                JOptionPane.QUESTION_MESSAGE, null, playerCards, playerCards[0]);
    }

    private static ArrayList<String> getStringCards(){
        return PlayerManager.getPlayerArrayList().get(Question.getCurrentPlayerToShowCards()).getStringCards();
    }

    /*@Return player choice of weapon to move*/
    protected  static String getWeaponToMove() {
        return weaponToMove;
    }

    /*@Return player choice of room to move weapon to*/
    protected static String getMoveToRoom() {
        return moveToRoom;
    }

    protected static boolean isAccusationInProgress(){
        return Question.isAccusationInProgress();
    }
 
    public static void showNotes(int turn){
        System.out.println("Player notes for " + PlayerManager.getPlayerArrayList().get(turn).getCharacterName());
        if(isAccusationInProgress()){
            turn = Question.getPlayerIndexWhoAskedQuestion();
            System.out.println("Player notes for " + PlayerManager.getPlayerArrayList().get(turn).getCharacterName());
        }
        Player play = PlayerManager.getPlayerArrayList().get(turn);
        JFrame notes = play.getNotes();
        JFrame cards = play.getDeck();
        cards.pack();
        cards.setVisible(true);

        if (18 % PlayerManager.getNumOfPlayers() != 0) {
            JFrame extras = PlayerManager.getPlayerArrayList().get(turn).getExtras();
            extras.setVisible(true);
            extras.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            Point p = extras.getLocation();
            p.move(extras.getWidth() + p.x, 0);
            notes.setLocation(p);
        }
        cards.setVisible(true);
        notes.setVisible(true);
        cards.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        notes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /*getWeaponChoice provides a pop up dialog box with weapon choices
    * stores the choice in static variable weaponToMove
    * weaponToMove is used as an argument in moveWeapon method in Board.java*/
    protected static void getWeaponChoice() {
        weaponToMove = (String) JOptionPane.showInputDialog(Board.mainFrame,
                "Pick a weapon!", "Weapon Choice",
                JOptionPane.QUESTION_MESSAGE, null, wChoices, "rope");
        printWeaponChoice(weaponToMove);
    }

    /*getRoomChoice provides a pop up dialog box with room choices
    * stores the choice in static variable moveToRoom
    * moveToRoom is used as an argument in moveWeapon method in Board.java*/
    protected static void getRoomChoice() {
        moveToRoom = (String) JOptionPane.showInputDialog(Board.mainFrame,
                "Pick a room!", "Room Choice",
                JOptionPane.QUESTION_MESSAGE, null, rChoices, "ballroom");
        printRoomChoice(moveToRoom);
    }

    /*Sets font style, background colour and text colour for input and output text*/
    private void setTextStyle() {
        textIn.setFont(new Font("Serif", Font.BOLD, 20));
        textOut.setFont(new Font("Serif", Font.BOLD, 20));
        textIn.setBackground(Color.LIGHT_GRAY);
        textOut.setBackground(Color.LIGHT_GRAY);
        textIn.setForeground(Color.BLACK);
        textOut.setForeground(Color.BLACK);
    }
    private static void printToScreen(String message){
        textOut.append(message);
    }

    /*prints to textArea what weapon the user chose
    * Param weaponPicked is the return value from inputDialogBox */
    private static void printWeaponChoice(String weaponPicked) {
        printToScreen("Player chose the "+weaponPicked+"\n");
    }

    /*prints to textArea what room the user chose
    *@Param roomPicked is the return value from inputDialogBox */
    private static void printRoomChoice(String roomPicked) {
        printToScreen("Player chose the "+roomPicked+"\n");
    }

    /*Prints number of players chosen for the current game
    * @Param players numOfPLayers */
    void printNumberOfPlayers(int players) {
        printToScreen("Number of players for this game is "+players+"\n\n");
    }

    /*Prints player details when all player names and characters have been chosen*/
    void printPlayerDetails() {
        textOut.append("Player setup is finished here is a list\n" +
                "of all current player details\n\n");
        int i = 0;
        while (i < PlayerManager.getNumOfPlayers()) {
            textOut.append("Player " + (i + 1) + " name: " + getPname(i) + "\n");
            textOut.append("Player " + (i + 1) + " character choice: " + getCname(i) + "\n\n");
            i++;
        }
    }

    /*Simulates and prints the results of a player rolling two dice
    * @Param player is the current player
    * @Param rolled true if player has rolled already false if not*/
    protected static void printPlayerDiceRoll(int player, boolean rolled) {
        if (rolled) {
            textOut.append("You already rolled this turn\n\n");
        } else {
            int die1 = Dice.rollDie1();
            int die2 = Dice.rollDie2();

            textOut.append(getPname(player) + " controlling " + getCname(player) + " rolled a " + die1 + " and a\n" + die2 +
                    " you can now move " + (die1 + die2) + " places on the board\n\n");
            setPlayerMoves(player, (die1 + die2));
            startInRoomFalse();
            setRolledDiceTrue();
            PlayerManager.removeLastMove();
        }
    }

    /*called when player has ended their turn and typed done
    * resets moves and all boolean attributes of the current player ready for their next turn
    * if the player ends turn in a room sets startInRoom to true
    * Calls nextPlayer then prints nextPlayers details and current room
    * if room has passage print message alerting player*/
    protected static void finishPlayerTurn() {
        setPlayerMoves(PlayerManager.checkIsTurn(), 0);
        usedPassageFalse();
        setRolledDiceFalse();
        setJustEnteredFalse();
        if (PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isInRoom()) {
            PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).setStartInRoom(true);
        }
        int nextPlayer = PlayerManager.checkIsTurn() + 1;
        System.out.println("check turn in end turn " + PlayerManager.checkIsTurn());
        PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).setTurn(false);
        PlayerManager.nextPlayerTurn(nextPlayer);
        textOut.append("Player " + getPname(PlayerManager.checkIsTurn()) + " controlling " + getCname(PlayerManager.checkIsTurn()) + "\n" +
                "Your turn has started\n" + "You are currently in the " + currentRoom() + "\n" +
                "make a move\n\n");
        if (passageInRoom()) {
            textOut.append("The room you are currently in has a secret passage\ntype passage to use it.\n\n");
        }
        appendText();
    }

    /*quitGame method called if player types quit
    * shows a confirm dialog box asking for confirmation*/
    public static void quitGame() {
        int quit = JOptionPane.showConfirmDialog(
                Board.mainFrame,
                "Are you sure you want to quit, all progress will be lost?",
                "Quit Game?",
                JOptionPane.YES_NO_OPTION);
        if (quit == JOptionPane.YES_OPTION) {
            Board.quit();
            appendText();
        }
    }

    protected static Player getPlayer(){
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn());
    }

    /*gets current player name
    * @Param i is player index*/
    private static String getPname(int i) {
        return PlayerManager.getPlayerArrayList().get(i).getPlayerName();
    }

    /*gets current players character name
    * @Param i is player index*/
    private static String getCname(int i) {
        return PlayerManager.getPlayerArrayList().get(i).getCharacterName();
    }

    /*sets current players moves is called in printPlayerDiceRoll()
    * @Param i is player index
    * @Param diceRolled is the sum of two dice rolled*/
    private static void setPlayerMoves(int i, int diceRolled) {
        PlayerManager.getPlayerArrayList().get(i).setMoves(diceRolled);
    }

    /*gets current player character index*/
    protected static  int characterIndex() {
        return PlayerManager.getCharIndex();
    }

    /*@Return true if current player rolled the dice*/
    protected static boolean playerRolled() {
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isRolledDice();
    }

    /*sets boolean rolledDice to true for current player*/
    protected static void setRolledDiceTrue() {
        PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).setRolledDice(true);
    }

    /*sets boolean rolledDice to false for current player*/
    protected static void setRolledDiceFalse() {
        PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).setRolledDice(false);
    }

    /*sets boolean usedSecretPassage to false*/
    protected static void usedPassageFalse() {
        PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).setUsedSecretPassage(false);
    }

    /*sets boolean justEntered to false*/
    protected static void setJustEnteredFalse() {
        PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).setJustEntered(false);
    }

    /*@Return current players room*/
    protected static String currentRoom() {
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).getCurrentRoom();
    }

    /*sets start boolean startInRoom false*/
    protected static void startInRoomFalse() {
        PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).setStartInRoom(false);
    }

    /*@Return boolean usedSecretPassage true if player just used a passage*/
    protected static boolean usedSecretPassage() {
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isUsedSecretPassage();
    }

    /*@Return isSecretPassage true if current room contains a hidden passage*/
    protected static boolean passageInRoom() {
        return PlayerManager.getPlayerArrayList().get(PlayerManager.checkIsTurn()).isSecretPassage();
    }

    protected static boolean rolledHigh() {
        return RollDiceForFirstTurn.isRolledHigh();
    }
}