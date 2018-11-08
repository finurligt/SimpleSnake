package mainPackage;

import java.awt.event.KeyEvent;
import java.util.Collection;

public abstract class Game extends Thread {
    /** Write game loop in run() and use setGraphics to update the graphics. **/


    private Collection<GameObject> gameObjects;


    public synchronized Collection<GameObject> getGraphics() {
        return gameObjects;
    }

    public synchronized void setGraphics(Collection<GameObject> g) {
        gameObjects = g;
    }


    abstract void keyTyped(KeyEvent key);
}
