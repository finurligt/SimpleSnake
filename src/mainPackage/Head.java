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
    int size;
    LinkedList<BodyPart> body;
    int[][] map;
    int direction;
    int prevDirection;

    public Head(int offsetX, int offsetY, int x, int y, int gridSize, int[][] map) {
        body = new LinkedList<BodyPart>();
        direction=Directions.RIGHT;
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
        if (prevDirection==Directions.RIGHT) {
            g.drawImage(ImageLoader.get(ImageLoader.HEAD_RIGHT),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
        } else if (prevDirection==Directions.DOWN) {
            g.drawImage(ImageLoader.get(ImageLoader.HEAD_DOWN),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
        } else if (prevDirection==Directions.LEFT) {
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

        body.addFirst(new BodyPart(x, y, offsetX, offsetY, gridSize,direction,prevDirection));

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
            case Directions.DOWN : {
                y++;
                //System.out.println();
                break;
            }

            case Directions.RIGHT : {
                x++;
                break;
            }
            case Directions.LEFT : {
                x--;
                break;
            }
            case Directions.UP : {
                y--;
                break;
            }
        }
        body.getLast().tail=true;
        prevDirection=this.direction;
    }

    public void setDirection(int newDirection) {
        this.direction = newDirection;
    }

    public void eat() {
        size++;
    }
}
