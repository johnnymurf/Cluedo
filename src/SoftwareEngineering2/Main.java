package SoftwareEngineering2;

import java.io.IOException;

public class Main {
 
    public static void main(String[] args) throws IOException {
        Log log = new Log();
        Board board = new Board();
        Weapons weapons = new Weapons();
        PlayerIcons icons = new PlayerIcons();
        PlayerManager playerManager = new PlayerManager();

        log.showLog();
        board.showFrame();
        Log.createHiddenFrame();
        board.addWeaponIcons(weapons.getLabels()); // adds weapon label icons to weaponIcons from Board.java
        board.addPlayerIcons(icons.getPlayerIconJLabels()); // adds player label icons to playerIcons from Board.java, player label icons are character images

        playerManager.setNumOfPlayers(); // prompts user for number of players for current game
        int numberOfPlayers = PlayerManager.getNumOfPlayers();
        int i = 0;

        while(i < numberOfPlayers) {
            Question.getArray().add(i);
            playerManager.fillPlayerDetails(i); // prompts a player to enter their name and pick a character on each iteration
            i++;
        }

        playerManager.printPlayerDetails(); // prints all players names and characters picked when the last player enters their details
        Board.placeWeaponsRandom(); // places weapons in random rooms and finishes game setup
    }
}
