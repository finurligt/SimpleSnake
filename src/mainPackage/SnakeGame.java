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

    public SnakeGame(int width,int height) {
        gameObjectsList = new LinkedList<GameObject>();
        prevTickTime=System.nanoTime();
        head = new Head(10,80);
        this.width=width;
        this.height=height;
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
        head.tick();
        LinkedList<GameObject> newList = new LinkedList<GameObject>();
        newList.add(head);
        gameObjectsList = newList;
        System.out.println("tick");

    }

}
