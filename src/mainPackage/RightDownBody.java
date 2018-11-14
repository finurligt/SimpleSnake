package mainPackage;

import java.awt.*;

public class RightDownBody extends BodyPart {
    public RightDownBody(int x, int y, int offsetX, int offsetY, int gridSize) {
        super(x,y,offsetX,offsetY,gridSize);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(ImageLoader.get(ImageLoader.BODY_RIGHT_DOWN),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
    }
}
