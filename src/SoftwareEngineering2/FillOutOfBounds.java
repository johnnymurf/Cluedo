package SoftwareEngineering2;

import java.io.*;
import java.util.Scanner;
/*class reads a text file and places contents in a string array
* outOfBounds array is used in FetchMoveAndCheck class*/
public class FillOutOfBounds {
    private static String[] outOfBounds; // string array to hold outOfBounds coordinates
    private static final int INDEX = 179; // size of file
    private InputStream stream = getClass().getResourceAsStream(PathConstants.NO_MOVE_PATH);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

    FillOutOfBounds(){
        outOfBounds = new String[INDEX];
        fillOutOfBoundsArray();
    }

    public static int getINDEX() {
        return INDEX;
    }

    public static String[] getOutOfBounds() {
        return outOfBounds;
    }
    /*uses a BufferedReader to read a file line by line and store each line in outOfBounds array*/
    private void fillOutOfBoundsArray(){
        String line;
        int i = 0;
        try {
            while((line = reader.readLine()) != null && i <= INDEX){
                outOfBounds[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
