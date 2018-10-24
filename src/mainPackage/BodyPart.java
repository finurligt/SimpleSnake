package mainPackage;

import java.awt.*;

public class BodyPart implements GameObject {
    int x,y;

    public BodyPart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x,y,32,32);
    }
}
