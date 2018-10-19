package SoftwareEngineering2;

import java.util.ArrayList;

import static SoftwareEngineering2.InputOutput.textOut;

public class RollDiceForFirstTurn extends Dice {

    private static ArrayList<Integer> diceRolls = new ArrayList<>(); // diceRolls size == number of players
    private static int highestRollPlayerIndex = 0; // player index of highest roll
    private static int count = 0; // count == number of recursive calls i.e. how many times players rolled duplicate high rolls
    private static ArrayList<int[][]> listOfRepeatRolls = new ArrayList<>(); // list of 2d arrays, each 2d array holds the dice roll and player index of each recursive call. Only gets filled if duplicates rolled > 1 times
    private static int highRoll = 0;
    private static boolean rolledHigh = false;

    /*Method fills arrayList diceRolls with the sum of two random numbers
    * @Param totalRolls is the number of players*/
    public static void fillDiceRolls(int totalRolls){
        int i = 0;
        while(i < totalRolls){
            diceRolls.add(rollTotal());
            i++;
        }
    }

    /*Recursive method finds the player index with the highest roll
    * @Param duplicateRolls is an empty list is filled with player index of highest roll*/
    public static void findHighestRoll(ArrayList<Integer> duplicateRolls){
        int index = 0;
        int initialHighRoll = 0;

        if(duplicateRolls.size() == 1){ // base case method has found one player with the highest roll
            if(count == 0) { // if the initial call found 1 high roll
                textOut.append("Player " + playerArrayList().get(highestRollPlayerIndex).getCharacterName() + " rolled the highest and is now first to take a turn\n\n");
                setFirstPlayer(highestRollPlayerIndex); // sets first player to player found with highest roll
                setRolledHigh(true);
            }
            if(count > 0) { // if duplicate rolls were found more then once

                ArrayList<Integer> rolls = new ArrayList<>(); // holds dice rolls taken from 2d array in listOfRepeats.get()[][0]
                ArrayList<Integer> players = new ArrayList<>(); // holds player indexes from 2d array in listOfRepeats.get()[][1]
                for (int i = 0; i < listOfRepeatRolls.size(); i++) {
                    for (int j = 0; j < listOfRepeatRolls.get(i).length; j++) {
                        rolls.add(listOfRepeatRolls.get(i)[j][0]);
                        players.add(listOfRepeatRolls.get(i)[j][1]);
                    }
                }
                int k = 0; // will hold highest roll from rolls
                int j = 0; // will hold index of highest roll from rolls, this will correspond to the player index that rolled it
                for (int i = 0; i < rolls.size(); i++) {
                    if (k < rolls.get(i)) {
                        k = rolls.get(i);
                        j = i;
                    }
                }
                if (count < 2) { // if there was < 3 recursive calls, count is initialized to 0 and increments each recursive call where duplicates.size() > 1, SO count == 2 == 3 recursive calls

                    textOut.append("Players re-roll\n");

                    for (int repeatIndex = 0; repeatIndex < listOfRepeatRolls.get(0).length; repeatIndex++){
                        textOut.append("\nPlayer controlling character: " + playerArrayList().get(listOfRepeatRolls.get(0)[repeatIndex][1]).getCharacterName() + " rolled " + listOfRepeatRolls.get(0)[repeatIndex][0]);
                    }

                    textOut.append("\n\nPlayer " + playerArrayList().get(players.get(j)).getCharacterName() + " rolled the highest and is now first to take a turn\n\n");
                    setFirstPlayer(players.get(j)); // if count < 2 player.get(j) is the correct index of player who rolled the highest
                    setRolledHigh(true);
                }
                else if(count >= 2){ // if there was >= 3 recursive calls, very rarely happens

                    textOut.append("\nPlayers re-roll again\n");
                    textOut.append("Player " + playerArrayList().get(listOfRepeatRolls.get(0)[players.get(j)][1]).getCharacterName() + " rolled the highest and is now first to take a turn\n\n");
                    setFirstPlayer(listOfRepeatRolls.get(0)[players.get(j)][1]); // this case the correct player index will be listOfRepeatRolls.get(0)[players.get(j)][1]
                    setRolledHigh(true);
                }
            }
        }
        else if(duplicateRolls.size() > 1){ // if more then one high rolls where found, duplicate rolls holds the player indexes of these high rolls
            textOut.append("\n");
            for(int i = 0; i < duplicateRolls.size(); i++){
                textOut.append("Player controlling character " + playerArrayList().get(duplicateRolls.get(i)).getCharacterName() + "\n");
            }
            if(duplicateRolls.size() == 2){
                textOut.append("Both rolled " + highRoll + "\n\n");
            }
            else{
                textOut.append("All rolled " + highRoll + "\n\n");
            }

            diceRolls.clear(); // clears previous dice rolls
            fillDiceRolls(duplicateRolls.size()); // rolls the dice again for each player who previously rolled the same high roll

            fillListRepeatRolls(duplicateRolls); // adds a new 2d array to list and fills with dice rolls and player indexes
            findInitialHighRoll(0, initialHighRoll); // finds the initial high roll, duplicates to this are found in findDuplicateHighRolls

            index = 0;

            duplicateRolls.clear(); // clears duplicates

            findHighestRoll(findDuplicateHighRolls(initialHighRoll, index, duplicateRolls)); // findDuplicateHighRolls() removes player indexes that don't match the initial high roll and returns an arrayList of duplicates. if arrayList == 1 then method enters base case otherwise comes back here
        }
        else{ // initial case where duplicateRolls isEmpty. Fills dice rolls, finds an initial high roll and any duplicates then passes arrayList to recursive call
            System.out.println("Size of dice rolls: " + diceRolls.size());
            int highRoll = findInitialHighRoll(index, initialHighRoll);
            System.out.println("highRoll: " + highRoll);

            System.out.println("dupSize: " + duplicateRolls.size());
            int i = 0;
            for(Integer roll : diceRolls){
                textOut.append("Dice roll: " + roll + " for player controlling character " + playerArrayList().get(i).getCharacterName() + "\n");
                i++;
            }
            System.out.println("\n");
            findHighestRoll(findDuplicateHighRolls(highRoll, index, duplicateRolls));
        }
    }

