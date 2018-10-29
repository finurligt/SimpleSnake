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
    LinkedList<String> highScores;
    Font font;

    public HighScore(Font font) {
        scoreFile = new File("res/HighScore.txt");
        this.font=font;
        try {
            System.out.println("reading file");
            img = ImageIO.read(new File("res/HighScore.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        highScores = readHighScores();


    }

    private LinkedList<String> readHighScores() {
        LinkedList<String> highScores = new LinkedList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(scoreFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {   //TODO: maybe dont do this after every single game
            highScores.add(sc.nextLine());
        }
        return highScores;
    }

    @Override
    public void render(Graphics g) {
        int y=150;

        g.drawImage(img,0,0,null);
        g.setColor(Color.black);
        g.setFont(font);
        for (String s : highScores) {
            g.drawString(s,120,y);
            y=y+100;
        }

    }
}
