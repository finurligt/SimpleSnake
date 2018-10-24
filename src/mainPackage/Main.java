package mainPackage;


/**
 * Created by Fabian on 2018-10-23.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new SnakeGame();
        Window window = new Window(game,640,480);
        window.run();
    }


}
