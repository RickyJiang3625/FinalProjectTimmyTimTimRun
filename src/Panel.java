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
    private double gravitySpeed=0.001;
    private boolean collisionRight;
    private boolean collisionLeft;
    private int startX;
    private int startY;
    private boolean leftNotFall;

    public Panel()  {
        this.addKeyListener(this);
        this.setFocusable(true);
        map=new Map();
        xx=map.getPlayer().getX();
        yy=map.getPlayer().getY();
        startY=map.getPlayer().getY();
        startX=map.getPlayer().getX();
        test=map.getWorldData();
        collisionRight=false;
        collisionLeft=false;
        leftNotFall=false;
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

                //System.out.println("min "+minX);
                //System.out.println("x "+xx);
                //System.out.println("max "+ maxX);
                    if(test[(int) (Math.floor((float) yy /20)+1)][(int) xx /20+1  ]==1 || test[(int) (Math.floor((float) yy /20) +1)][(int) xx /20 +1]==2){
                        map.getPlayer().setFalling(false);
                    }

                    else if(test[(int) (Math.floor((float) yy /20)+1)][(int) xx /20 ]==0 || test[(int) (Math.floor((float) yy /20) +1)][(int) xx /20]==3 || test[(int) (Math.floor((float) yy /20) +1)][(int) xx /20]==4 ){


                //System.out.println((int) xx/20);
                //System.out.println("X "+xx/20);
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
                    if(test[(int) (Math.floor((float) yy /20) +1)][(int) Math.floor((float) xx /20)]==0 || test[(int) (Math.floor((float) yy /20) +1)][(int) Math.floor((float) xx /20)]==3 || test[(int) (Math.floor((float) yy /20) +1)][(int) xx /20]==4){
                        yy+=gravitySpeed;
                        gravitySpeed+=0.0001;
                    }
                    if(test[(int) (Math.floor((float) yy /20) +1)][(int) Math.floor((float) xx /20)]==1 || test[(int) (Math.floor((float) yy /20) +1)][(int) Math.floor((float) xx /20)]==2){
                        gravitySpeed=0.0001;
                        map.getPlayer().setFalling(false);

                    }
                }
                if(test[(int) yy/20][(int) xx/20]==4){
                    xx=startX;
                    yy=startY;
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
        if(map.getPlayer().isMovingRight()){
            moveRight(3);
        }
        if(map.getPlayer().isMovingLeft()){
             moveLeft(3);
        }


    }







    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int howMany = (int) yy / 20;
        if (!gameOver) {

            if (key == 'w') {
                if (!map.getPlayer().isFalling()) {
                    if (yy != 0) {
                        if (Math.ceil((float) yy / 20 - 1) < 0) {

                            for (int i = 0; i < howMany * 2000; i++) {
                                yy -= 0.01;
                                if (test[(int) (Math.ceil((float) yy / 20) - 1)][Math.round((float) xx / 20)] != 0) {
                                    map.getPlayer().setJumping(false);
                                    i = howMany * 2000 - 1;
                                }
                                if (i == howMany * 2000 - 1) {
                                    map.getPlayer().setFalling(true);
                                }
                            }
                        }
                        else {
                            if (test[(int) (Math.ceil((float) yy / 20) - 1)][(int) Math.ceil((float) xx / 20)] == 0 || test[(int) (Math.ceil((float) yy / 20) - 1)][(int) Math.ceil((float) xx / 20)] == 3 || test[(int) (Math.ceil((float) yy / 20) - 1)][(int) Math.ceil((float) xx / 20)] == 4) {
                                map.getPlayer().setJumping(true);
                            } else {
                                map.getPlayer().setJumping(false);

                            }
                            if (map.getPlayer().isJumping()) {
                                for (int i = 0; i < 8000; i++) {
                                    yy -= 0.01;
                                    if (test[(int) (Math.ceil((float) yy / 20) - 1)][Math.round((float) xx / 20)] != 0) {
                                        map.getPlayer().setJumping(false);
                                        i = 7999;
                                    }

                                    if (i == 7999) {
                                        map.getPlayer().setJumping(false);
                                    }
                                }

                            }
                        }
                    }
                }
            }

            if (key == 'a') {
                if (xx != 0) {
                    if (test[(int) (Math.floor((float) yy / 20))][(int) Math.floor((float) xx / 20) - 1] == 0 || test[(int) (Math.floor((float) yy / 20))][(int) Math.floor((float) xx / 20) - 1] == 3 || test[(int) (Math.floor((float) yy / 20))][(int) Math.floor((float) xx / 20) - 1] == 4) {
                        map.getPlayer().setMovingLeft(true);
                    }
                }
            }

            if (key == 's') {
                if (yy != 1080) {
                    if (test[(int) (Math.floor((float) yy / 20) + 1)][(int) Math.floor((float) xx / 20)] == 0 || test[(int) (Math.floor((float) yy / 20) + 1)][(int) Math.floor((float) xx / 20)] == 3) {
                        yy += 20;
                    }
                }
            }
            if (key == 'd') {
                if (xx != 1900) {
                    if (test[(int) (Math.floor((float) yy / 20))][(int) Math.floor((float) xx / 20) + 1] == 0 || test[(int) (Math.floor((float) yy / 20))][(int) Math.floor((float) xx / 20) + 1] == 3 ||test[(int) (Math.floor((float) yy / 20))][(int) Math.floor((float) xx / 20) + 1] == 4 ) {
                        map.getPlayer().setMovingRight(true);
                    }
                }
            }


        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        char key=e.getKeyChar();
        if(key=='a'){
            map.getPlayer().setMovingLeft(false);
        }
        if(key=='d'){
            map.getPlayer().setMovingRight(false);
        }
    }
    public void moveRight(double distance){
        if(xx!=1900 && !gameOver) {

            if (test[(int) (Math.floor((float) yy / 20))][(int) Math.round((float) xx / 20) + 1] == 0 || test[(int) (Math.floor((float) yy / 20))][(int) Math.round((float) xx / 20) + 1] == 3 || test[(int) (Math.floor((float) yy / 20))][(int) Math.round((float) xx / 20) + 1] == 4) {
                xx += distance;

                collisionRight = false;

            } else {
                double nextX = (xx + (20 - xx % 20));
                if (!collisionRight) {
                    if (test[(int) (Math.floor((float) yy / 20))][(int) Math.round((float) xx / 20) + 1] == 1 || test[(int) (Math.floor((float) yy / 20))][(int) Math.round((float) xx / 20) + 1] == 2) {
                        xx = nextX;
                        collisionRight = true;

                    }
                }
            }
            if(collisionRight ){
                if ((map.getPlayer().isJumping() || map.getPlayer().isFalling()) && (test[(int) (Math.floor((float) yy / 20))][(int) Math.round((float) xx / 20) + 1] == 0 || test[(int) (Math.floor((float) yy / 20))][(int) Math.round((float) xx / 20) + 1] == 3) ) {
                xx += distance;
            }
            }
        }
    }
    public void moveLeft(double distance){
        if(xx!=0 && !gameOver){
                if(test[(int) (Math.floor((float) yy /20))][(int) Math.round((float) xx /20)-1]==0 || test[(int) (Math.floor((float) yy /20))][(int) Math.round((float) xx /20)-1]==3 || test[(int) (Math.floor((float) yy /20))][(int) Math.round((float) xx /20)-1]==4){
                    xx-=distance;
                    collisionLeft=false;

                }
                else{
                    double nextX=  (xx-(xx%20));
                    if(!collisionLeft){
                        if(test[(int) (Math.floor((float) yy /20))][(int) Math.round((float) xx /20)-1]==1 || test[(int) (Math.floor((float) yy /20))][(int) Math.round((float) xx /20)-1]==2){
                            xx=nextX;
                            collisionLeft=true;

                        }
                    }
                }
                if(collisionLeft) {
                    if (map.getPlayer().isJumping() && test[(int) (Math.floor((float) yy / 20))][(int) Math.round((float) xx / 20) - 1] == 0 || test[(int) (Math.floor((float) yy / 20))][(int) Math.round((float) xx / 20) - 1] == 3) {
                        xx -= distance;
                    }
                }
            }
        }
    }

