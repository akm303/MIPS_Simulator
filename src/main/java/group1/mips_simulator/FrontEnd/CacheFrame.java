package group1.mips_simulator.FrontEnd;


import group1.mips_simulator.components.memParts.Cache;

import javax.swing.*;
import java.awt.*;

public class CacheFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = 600;

    JFrame myFrame;
    JLabel label = new JLabel();

    public CacheFrame(int x, int y) {
        myFrame = makeNewCacheFrame();
        myFrame.setLocation(x, y);
    }

    JFrame makeNewCacheFrame() {
        myFrame = new JFrame("Cache Display");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(WIDTH, HEIGHT);

        myFrame.setLayout(new GridLayout(1,1));
        myFrame.add(this.label);

        myFrame.setVisible(true);
        return myFrame;
    }

    public void redrawCache(Cache cache) {
        String newText = "<html>" + cache.getCacheString() + "</html>";
        newText = newText.replace("\n", " <br/> ");
        this.label.setText(newText);
    }
}
