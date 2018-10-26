package mainPackage;

import java.awt.*;

public class BodyPart implements GameObject {
    int x,y;
    int gridszie;
    int offsetX,offsetY;

    public BodyPart(int x, int y, int offsetX, int offsetY, int gridSize) {
        this.x = x;
        this.y = y;
        this.gridszie=gridSize;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(29,38,0));
        g.fillRect(x*gridszie+offsetX,y*gridszie+offsetY,gridszie,gridszie);
    }
}
