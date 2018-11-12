package mainPackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    private static Image bodyHorizontal, bodyVertical, bodyTurnUpRigh, bodyTurnUpLeft,bodyTurnDownRight,bodyTurnDownLeft,head;
    private ImageLoader() {

    }

    public static void loadAll() {
        try {
            loadHead();
            bodyHorizontal =ImageIO.read(new File("res/snakeBodyHoz.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadHead() throws IOException {
        head = ImageIO.read(new File("res/snakeHead.gif"));
    }
    private static void loadBodyHorizontal() throws IOException {
        bodyHorizontal = ImageIO.read(new File("res/snakeBodHoz.gif"));
    }

    public static Image getBodyHorizontal() {
        if(bodyHorizontal!=null) {
            return bodyHorizontal;
        }
        else {
            try {
                loadBodyHorizontal();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bodyHorizontal;
    }

    public static Image getHead() {
        if(head!=null) {
            return head;
        }
        else {
            try {
                loadHead();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return head;
    }
}
