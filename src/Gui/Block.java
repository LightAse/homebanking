package Gui;

import javax.swing.*;
import java.awt.*;

public class Block extends JPanel {

    private JLabel[] array_labels;
    private int lastPos;

    public Block(Color col,int x,int y,int width, int height) {

        this.setBackground(col);
        this.setBounds(x,y,width,height);

        array_labels = new JLabel[15];
        lastPos = 0;
    }

    public void addLabel(String text){

        if(checkpos()){

            JLabel label = new JLabel();
            label.setText(text);
            label.setVerticalAlignment(JLabel.TOP);
            label.setHorizontalAlignment(JLabel.RIGHT);
            label.setFont(new Font( "MV Boli",Font.PLAIN,20));
            this.add(label);
            array_labels[lastPos] = label;
            lastPos += 1;

        }

    }

    public JLabel getlabels(int pos) {
        return array_labels[pos];
    }

    private boolean checkpos(){

        if(lastPos >= array_labels.length){

            return false;

        }

        return true;

    }

}