    /*Finds an initial high roll
    * @Param index basic index variable starts at 0
    * @Param initialHighRoll integer starts at 0
    * @Return the value stored in initial high roll*/
    private static int findInitialHighRoll(int index, int initialHighRoll){

        while(index < diceRolls.size()){
            if(initialHighRoll < diceRolls.get(index)){
                initialHighRoll = diceRolls.get(index);
                highestRollPlayerIndex = index;
            }
            index++;
        }
        highRoll = initialHighRoll;
        return initialHighRoll;
    }

    /*Method uses the initial highRoll to find player indexes who rolled the same
    * @Param highRoll is initial high roll returned from findInitialHighRoll
    * @Param index basic index variable starts at 0
    * @Param duplicateRolls is an empty arraylist which will hold player indexes of players who rolled the same high roll
    * @Return duplicateRolls arrayList filled with player indexes of high rolls, returned as an argument in the recursive calls*/
    private static ArrayList<Integer> findDuplicateHighRolls(int highRoll, int index, ArrayList<Integer> duplicateRolls){
        duplicateRolls.add(highestRollPlayerIndex); // player who was found to have the initial high roll is added first

        while(index < diceRolls.size()){
            if(highRoll == diceRolls.get(index) && index != highestRollPlayerIndex){
                duplicateRolls.add(index);
            }
            index++;
        }
        return duplicateRolls;
    }

    /*Method sets first player and keeps original order of turns
    * @Param firstPLayerIndex is the player index found in recursive method*/
    private static void setFirstPlayer(int firstPlayerIndex){
        Player tempPlayer = playerArrayList().get(firstPlayerIndex); // initializes a temporary Player instance with the player who will be first
        playerArrayList().remove(firstPlayerIndex); // removes first player from current position in playerArrayList
        playerArrayList().add(0, tempPlayer); // adds player to first position
        playerArrayList().get(0).setTurn(true); // sets first player turn attribute to true
    }

    /*Method adds a new 2d array to list of 2d arrays and fills array with dice roll and player index
    * @Param duplicates arraylist filled with player indexes of duplicate high rolls
    * */
    private static void fillListRepeatRolls(ArrayList<Integer> duplicates){
        int[][] diceRollAndPlayerIndex = new int[diceRolls.size()][2];
        for(int i = 0; i < duplicates.size(); i++){
            diceRollAndPlayerIndex[i][0] = diceRolls.get(i);
            diceRollAndPlayerIndex[i][1] = duplicates.get(i);
        }
        listOfRepeatRolls.add(count, diceRollAndPlayerIndex);
        count++;
    }

    public static boolean isRolledHigh() {
        return rolledHigh;
    }

    public static void setRolledHigh(boolean rolledHigh) {
        RollDiceForFirstTurn.rolledHigh = rolledHigh;
    }

    private static ArrayList<Player> playerArrayList(){
        return PlayerManager.getPlayerArrayList();
    }
}