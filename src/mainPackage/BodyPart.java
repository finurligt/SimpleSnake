package mainPackage;

import java.awt.*;

public  class BodyPart implements GameObject {
    int x,y;
    int gridSize;
    int offsetX,offsetY;
    int direction,prevDirection;
    public boolean tail=false;

    public BodyPart(int x, int y, int offsetX, int offsetY, int gridSize, int direction,int prevDirection) {
        this.x = x;
        this.y = y;
        this.gridSize =gridSize;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.direction=direction;
        this.prevDirection=prevDirection;
    }


    @Override
    public void render(Graphics g) {
        if(tail) {
            switch (direction) {
                case Directions.RIGHT :
                    g.drawImage(ImageLoader.get(ImageLoader.TAIL_RIGHT),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                    break;
                case Directions.LEFT :
                    g.drawImage(ImageLoader.get(ImageLoader.TAIL_LEFT),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                    break;
                case Directions.DOWN :
                    g.drawImage(ImageLoader.get(ImageLoader.TAIL_DOWN),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                    break;
                case Directions.UP :
                    g.drawImage(ImageLoader.get(ImageLoader.TAIL_UP),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                    break;
            }
        } else {
            if (prevDirection==Directions.RIGHT) {
                if (direction==Directions.RIGHT||direction==Directions.LEFT) {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_HORIZONTAL),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                } else if (direction==Directions.DOWN) {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_LEFT_DOWN),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                } else {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_LEFT_UP),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                }
            } else if (prevDirection==Directions.LEFT) {
                if (direction==Directions.LEFT||direction==Directions.RIGHT) {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_HORIZONTAL),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                } else if (direction==Directions.DOWN) {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_RIGHT_DOWN),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                } else {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_RIGHT_UP),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                }
            } else if (prevDirection==Directions.DOWN) {
                if (direction==Directions.RIGHT) {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_RIGHT_UP),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                } else if (direction==Directions.LEFT) {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_LEFT_UP),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                } else {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_VERTICAL),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                }
            } else {
                if (direction==Directions.RIGHT) {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_RIGHT_DOWN),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                } else if (direction==Directions.LEFT) {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_LEFT_DOWN),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                } else {
                    g.drawImage(ImageLoader.get(ImageLoader.BODY_VERTICAL),offsetX +(gridSize*x), offsetY + (y*gridSize),null);
                }
            }
        }
    }
}
