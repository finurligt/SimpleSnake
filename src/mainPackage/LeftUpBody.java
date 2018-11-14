package mainPackage;

import java.awt.*;

public class LeftUpBody extends BodyPart {
    public LeftUpBody(int x, int y, int offsetX, int offsetY, int gridSize) {
        super(x,y,offsetX,offsetY,gridSize);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(ImageLoader.get(ImageLoader.BODY_LEFT_UP),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
    }
}
