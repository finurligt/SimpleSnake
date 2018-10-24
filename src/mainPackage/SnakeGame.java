package mainPackage;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.LinkedList;

class SnakeGame implements Game {

    final int NANOS_IN_TICK = 1000000000;
    LinkedList<GameObject> gameObjectsList;
    long prevTickTime;
    Head head;

    public SnakeGame() {
        gameObjectsList = new LinkedList<GameObject>();
        prevTickTime=System.nanoTime();
        head = new Head(0,0);
    }

    @Override
    public Collection<GameObject> getGraphics() {
        long timePassed = System.nanoTime() - prevTickTime;
        if (timePassed>NANOS_IN_TICK) {
            tick();
            prevTickTime+=NANOS_IN_TICK;
        }
        return gameObjectsList;
    }

    @Override
    public void handleKey(KeyEvent ke) {
        System.out.println("hej");
        switch (ke.getKeyCode()) {

            case SparkyKeys.P1_DOWN : {
                System.out.println("hej igen");
                head.setDirection(Head.DOWN);
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
