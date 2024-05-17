import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Tile {

    private BufferedImage image;
    private final String GRASS="platformimages/grass.png";
    private final String DIRT="platformimages/dirt.png";
    private final String SKY="platformimages/tempsky.png";
    private final String END="spriteimages/tempEnd.png";
    private int tileType;
    private String[] pngs={SKY,DIRT,GRASS,END};
    public Tile(int type){

        this.setTileType(type);
    }
    public void setTileType(int tileType){
        image=loadImage(pngs[tileType]);
        image=resize(image,20,20);
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
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }
}
