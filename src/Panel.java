import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel implements KeyListener {

    private BufferedImage image;
    private Map map;

    public Panel()  {
        this.addKeyListener(this);
        this.setFocusable(true);
        map=new Map();
    }

    public BufferedImage loadImage(String fileName) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(fileName));
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        image=loadImage("platformimages/sky.jpg");
        g.drawImage(image,0,0,1920,1080,null);
        int x=0;
        int y=0;

        int playerX=map.getPlayer().getCol()/60;
        int playerY=map.getPlayer().getRow()/60;



        g.drawImage(map.getPlayer().getImage(),playerX*60,playerY*60,null);
        for(int row=0;row<map.getMap().length;row++){
            for(int col=0;col<map.getMap()[0].length;col++){
                Tile t=map.getMap()[row][col];
                g.drawImage(t.getImage(),x,y,null);
                x+=60;
            if(playerX==col && playerY==row){
                g.drawImage(map.getPlayer().getImage(),playerX*60,playerY*60,null);
            }
            }

            x=0;
            y+=60;
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key= e.getKeyChar();
        if(key=='w'){
            map.movePlayer("W");
        }
        if(key=='a'){
            map.movePlayer("A");
        }
        if(key == 's'){
            map.movePlayer("S");
        }
        if(key == 'd'){
            map.movePlayer("D");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}
