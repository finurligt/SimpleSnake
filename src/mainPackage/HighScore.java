package mainPackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Fabian on 2018-10-29.
 */
public class HighScore implements GameObject {
    Image img;
    File scoreFile;
    LinkedList<Score> highScores;
    Font font;

    public HighScore(LinkedList<Score> highScores, Font font) {
        scoreFile = new File("res/HighScore.txt");
        this.font=font;
        try {
            System.out.println("reading file");
            img = ImageIO.read(new File("res/HighScore.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.highScores = highScores;


    }



    @Override
    public void render(Graphics g) {
        int y=150;

        g.drawImage(img,0,0,null);
        g.setColor(Color.black);
        g.setFont(font);
        for (Score s : highScores) {
            g.drawString(s.name,120,y);
            g.drawString(Integer.toString(s.score),450,y);
            y=y+100;
        }

    }
}
