import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public Panel()
    {

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(0,0,100,50);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("TIME",20,30);


    }


}
