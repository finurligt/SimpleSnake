package mainPackage;

import java.awt.*;

public class VerticalBody extends BodyPart {

    public VerticalBody(int x, int y, int offsetX, int offsetY, int gridSize) {
        super(x, y, offsetX, offsetY, gridSize);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(ImageLoader.get(ImageLoader.BODY_VERTICAL),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
    }
}
