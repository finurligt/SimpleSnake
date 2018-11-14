package mainPackage;

import java.awt.*;

public class HorizontalBody extends BodyPart {


    public HorizontalBody(int x, int y, int offsetX, int offsetY, int gridSize) {
        super(x, y, offsetX, offsetY, gridSize);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(ImageLoader.get(ImageLoader.BODY_HORIZONTAL),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
    }
}
