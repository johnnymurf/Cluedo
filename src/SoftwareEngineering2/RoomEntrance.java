package SoftwareEngineering2;

import javax.swing.*;

import static SoftwareEngineering2.Board.board;
/*RoomEntrance class handles the coordinates on the board which lead to a room*/
public class RoomEntrance{
    /*Method isRoomEntrance() takes the players current x and y coordinates and assigns them to a string xy
    * String xy is used as an argument in methods created for each rooms entrances
    * All the different roomEntrance methods are used as conditions in if and else if statements
    * roomEntrance methods compare xy to the coordinates of each room entrance and returns a boolean
    * @Param character is the character index set for the player depending on character choice
    * @Param player is the current players index from playerArrayList
    * @Param leftRoom is true if the player has just left a room
    * when a player leaves a room we don't want them asked to enter the same room*/
    public static void isRoomEntrance(int character, int player, boolean leftRoom){
        int x = Board.playerIcons.get(character).getX();
        int y = Board.playerIcons.get(character).getY();
        String xy = x + " " + y;

        if(diningRoomEntrance(xy) && !leftRoom){ // if current xy equals the x and y of an entrance leading to the dining room and player has not just left a room
            int diningEntry = JOptionPane.showConfirmDialog( // return value from dialog box assigned to int variable
                    Board.mainFrame,
                    "Would you like to enter the dining room?",
                    "Enter Dining Room?",
                    JOptionPane.YES_NO_OPTION);
            if(diningEntry == JOptionPane.YES_OPTION){ // if the user chose the yes option (return value for yes option == 0)
                if(character == 0) { // if players character index == 0. each index corresponds to a different playerIcon
                    board.add(Board.playerIcons.get(character)).setBounds(89, 324, 21, 21); // each playerIcon has its own tile in each room
                    setToDiningRoom(player);
                }
                else if(character == 1){
                    board.add(Board.playerIcons.get(character)).setBounds(112, 324, 21, 21);
                    setToDiningRoom(player);
                }
                else if(character == 2){
                    board.add(Board.playerIcons.get(character)).setBounds(135, 324, 21, 21);
                    setToDiningRoom(player);
                }
                else if(character == 3){
                    board.add(Board.playerIcons.get(character)).setBounds(135, 301, 21, 21);
                    setToDiningRoom(player);
                }
                else if(character == 4){
                    board.add(Board.playerIcons.get(character)).setBounds(112, 301, 21, 21);
                    setToDiningRoom(player);
                }
                else if(character == 5){
                    board.add(Board.playerIcons.get(character)).setBounds(89, 301, 21, 21);
                    setToDiningRoom(player);
                }
            }

        }
        else if(loungeEntrance(xy) && !leftRoom){
            int loungeEntry = JOptionPane.showConfirmDialog(
                    Board.mainFrame,
                    "Would you like to enter the lounge?",
                    "Enter Lounge?",
                    JOptionPane.YES_NO_OPTION);
            if(loungeEntry == JOptionPane.YES_OPTION){
                if(character == 0) {
                    board.add(Board.playerIcons.get(character)).setBounds(89, 531, 21, 21);
                    setToLounge(player);
                }
                else if(character == 1){
                    board.add(Board.playerIcons.get(character)).setBounds(112, 531, 21, 21);
                    setToLounge(player);
                }
                else if(character == 2){
                    board.add(Board.playerIcons.get(character)).setBounds(135, 531, 21, 21);
                    setToLounge(player);
                }
                else if(character == 3){
                    board.add(Board.playerIcons.get(character)).setBounds(135, 508, 21, 21);
                    setToLounge(player);
                }
                else if(character == 4){
                    board.add(Board.playerIcons.get(character)).setBounds(112, 508, 21, 21);
                    setToLounge(player);
                }
                else if(character == 5){
                    board.add(Board.playerIcons.get(character)).setBounds(89, 508, 21, 21);
                    setToLounge(player);
                }
            }
        }
        else if(hallEntrance(xy) && !leftRoom){
            int hallEntry = JOptionPane.showConfirmDialog(
                    Board.mainFrame,
                    "Would you like to enter the hall?",
                    "Enter Hall?",
                    JOptionPane.YES_NO_OPTION);
            if(hallEntry == JOptionPane.YES_OPTION){
                if(character == 0) {
                    board.add(Board.playerIcons.get(character)).setBounds(296, 485, 21, 21);
                    setToHall(player);
                }
                else if(character == 1){
                    board.add(Board.playerIcons.get(character)).setBounds(319, 485, 21, 21);
                    setToHall(player);
                }
                else if(character == 2){
                    board.add(Board.playerIcons.get(character)).setBounds(319, 508, 21, 21);
                    setToHall(player);
                }
                else if(character == 3){
                    board.add(Board.playerIcons.get(character)).setBounds(296, 531, 21, 21);
                    setToHall(player);
                }
                else if(character == 4){
                    board.add(Board.playerIcons.get(character)).setBounds(319, 531, 21, 21);
                    setToHall(player);
                }
                else if(character == 5){
                    board.add(Board.playerIcons.get(character)).setBounds(296, 508, 21, 21);
                    setToHall(player);
                }
            }
        }
        else if(basementEntrance(xy) && !leftRoom){ // The basement entrance is situated at a tile which is also a hall entrance
            Object[] options = {"Basement", // an array of objects lets the user choose from 3 options
                    "Hall",
                    "cancel"};
            int basementEntry = JOptionPane.showOptionDialog(Board.mainFrame,
                    "Would you like to enter the Hall or Basement?",
                    "Hall and Basement Entrance",
                    JOptionPane.YES_NO_CANCEL_OPTION, // an optionPane with yes no cancel option gives us three buttons to choose instead of yes and no we have Basement and Hall
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
            if (basementEntry == JOptionPane.YES_OPTION) {
                board.add(Board.playerIcons.get(character)).setBounds(319, 324, 21, 21);
                setToBasement(player);
            }
            else if (basementEntry == JOptionPane.NO_OPTION) {
                board.add(Board.playerIcons.get(character)).setBounds(296, 508, 21, 21); // MIGHT HAVE BUG HERE
                setToHall(player);
            }
        }
        else if(studyEntrance(xy) && !leftRoom){
            int studyEntry = JOptionPane.showConfirmDialog(
                    Board.mainFrame,
                    "Would you like to enter the study?",
                    "Enter Study?",
                    JOptionPane.YES_NO_OPTION);
            if(studyEntry == JOptionPane.YES_OPTION){
                if(character == 0) {
                    board.add(Board.playerIcons.get(character)).setBounds(480, 554, 21, 21);
                    setToStudy(player);
                }
                else if(character == 1){
                    board.add(Board.playerIcons.get(character)).setBounds(503, 554, 21, 21);
                    setToStudy(player);
                }
                else if(character == 2){
                    board.add(Board.playerIcons.get(character)).setBounds(526, 554, 21, 21);
                    setToStudy(player);
                }
                else if(character == 3){
                    board.add(Board.playerIcons.get(character)).setBounds(526, 531, 21, 21);
                    setToStudy(player);
                }
                else if(character == 4){
                    board.add(Board.playerIcons.get(character)).setBounds(480, 531, 21, 21);
                    setToStudy(player);
                }
                else if(character == 5){
                    board.add(Board.playerIcons.get(character)).setBounds(503, 531, 21, 21);
                    setToStudy(player);
                }
            }
        }
        else if(libraryEntrance(xy) && !leftRoom){
            int libraryEntry = JOptionPane.showConfirmDialog(
                    Board.mainFrame,
                    "Would you like to enter the library?",
                    "Enter Library?",
                    JOptionPane.YES_NO_OPTION);
            if(libraryEntry == JOptionPane.YES_OPTION){
                if(character == 0) {
                    board.add(Board.playerIcons.get(character)).setBounds(480, 416, 21, 21);
                    setToLibrary(player);
                }
                else if(character == 1){
                    board.add(Board.playerIcons.get(character)).setBounds(503, 416, 21, 21);
                    setToLibrary(player);
                }
                else if(character == 2){
                    board.add(Board.playerIcons.get(character)).setBounds(526, 416, 21, 21);
                    setToLibrary(player);
                }
                else if(character == 3){
                    board.add(Board.playerIcons.get(character)).setBounds(526, 393, 21, 21);
                    setToLibrary(player);
                }
                else if(character == 4){
                    board.add(Board.playerIcons.get(character)).setBounds(480, 393, 21, 21);
                    setToLibrary(player);
                }
                else if(character == 5){
                    board.add(Board.playerIcons.get(character)).setBounds(503, 393, 21, 21);
                    setToLibrary(player);
                }
            }
        }
        else if(billiardEntrance(xy) && !leftRoom){
            int billiardEntry = JOptionPane.showConfirmDialog(
                    Board.mainFrame,
                    "Would you like to enter the billiard room?",
                    "Enter Billiard Room?",
                    JOptionPane.YES_NO_OPTION);
            if(billiardEntry == JOptionPane.YES_OPTION){
                if(character == 0) {
                    board.add(Board.playerIcons.get(character)).setBounds(503, 278, 21, 21);
                    setToBilliard(player);
                }
                else if(character == 1){
                    board.add(Board.playerIcons.get(character)).setBounds(526, 278, 21, 21);
                    setToBilliard(player);
                }
                else if(character == 2){
                    board.add(Board.playerIcons.get(character)).setBounds(549, 278, 21, 21);
                    setToBilliard(player);
                }
                else if(character == 3){
                    board.add(Board.playerIcons.get(character)).setBounds(549, 255, 21, 21);
                    setToBilliard(player);
                }
                else if(character == 4){
                    board.add(Board.playerIcons.get(character)).setBounds(503, 255, 21, 21);
                    setToBilliard(player);
                }
                else if(character == 5){
                    board.add(Board.playerIcons.get(character)).setBounds(526, 255, 21, 21);
                    setToBilliard(player);
                }
            }
        }
        else if(conservatoryEntrance(xy) && !leftRoom){
            int conservatoryEntry = JOptionPane.showConfirmDialog(
                    Board.mainFrame,
                    "Would you like to enter the conservatory?",
                    "Enter Conservatory?",
                    JOptionPane.YES_NO_OPTION);
            if(conservatoryEntry == JOptionPane.YES_OPTION){
                if(character == 0) {
                    board.add(Board.playerIcons.get(character)).setBounds(503, 94, 21, 21);
                    setToConservatory(player);
                }
                else if(character == 1){
                    board.add(Board.playerIcons.get(character)).setBounds(526, 94, 21, 21);
                    setToConservatory(player);
                }
                else if(character == 2){
                    board.add(Board.playerIcons.get(character)).setBounds(549, 94, 21, 21);
                    setToConservatory(player);
                }
                else if(character == 3){
                    board.add(Board.playerIcons.get(character)).setBounds(549, 71, 21, 21);
                    setToConservatory(player);
                }
                else if(character == 4){
                    board.add(Board.playerIcons.get(character)).setBounds(526, 71, 21, 21);
                    setToConservatory(player);
                }
                else if(character == 5){
                    board.add(Board.playerIcons.get(character)).setBounds(503, 71, 21, 21);
                    setToConservatory(player);
                }
            }
        }
        else if(ballroomEntrance(xy) && !leftRoom){
            int ballRoomEntry = JOptionPane.showConfirmDialog(
                    Board.mainFrame,
                    "Would you like to enter the ball room?",
                    "Enter Ball Room?",
                    JOptionPane.YES_NO_OPTION);
            if(ballRoomEntry == JOptionPane.YES_OPTION){
                if(character == 0) {
                    board.add(Board.playerIcons.get(character)).setBounds(273, 117, 21, 21);
                    setToBallRoom(player);
                }
                else if(character == 1){
                    board.add(Board.playerIcons.get(character)).setBounds(296, 117, 21, 21);
                    setToBallRoom(player);
                }
                else if(character == 2){
                    board.add(Board.playerIcons.get(character)).setBounds(319, 117, 21, 21);
                    setToBallRoom(player);
                }
                else if(character == 3){
                    board.add(Board.playerIcons.get(character)).setBounds(342, 117, 21, 21);
                    setToBallRoom(player);
                }
                else if(character == 4){
                    board.add(Board.playerIcons.get(character)).setBounds(319, 94, 21, 21);
                    setToBallRoom(player);
                }
                else if(character == 5){
                    board.add(Board.playerIcons.get(character)).setBounds(296, 94, 21, 21);
                    setToBallRoom(player);
                }
            }
        }
        else if(kitchenEntrance(xy) && !leftRoom){
            int kitchenEntry = JOptionPane.showConfirmDialog(
                    Board.mainFrame,
                    "Would you like to enter the kitchen?",
                    "Enter Kitchen?",
                    JOptionPane.YES_NO_OPTION);
            if(kitchenEntry == JOptionPane.YES_OPTION){
                if(character == 0) {
                    board.add(Board.playerIcons.get(character)).setBounds(66, 117, 21, 21);
                    setToKitchen(player);
                }
                else if(character == 1){
                    board.add(Board.playerIcons.get(character)).setBounds(89, 117, 21, 21);
                    setToKitchen(player);
                }
                else if(character == 2){
                    board.add(Board.playerIcons.get(character)).setBounds(112, 117, 21, 21);
                    setToKitchen(player);
                }
                else if(character == 3){
                    board.add(Board.playerIcons.get(character)).setBounds(112, 94, 21, 21);
                    setToKitchen(player);
                }
                else if(character == 4){
                    board.add(Board.playerIcons.get(character)).setBounds(89, 94, 21, 21);
                    setToKitchen(player);
                }
                else if(character == 5){
                    board.add(Board.playerIcons.get(character)).setBounds(66, 94, 21, 21);
                    setToKitchen(player);
                }
            }
        }
    }
    /*Tests string xy against two strings representing the xy of entrance tiles for dining room
    * @Param xy string representation of players current tile coordinates
    * @Return true if xy equals either string
    * the dining room has two entrances so method tests for either one
    * all methods below are basically the same with different number of entrances*/
    private static boolean diningRoomEntrance(String xy){
        return xy.equalsIgnoreCase("227 301") || xy.equalsIgnoreCase("181 393");
    }
    /*Tests string xy against a string representing the xy of the entrance tile for the lounge
    * @Param xy string representation of players current tile coordinates
    * @Return true if xy equals string*/
    private static boolean loungeEntrance(String xy){
        return xy.equalsIgnoreCase("181 439");
    }
    /*Tests string xy against two strings representing the xy of entrance tiles for the hall
    * @Param xy string representation of players current tile coordinates
    * @Return true if xy equals either string*/
    private static boolean hallEntrance(String xy){
        return xy.equalsIgnoreCase("296 416") || xy.equalsIgnoreCase("388 485");
    }
    /*Tests string xy against a string representing the xy of the entrance tile for the basement
    * @Param xy string representation of players current tile coordinates
    * @Return true if xy equals string*/
    private static boolean basementEntrance(String xy){
        return xy.equalsIgnoreCase("319 416");
    }

    private static boolean studyEntrance(String xy){
        return xy.equalsIgnoreCase("434 485");
    }

    private static boolean libraryEntrance(String xy){
        return xy.equalsIgnoreCase("411 393") || xy.equalsIgnoreCase("503 324");
    }

    private static boolean billiardEntrance(String xy){
        return xy.equalsIgnoreCase("549 324") || xy.equalsIgnoreCase("434 232");
    }

    private static boolean conservatoryEntrance(String xy){
        return xy.equalsIgnoreCase("457 140");
    }

    private static boolean ballroomEntrance(String xy){
        return xy.equalsIgnoreCase("411 140") || xy.equalsIgnoreCase("365 209") || xy.equalsIgnoreCase("250 209") || xy.equalsIgnoreCase("204 140");
    }

    private static boolean kitchenEntrance(String xy){
        return xy.equalsIgnoreCase("135 186");
    }
    /*sets player attributes on entry to normal room
    * error and other messages depend on what player attributes have been set
    * @Param player is the index of current player*/
    private static void setRoomEntryAttributes(int player){
        PlayerManager.getPlayerArrayList().get(player).setInRoom(true);
        PlayerManager.getPlayerArrayList().get(player).setJustEntered(true);
        PlayerManager.getPlayerArrayList().get(player).setInCorridor(false);
        PlayerManager.getPlayerArrayList().get(player).setMoves(0);
    }
    /*Sets player attributes on entry to a room with a secret passage
    * when secret passage is set to true player wil be notified at the start of their next turn they have the option to use a passage
    * @Param player is the index of current player*/
    private static void setRoomEntryAttributesSecret(int player){
        PlayerManager.getPlayerArrayList().get(player).setInRoom(true);
        PlayerManager.getPlayerArrayList().get(player).setJustEntered(true);
        PlayerManager.getPlayerArrayList().get(player).setSecretPassage(true);
        PlayerManager.getPlayerArrayList().get(player).setInCorridor(false);
        PlayerManager.getPlayerArrayList().get(player).setMoves(0);
    }
    /*called when a player enters the dining room
    * calls setRoomEntryAttributes
    * sets current player room attribute to dining room*/
    public static void setToDiningRoom(int player){
        setRoomEntryAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("dining room");
    }

    public static void setToLounge(int player){
        setRoomEntryAttributesSecret(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("lounge");
    }

    public static void setToHall(int player){
        setRoomEntryAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("hall");
    }

    public static void setToBasement(int player){
        setRoomEntryAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("basement");
    }

    public static void setToStudy(int player){
        setRoomEntryAttributesSecret(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("study");
    }

    public static void setToLibrary(int player){
        setRoomEntryAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("library");
    }

    public static void setToBilliard(int player){
        setRoomEntryAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("billiard");
    }

    public static void setToConservatory(int player){
        setRoomEntryAttributesSecret(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("conservatory");
    }

    public static void setToBallRoom(int player){
        setRoomEntryAttributes(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("ballroom");
    }

    public static void setToKitchen(int player){
        setRoomEntryAttributesSecret(player);
        PlayerManager.getPlayerArrayList().get(player).setCurrentRoom("kitchen");
    }
}