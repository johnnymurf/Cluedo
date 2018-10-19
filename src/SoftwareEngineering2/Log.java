package SoftwareEngineering2;

import javax.swing.*;
import java.awt.*;

public class Log extends JPanel{
    private static JFrame logFrame;
    public static TextArea logOut;
    public static TextArea hiddenLog;
    private static JFrame hiddenFrame;

    Log(){
        hiddenFrame = new JFrame();
        hiddenLog = new TextArea();
        logFrame = new JFrame("Question Log");
        logOut = new TextArea();
        logOut.setEditable(false);
        hiddenLog.setEditable(false);
    }

    public static void createHiddenFrame(){
        hiddenFrame.getContentPane().add(hiddenLog);
        hiddenFrame.pack();
        hiddenFrame.setVisible(false);
    }

    public void showLog(){
        logFrame.setResizable(false);
        logFrame.setBackground(Color.LIGHT_GRAY);
        logFrame.getContentPane().add(logOut);
        logFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        logFrame.pack();
        logFrame.setLocationRelativeTo(null);
        logFrame.setVisible(false);
    }
 
    public static void logOrderOfPlayers(){
        for (int i = 0; i < PlayerManager.getPlayerArrayList().size(); i++){
            logOut.append("Player " + i  + " " + PlayerManager.getPlayerArrayList().get(i).getCharacterName() + "\n");
        }
    }

    public static void setLogVisible(){
        logOrderOfPlayers();
        logFrame.setVisible(true);
    }
    public static void disposeLog(){
        hiddenFrame.remove(hiddenLog);
        logFrame.remove(logOut);
        hiddenFrame.dispose();
        logFrame.dispose();
    }
}
