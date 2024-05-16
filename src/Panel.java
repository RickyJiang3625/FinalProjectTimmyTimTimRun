import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Panel extends JPanel implements KeyListener {

    private BufferedImage image;
    private Map map;
    private int xx;
    private int yy;
    private int[][] test;
    private boolean gameOver;
    public Panel()  {
        this.addKeyListener(this);
        this.setFocusable(true);
        map=new Map();
        xx=map.getPlayer().getCol()*20;
        yy=map.getPlayer().getRow()*20;
        test=map.getWorldData();
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
        int x=0;
        int y=0;
        gameOver=false;





        for(int row=0;row<map.getMap().length;row++){
            for(int col=0;col<map.getMap()[0].length;col++){
                Tile t=map.getMap()[row][col];
                g.drawImage(t.getImage(),x,y,null);
                x+=20;
            }
            if(!gameOver) {
                g.drawImage(map.getPlayer().getImage(), xx, yy, null);

                if(test[(int) (Math.ceil((float) yy /20)+1)][(int) Math.ceil((float) xx /20)]==0){
                    if(map.getPlayer().isJumping()){
                        map.getPlayer().setFalling(false);
                    }
                    else{
                    map.getPlayer().setFalling(true);}
                }
                else{
                    map.getPlayer().setFalling(false);
                    map.getPlayer().setJumping(false);
                }
                if(map.getPlayer().isFalling()){
                    if(test[(int) (Math.ceil((float) yy /20)+1)][(int) Math.ceil((float) xx /20)]==0){
                    yy+=1;
                    }
                    else{
                        map.getPlayer().setFalling(false);
                    }
                }
            }
            x=0;
            y+=20;
        }


    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key= e.getKeyChar();
        if(key=='w'){
            if(test[Math.round((float) yy /20)-1][Math.round((float) xx /20)]==0){
                map.getPlayer().setJumping(true);
                yy-=20;
            }
        }
        if(key=='a'){
            if(test[Math.round((float) yy /20)][Math.round((float) xx /20)-1]==0){
            xx-=20;}
        }
        if(key == 's'){
            if(test[Math.round((float) yy /20)+1][Math.round((float) xx /20)]==0){
            yy+=20;}
        }
        if(key == 'd'){
            if(test[Math.round((float) yy /20)][Math.round((float) xx /20)+1]==0){
            xx+=20;}
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

}
