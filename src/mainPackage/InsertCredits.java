package mainPackage;

import java.awt.*;

public class InsertCredits implements GameObject {
    private boolean showText=false;
    private Font font;
    private int cred;

    public InsertCredits(Font font) {
        this.font=font;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(font);
        if (showText) {
            if (cred<1) {
                g.drawString("INSERT COIN!",220,200);
            } else {
                g.drawString("PRESS START!",220,200);
            }
        }
        g.drawString("CREDIT: " + cred,240,300);
    }

    public void update(int cred) {
        this.cred=cred;
        showText=!showText;
    }
}
