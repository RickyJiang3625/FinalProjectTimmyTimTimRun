import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel {
    private BufferedImage image;
    private Map map;

    public Panel()
    {
    map=new Map();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(new Color(0,255,255));
        int x=0;
        int y=0;
        for(int row=0;row<map.getMap().length;row++){
            for(int col=0;col<map.getMap()[0].length;col++){
                Tile t=map.getMap()[row][col];
                g.drawImage(t.getImage(),x,y,null);
                x+=150;

            }
            x=0;
            y+=200;
        }

    }


}
