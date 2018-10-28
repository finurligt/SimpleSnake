package mainPackage;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Fabian on 2018-10-24.
 */
public class Head implements GameObject {
    int offsetX, offsetY;
    int gridSize;
    int x;
    int y;
    public static final int DOWN = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int LEFT = 3;
    int size;
    LinkedList<BodyPart> body;
    int[][] map;

    int direction;
    public Head(int offsetX, int offsetY, int x, int y, int gridSize, int[][] map) {
        body = new LinkedList<BodyPart>();
        direction=RIGHT;
        this.offsetX =offsetX;
        this.offsetY =offsetY;
        this.gridSize=gridSize;
        this.map=map;
        this.x=x;
        this.y=y;
        size =1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(offsetX +(gridSize*x), offsetY + (y*gridSize),gridSize,gridSize);
        for(BodyPart b: body) {
            b.render(g);

        }
    }

    public void tick() {
        body.addFirst(new BodyPart(x, y, offsetX, offsetY, gridSize));
        if(body.size()>size) {
            body.removeLast();
        }
        for(BodyPart b: body) {
            try {
                map[b.x][b.y]=1;
            } catch (ArrayIndexOutOfBoundsException e) {
                //handled in SnakeGame.tick()
            }
        }
        switch (this.direction) {
            case DOWN : {
                y++;
                //System.out.println();
                break;
            }

            case RIGHT : {
                x++;
                break;
            }
            case LEFT : {
                x--;
                break;
            }
            case UP : {
                y--;
                break;
            }
        }
    }

    public void setDirection(int newDirection) {
        this.direction = newDirection;
    }

    public void eat() {
        size++;
    }
}
