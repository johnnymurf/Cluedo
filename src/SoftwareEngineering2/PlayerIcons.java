package SoftwareEngineering2;


import javax.swing.*;

import java.io.IOException;
import java.util.ArrayList;
/*adds image files to an arrayList of JLabels as imageIcons*/
class PlayerIcons {
    private ArrayList<JLabel> playerIconJLabels;

    PlayerIcons()  {
        playerIconJLabels = new ArrayList<>();
        addPlayers();
    }

    private void addPlayers(){
        playerIconJLabels.add(new JLabel(new ImageIcon(this.getClass().getResource(PathConstants.GREEN_PATH))));
        playerIconJLabels.add(new JLabel(new ImageIcon(this.getClass().getResource(PathConstants.YELLOW_PATH))));
        playerIconJLabels.add(new JLabel(new ImageIcon(this.getClass().getResource(PathConstants.BLUE_PATH))));
        playerIconJLabels.add(new JLabel(new ImageIcon(this.getClass().getResource(PathConstants.PURPLE_PATH))));
        playerIconJLabels.add(new JLabel(new ImageIcon(this.getClass().getResource(PathConstants.RED_PATH))));
        playerIconJLabels.add(new JLabel(new ImageIcon(this.getClass().getResource(PathConstants.WHITE_PATH))));
    }

    ArrayList<JLabel> getPlayerIconJLabels() {
        return playerIconJLabels;
    }
}