package group1.mips_simulator.FrontEnd;


import group1.mips_simulator.components.memParts.Cache;

import javax.swing.*;

public class CacheFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = 600;

    JFrame myFrame;
    JLabel label;

    public CacheFrame(int x, int y) {
        myFrame = makeNewCacheFrame();
        myFrame.setLocation(x, y);
    }

    JFrame makeNewCacheFrame() {
        myFrame = new JFrame("Cache Display");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(WIDTH, HEIGHT);

        myFrame.setVisible(true);
        return myFrame;
    }

    public void redrawCache(Cache cache) {
        // TODO:
    }
}
