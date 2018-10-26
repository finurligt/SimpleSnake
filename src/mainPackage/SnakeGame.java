package mainPackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.LinkedList;

class SnakeGame implements Game {

    final int NANOS_IN_TICK = 1000000000;
    LinkedList<GameObject> gameObjectsList;
    long prevTickTime;
    Head head;
    int width,height;
    int[][] map;
    boolean gameOn;

    public SnakeGame(int width,int height) {
        gameObjectsList = new LinkedList<GameObject>();
        prevTickTime=System.nanoTime();
        head = new Head(10,80,map);
        this.width=width;
        this.height=height;
        map = new int[20][11];
        gameOn=false;
    }

    @Override
    public Collection<GameObject> getGraphics() {
        long timePassed = System.nanoTime() - prevTickTime;
        if (timePassed>NANOS_IN_TICK) {
            tick();
            prevTickTime+=NANOS_IN_TICK;
        }
        gameObjectsList.addFirst(new Background(new Color(134, 179, 0),width,height));
        return gameObjectsList;
    }

    @Override
    public void handleKey(KeyEvent ke) {
        gameOn=true;
        switch (ke.getKeyCode()) {

            case SparkyKeys.P1_DOWN : {
                head.setDirection(Head.DOWN);
                break;
            }
            case SparkyKeys.P1_RIGHT : {
                head.setDirection(Head.RIGHT);
                break;
            }
            case SparkyKeys.P1_LEFT : {
                head.setDirection(Head.LEFT);
                break;
            }
            case SparkyKeys.P1_UP : {
                head.setDirection(Head.UP);
                break;
            }
        }
    }


    private void tick() {
        if(gameOn) {
            map = new int[20][11];
            head.map=map;
            head.tick();
            LinkedList<GameObject> newList = new LinkedList<GameObject>();
            newList.add(head);
            gameObjectsList = newList;

            if (head.x>19 || head.x<0 || head.y>10 || head.y<0) {
                gameOver();
                return;
            }





            switch (map[head.x][head.y]) {
                case 1 :
                    gameOver();
                    break;

            }
        }

    }

    private void gameOver() {
        gameObjectsList.addLast(new GameOver());
        gameOn = false;
    }

}
