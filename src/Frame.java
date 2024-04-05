import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Frame extends JFrame implements Runnable {
    private Panel p;

    public Frame(String display) {
        super(display);

        int frameWidth = 500;
        int frameHeight = 500;
        p = new Panel();
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(0, 0);
        this.setVisible(true);

        startThread();

    }
    public void startThread() {

        Thread windowThread = new Thread(this);
        windowThread.start();
    }

    public void run() {
        while(true){
            p.repaint();
        }


    }
}
