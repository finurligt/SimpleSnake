package mainPackage;

import java.awt.event.KeyEvent;
import java.util.Collection;

public interface Game {
    public Collection<GameObject> getGraphics();


    void handleKey(KeyEvent ke);
}
