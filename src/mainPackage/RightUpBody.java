package mainPackage;

import java.awt.*;

public class RightUpBody extends BodyPart {
    public RightUpBody(int x, int y, int offsetX, int offsetY, int gridSize) {
        super(x,y,offsetX,offsetY,gridSize);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(ImageLoader.get(ImageLoader.BODY_RIGHT_UP),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
    }
}
