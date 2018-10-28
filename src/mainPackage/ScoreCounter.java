package mainPackage;

import java.awt.*;

/**
 * Created by Fabian on 2018-10-28.
 */
public class ScoreCounter implements GameObject {
    int score;
    Font font;

    ScoreCounter(int score, Font font) {
        this.score = score;
        this.font=font;
    }
    @Override
    public void render(Graphics g) {
        g.setFont(font.deriveFont(25F));

        g.drawString("Score: " + score,10,60);
    }
}
