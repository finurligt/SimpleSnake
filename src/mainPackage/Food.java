package mainPackage;

import java.awt.*;

/**
 * Created by Fabian on 2018-10-28.
 */
public class Food implements GameObject {
    public int x,y;
    int offsetX,offsetY,gridSize;
    public Food(int x, int y, int offsetX, int offsetY, int gridSize) {
        this.x = x;
        this.y = y;
        this.gridSize=gridSize;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(new Color(29,38,0));
        //g.fillRect(x*gridSize+offsetX,y*gridSize+offsetY,gridSize,gridSize);
        g.drawImage(ImageLoader.get(ImageLoader.PEAR),x*gridSize+offsetX,y*gridSize+offsetY,null);
    }
}
