import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class Panel extends JPanel implements KeyListener {

    private BufferedImage image;
    private Map map;
    private double xx;
    private double yy;
    private int[][] test;
    private boolean gameOver;
    private int endRow;
    private int endCol;

    public Panel()  {
        this.addKeyListener(this);
        this.setFocusable(true);
        map=new Map();
        xx=map.getPlayer().getX();
        yy=map.getPlayer().getY();
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

        int endRow=map.getEndRow();
        int endCol=map.getEndCol();
        for(int row=0;row<map.getMap().length;row++){
            for(int col=0;col<map.getMap()[0].length;col++){
                Tile t=map.getMap()[row][col];
                g.drawImage(t.getImage(),x,y,null);
                x+=20;
            }
            if(!gameOver) {
                g.drawImage(map.getPlayer().getImage(), (int) xx, (int) yy, null);
                g.drawRect((int) xx, (int) yy,20,20);

                if(test[(int) (Math.floor((float) yy /20)+1)][(int) Math.floor((float) xx /20)]==0 || test[(int) (Math.floor((float) yy /20) +1)][(int) Math.floor((float) xx /20)]==3){
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
                    if(test[(int) (Math.floor((float) yy /20) +1)][(int) Math.floor((float) xx /20)]==0 || test[(int) (Math.floor((float) yy /20) +1)][(int) Math.floor((float) xx /20)]==3){
                        yy+=0.005;
                    }
                    else{
                        map.getPlayer().setFalling(false);
                    }
                }
                if(Math.round(yy/20)==endRow && Math.round(xx/20)==endCol){
                    gameOver=true;
                }
            }
            else{
                g.setFont(new Font("Courier New",Font.BOLD,100));
                g.drawString("YOU WINNED",660,540);

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
        if(!gameOver){

            if(key=='w'){
                if(!map.getPlayer().isFalling()){
                if(yy!=0){
                    if(test[(int) (Math.ceil((float) yy /20)-1)][(int) Math.ceil((float) xx /20)]==0 || test[(int) (Math.ceil((float) yy /20)-1)][(int) Math.ceil((float) xx /20)]==3) {
                        map.getPlayer().setJumping(true);
                    }
                    else{
                        map.getPlayer().setJumping(false);

                    }
                        if(map.getPlayer().isJumping()){
                            for(int i=0;i<6000;i++){
                                yy-=0.01;
                                if(test[(int) (Math.ceil((float) yy /20)-1)][(int) Math.round((float) xx /20)]!=0){
                                    map.getPlayer().setJumping(false);
                                    i=5999;
                                }

                                if(i==5999){
                                    map.getPlayer().setJumping(false);
                                }
                        }

                        }
                    }
            }
            }
            if(key=='a'){
                if(xx!=0){
                    if(test[(int) (Math.floor((float) yy /20))][(int) Math.floor((float) xx /20)-1]==0 || test[(int) (Math.floor((float) yy /20))][(int) Math.floor((float) xx /20)-1]==3){
                        xx-=20;}}
            }
            if(key == 's'){
                if(yy!=1080){
                    if(test[(int) (Math.floor((float) yy /20)+1)][(int) Math.floor((float) xx /20)]==0 || test[(int) (Math.floor((float) yy /20)+1)][(int) Math.floor((float) xx /20)]==3){
                        yy+=20;}}
            }
            if(key == 'd'){
                if(xx!=1900){
                    if(test[(int) (Math.floor((float) yy /20))][(int) Math.floor((float) xx /20)+1]==0 || test[(int) (Math.floor((float) yy /20))][(int) Math.floor((float) xx /20)+1]==3){
                        xx+=20;}}
            }


        }

    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    public int getEndRow() {
        return endRow;
    }
    public int getEndCol() {
        return endCol;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }
}
