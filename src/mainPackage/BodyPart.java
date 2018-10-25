package mainPackage;

import java.awt.*;

public class BodyPart implements GameObject {
    int x,y;
    int gridszie;

    public BodyPart(int x, int y,int gridSize) {
        this.x = x;
        this.y = y;
        this.gridszie=gridSize;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(24,33,0));
        g.fillRect(x,y,gridszie,gridszie);
    }
}
