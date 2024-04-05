import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Tile {

    private BufferedImage image;
    private final String GRASS="platformimages/grass.png";
    private int tileType;
    public Tile(int type){

            this.setTileType(type);

    }
    public void setTileType(int tileType){
        this.tileType=tileType;
        if(this.tileType==1){
            image = loadImage(GRASS);

        }
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
    public BufferedImage getImage(){
        return image;
    }

}
