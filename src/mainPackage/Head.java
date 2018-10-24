package mainPackage;

import java.awt.*;

/**
 * Created by Fabian on 2018-10-24.
 */
public class Head implements GameObject {
    int x,y;
    public static final int DOWN = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int LEFT = 3;

    int direction;
    public Head(int x, int y) {
        direction=RIGHT;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x,y,32,32);
    }

    public void tick() {
        System.out.println("moving to "+ this.direction);
        switch (this.direction) {
            case DOWN : {
                y+=32;
                //System.out.println();
                break;
            }

            case RIGHT : {
                x+=32;
                break;
            }
        }
    }

    public void setDirection(int newDirection) {
        this.direction = newDirection;
        System.out.println("dir is "+direction);
    }
}
