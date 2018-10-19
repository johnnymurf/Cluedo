package SoftwareEngineering2;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

import static SoftwareEngineering2.PathConstants.*;
/*class sets up an arrayList of weapon icon labels*/
public class Weapons extends JLabel{
    private ArrayList<JLabel> labels;
    private final int SIZE = 6;
    private final String[] PATHS = new String[] {ROPE_PATH, DAGGER_PATH, REVOLVER_PATH, LEADPIPE_PATH, WRENCH_PATH, CANDLESTICK_PATH};

    Weapons() throws IOException{
        labels = new ArrayList<>();
        weaponLabels();
    }
    /*method weaponLabels() initializes a new JLabel with an imageIcon
     and adds to labels ArrayList on each iteration*/
    private void weaponLabels() throws IOException{
        int i = 0;
        while(i < SIZE) {
            labels.add(new JLabel(new ImageIcon(this.getClass().getResource(PATHS[i]))));
            i++;
        }
    }
    public ArrayList<JLabel> getLabels(){
        return labels;
    }
}
