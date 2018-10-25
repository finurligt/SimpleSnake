package mainPackage;


/**
 * Created by Fabian on 2018-10-23.
 */
public class Main {
    static int width = 640;
    static int height = 480;
    public static void main(String[] args) {
        Game game = new SnakeGame(width,height);
        Window window = new Window(game,640,480);
        window.run();
    }


}
