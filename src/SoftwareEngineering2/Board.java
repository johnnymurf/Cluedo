package SoftwareEngineering2;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Board extends InputOutput{
    public static JFrame mainFrame;
    static JLabel board; // board label holds the image of the board and all player and weapon icons
    private static ArrayList<JLabel> weaponIcons;
    static ArrayList<JLabel> playerIcons; // arrayList of playerIcon images, accessed throughout program to manipulate characters on the board
    private static ArrayList<String> roomList; // used in method to set weapons in random rooms at game setup
    private static ArrayList<String> weaponList; // used in method to set weapons in random rooms at game setup
    private static int index; // index of weaponIcons
    private static Random randRoom, randWeapon; // used in method to set weapons in random rooms at game setup
    /*constructor initializes frame, label and calls super class constructor*/
    Board() throws IOException {
        super();
        mainFrame = new JFrame("Cluedo");
        board = new JLabel(new ImageIcon(this.getClass().getResource(PathConstants.CLUEDO_BOARD_PATH)));
        weaponIcons = new ArrayList<>();
        playerIcons = new ArrayList<>();
        roomList = new ArrayList<>();
        weaponList = new ArrayList<>();
        randRoom = new Random();
        randWeapon = new Random();
    }

    /*showFrame() sets the layout for JFrame, builds and shows the JFrame*/
    public void showFrame(){
        mainFrame.setResizable(false);
        mainFrame.setBackground(Color.LIGHT_GRAY);

        mainFrame.getContentPane().add(textOut, BorderLayout.CENTER);

        mainFrame.getContentPane().add(textIn, BorderLayout.PAGE_END);

        mainFrame.getContentPane().add(board, BorderLayout.LINE_START);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    /*Fills ArrayLists. roomList and weaponList are used in method to place weapons at random*/
    public static void fillLists(){
        roomList.add("ballroom");
        roomList.add("conservatory");
        roomList.add("kitchen");
        roomList.add("dining room");
        roomList.add("lounge");
        roomList.add("hall");
        roomList.add("study");
        roomList.add("library");
        roomList.add("billiard room");

        weaponList.add("rope");
        weaponList.add("dagger");
        weaponList.add("wrench");
        weaponList.add("lead pipe");
        weaponList.add("revolver");
        weaponList.add("candlestick");

    }
    /*Method adds all elements of an arrayList to playerIcons arrayList*/
    public void addPlayerIcons(ArrayList<JLabel> icons){
        playerIcons.addAll(icons);
    }
    /*Method adds all elements of an arrayList to weaponIcons arrayList*/
    public void addWeaponIcons(ArrayList<JLabel> image){
        weaponIcons.addAll(image);
    }

    /*method moves weapons to rooms picked by user
    * param weapon is the weapon chosen from an inputDialogBox in InputOutput.java
     *param room is the room chosen from an inputDialogBox in InputOutput.java
     **/
    public static void moveWeapon(String weapon, String room){

        if(weapon.equalsIgnoreCase("rope")){
            index = 0;
        }
        else if(weapon.equalsIgnoreCase("dagger")){
            index = 1;
        }
        else if(weapon.equalsIgnoreCase("revolver")){
            index = 2;
        }
        else if(weapon.equalsIgnoreCase("lead pipe")){
            index = 3;
        }
        else if(weapon.equalsIgnoreCase("wrench")){
            index = 4;
        }
        else if(weapon.equalsIgnoreCase("candlestick")){
            index = 5;
        }
        switch(room){
            case "ballroom" :
                board.add(weaponIcons.get(index)).setBounds(312,68,25,25);
                break;
            case "conservatory" :
                board.add(weaponIcons.get(index)).setBounds(523,61,25,25);
                break;
            case "kitchen" :
                board.add(weaponIcons.get(index)).setBounds(58,65,25,25);
                break;
            case "dining room" :
                board.add(weaponIcons.get(index)).setBounds(65,255,25,25);
                break;
            case "lounge" :
                board.add(weaponIcons.get(index)).setBounds(65,576,25,25);
                break;
            case "hall" :
                board.add(weaponIcons.get(index)).setBounds(277,569,25,25);
                break;
            case "study" :
                board.add(weaponIcons.get(index)).setBounds(496,584,25,25);
                break;
            case "library" :
                board.add(weaponIcons.get(index)).setBounds(567,411,25,25);
                break;
            case "billiard room" :
                board.add(weaponIcons.get(index)).setBounds(484,300,25,25);
        }
    }
    /*handles player movement on the y axis
    * @Param character is the index of players selected character, corresponds to the index of that character in playerIcons arrayList
    * @Param y add or take away value on the y axis to move up or down
    * @Param player is the current player*/
    public static void movePlayerYAxis(int character, int y, int player){
            board.add(playerIcons.get(character)).setBounds(playerIcons.get(character).getX(),
                    playerIcons.get(character).getY() + y,
                    21, 21);

            PlayerManager.getPlayerArrayList().get(player).playerMoved();
            System.out.println(" " + playerIcons.get(character).getLocation());

    }
    /*handles player movement on the x axis
    * @Param character is the index of players selected character, corresponds to the index of that character in playerIcons arrayList
    * @Param x add or take away value on the x axis to move left or right
    * @Param player is the current player*/
    public static void movePlayerXAxis(int i, int x, int player){
            board.add(playerIcons.get(i)).setBounds(playerIcons.get(i).getX() + x,
                    playerIcons.get(i).getY(),
                    21, 21);

            PlayerManager.getPlayerArrayList().get(player).playerMoved();
            System.out.println(" " + playerIcons.get(i).getLocation());
    }
    /*method places weapons in random rooms at start of game
    * using random numbers to pick elements in roomList and weaponList
    * each iteration of the loop removes the elements from both arraylists
    * so they can't be picked again and decrements the bounds of random numbers*/
    public static void placeWeaponsRandom(){
        fillLists();
        int roomMax = 8;
        int weaponMax = 6;
        do{
            int min = 1;
            int weapon = randWeapon.nextInt((weaponMax - min) + 1);
            int room = randRoom.nextInt((roomMax - min) + 1);

            moveWeapon(weaponList.get(weapon), roomList.get(room));

            roomList.remove(room);
            weaponList.remove(weapon);
            roomMax--;
            weaponMax--;
    }while(weaponMax != 0);
        textOut.append("Weapons placed in random rooms\n" + "Board setup finished\n\n");
    }
    /*method to quit game. sets mainFrame visible false and then calls dispose(). */
    static void quit(){
        mainFrame.setVisible(false);
        Log.disposeLog();
        CardSetup.disposeMurderFrame();
        mainFrame.dispose();
    }
}
