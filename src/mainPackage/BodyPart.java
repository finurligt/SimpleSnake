package mainPackage;

import java.awt.*;

public class BodyPart implements GameObject {
    int x,y;
    int gridSize;
    int offsetX,offsetY;

    public BodyPart(int x, int y, int offsetX, int offsetY, int gridSize) {
        this.x = x;
        this.y = y;
        this.gridSize =gridSize;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(29,38,0));
        g.fillRect(x* gridSize +offsetX,y* gridSize +offsetY, gridSize, gridSize);
    }
}
