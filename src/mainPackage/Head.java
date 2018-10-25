package mainPackage;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Fabian on 2018-10-24.
 */
public class Head implements GameObject {
    int x,y;
    int gridSize;
    public static final int DOWN = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int LEFT = 3;
    LinkedList<BodyPart> body;

    int direction;
    public Head(int x, int y) {
        body = new LinkedList<BodyPart>();
        direction=RIGHT;
        this.x=x;
        this.y=y;
        gridSize=31;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x,y,gridSize,gridSize);
        for(BodyPart b: body) {
            b.render(g);
        }
    }

    public void tick() {
        System.out.println("moving to "+ this.direction);
        body.addFirst(new BodyPart(x,y,gridSize));
        if(body.size()>3) {
            body.removeLast();
        }
        switch (this.direction) {
            case DOWN : {
                y+=gridSize;
                //System.out.println();
                break;
            }

            case RIGHT : {
                x+=gridSize;
                break;
            }
            case LEFT : {
                x-=gridSize;
                break;
            }
            case UP : {
                y-=gridSize;
                break;
            }
        }
    }

    public void setDirection(int newDirection) {
        this.direction = newDirection;
        System.out.println("dir is "+direction);
    }
}
