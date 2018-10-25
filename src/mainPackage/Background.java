package mainPackage;

import javax.imageio.ImageIO;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Fabian on 2018-10-25.
 */
public class Background implements GameObject {
    int width,height;
    Color color;
    Image img;

    public Background(Color c,int width, int height) {
        color = c;
        this.width=width;
        this.height=height;
        try {
            img = ImageIO.read(new File("res/Background.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void render(Graphics g) {
        //g.setColor(color);
        //g.fillRect(0,0,width,height);

        g.drawImage(img,0,0,null);

    }
}
