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
    int prevDirection;

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
        if (prevDirection==RIGHT) {
            g.drawImage(ImageLoader.get(ImageLoader.HEAD_RIGHT),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
        } else if (prevDirection==DOWN) {
            g.drawImage(ImageLoader.get(ImageLoader.HEAD_DOWN),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
        } else if (prevDirection==LEFT) {
            g.drawImage(ImageLoader.get(ImageLoader.HEAD_LEFT),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
        } else {
            g.drawImage(ImageLoader.get(ImageLoader.HEAD_UP),offsetX +(gridSize*x), offsetY + (y*gridSize),null);

        }
        for(BodyPart b: body) {
            b.render(g);

        }
    }

    public void tick() {

        System.out.println("prevdir " + prevDirection);
        System.out.println("dir " + direction);

        if (prevDirection==RIGHT) {
            if (direction==RIGHT||direction==LEFT) {
                body.addFirst(new HorizontalBody(x, y, offsetX, offsetY, gridSize));
            } else if (direction==DOWN) {
                body.addFirst(new LeftDownBody(x, y, offsetX, offsetY, gridSize));
            } else {
                body.addFirst(new LeftUpBody(x, y, offsetX, offsetY, gridSize));
            }
        } else if (prevDirection==LEFT) {
            if (direction==LEFT||direction==RIGHT) {
                body.addFirst(new HorizontalBody(x, y, offsetX, offsetY, gridSize));
            } else if (direction==DOWN) {
                body.addFirst(new RightDownBody(x, y, offsetX, offsetY, gridSize));
            } else {
                body.addFirst(new RightUpBody(x, y, offsetX, offsetY, gridSize));
            }
        } else if (prevDirection==DOWN) {
            if (direction==RIGHT) {
                body.addFirst(new RightUpBody(x, y, offsetX, offsetY, gridSize));
            } else if (direction==LEFT) {
                body.addFirst(new LeftUpBody(x, y, offsetX, offsetY, gridSize));
            } else {
                body.addFirst(new VerticalBody(x, y, offsetX, offsetY, gridSize));
            }
        } else {
            if (direction==RIGHT) {
                body.addFirst(new RightDownBody(x, y, offsetX, offsetY, gridSize));
            } else if (direction==LEFT) {
                body.addFirst(new LeftDownBody(x, y, offsetX, offsetY, gridSize));
            } else {
                body.addFirst(new VerticalBody(x, y, offsetX, offsetY, gridSize));
            }
        }
            //body.addFirst(new VerticalBody(x, y, offsetX, offsetY, gridSize));

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
        prevDirection=this.direction;
    }

    public void setDirection(int newDirection) {
        this.direction = newDirection;
    }

    public void eat() {
        size++;
    }
}
