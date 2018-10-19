package SoftwareEngineering2;

import java.util.ArrayList;

/*Class prefetches the players selected move and checks the coordinates against a pre filled array that holds all x and y that is outOfBounds
* See FillOutOfBounds Class*/
public class FetchMoveAndCheck{
    private static final int INDEX = FillOutOfBounds.getINDEX(); // INDEX is length of file (179)
    private static boolean canMove = false;
    private static int count = 0; // set to 1 if attempted move is illegal
    private static int count2 = 0; // set to 1 if tile is occupied
    FillOutOfBounds a = new FillOutOfBounds(); //

    /*movedAllowed() method called in InputOutput class, checks player selected move before any move is made
    * if selected move is illegal or tile is occupied error message is shown
    * @Param move is the input from textField in InputOutput w = up, s = down etc.
    * @Param i is the index of playerIcons
    * @Return canMove if true player moves to selected square, if false print error message*/
    public boolean moveAllowed(String move, int i){
        switch(move){
            case "w" :
                int x = Board.playerIcons.get(i).getX();
                int y = Board.playerIcons.get(i).getY() - 23;
                String xy = x + " " + y;
                System.out.println(xy);
                canMove = (checkXY(xy) == 0 && checkXYForPlayer(xy) == 0);
                break;

            case "a" :
                x = Board.playerIcons.get(i).getX() - 23;
                y = Board.playerIcons.get(i).getY();
                xy = x + " " + y;
                System.out.println(xy);
                canMove = (checkXY(xy) == 0 && checkXYForPlayer(xy) == 0);
                break;
 
            case "s" :
                x = Board.playerIcons.get(i).getX();
                y = Board.playerIcons.get(i).getY() + 23;
                xy = x + " " + y;
                System.out.println(xy);
                canMove = (checkXY(xy) == 0 && checkXYForPlayer(xy) == 0);
            break;

            case "d" :
                x = Board.playerIcons.get(i).getX() + 23;
                y = Board.playerIcons.get(i).getY();
                xy = x + " " + y;
                System.out.println(xy);
                canMove = (checkXY(xy) == 0 && checkXYForPlayer(xy) == 0);
            break;
        }
        count = 0; // count reset to 0 after every call to move allowed
        count2 = 0;
        return canMove;
    }
    /*checkXY checks if a string representing the players chosen move is legal or not
    * @Param xy is a string representation of the x y of the next move
    * @Return count if Param xy equals any element of outOfBoundsArray count == 1
    * if count != 0 then boolean canMove is set to false and player receives an error message*/
    private static int checkXY(String xy){
        for(int i = 0; i < INDEX; i++){
            if(xy.equals(FillOutOfBounds.getOutOfBounds()[i])){
                count = 1;
            }
        }
        return count;
    }
    /*checks if a given string representation of the x y coordinates of the players attempted move
     is present in occupiedTile arraylist, which is a static arrayList belonging to PLayerManager class
     @Param xy is the x and y coordinates of the players attempted move eg. 35 123
     @return count2 if occupiedTile is not empty and xy matches an element in occupiedTile count2 set to 1 which sets canMove to false
     otherwise count2 stays 0
     @Return 0 if occupiedTile isEmpty return 0 canMove is not effected*/
    private static int checkXYForPlayer(String xy){
        if(!PlayerManager.getOccupiedTile().isEmpty()) {
            for (int i = 0; i < PlayerManager.getOccupiedTile().size(); i++) {
                if (xy.equals(PlayerManager.getOccupiedTile().get(i))) {
                    count2 = 1;
                }
            }
            return count2;
        }
        return 0;
    }
}