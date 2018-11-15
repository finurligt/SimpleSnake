package mainPackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader {

    public static final String HEAD_RIGHT = "res/headRight.gif",
            HEAD_UP = "res/headUp.gif",
            HEAD_LEFT = "res/headLeft.gif",
            HEAD_DOWN = "res/headDown.gif",
            BODY_HORIZONTAL = "res/bodyHorizontal.gif",
            BODY_VERTICAL = "res/bodyVertical.gif",
            BODY_LEFT_UP = "res/bodyLeftUp.gif",
            BODY_RIGHT_UP = "res/bodyRightUp.gif",
            BODY_RIGHT_DOWN = "res/bodyRightDown.gif",
            BODY_LEFT_DOWN = "res/bodyLeftDown.gif",
            TAIL_RIGHT = "res/tailRight.gif",
            TAIL_LEFT = "res/tailLeft.gif",
            TAIL_UP = "res/tailUp.gif",
            TAIL_DOWN = "res/tailDown.gif";


    private static HashMap<String, Image> images = new HashMap<>();

    private ImageLoader() {
        //prevent instance of this class
    }

    public static Image get(String key) {
        if (!images.containsKey(key)) {
            try {
                images.put(key, ImageIO.read(new File(key)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images.get(key);
    }

}
