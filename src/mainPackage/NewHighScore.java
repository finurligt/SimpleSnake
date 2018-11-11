package mainPackage;

import java.awt.*;

public class NewHighScore implements GameObject {
    ArcadeTyping[] arcadeTypings;
    int score;
    Font font;
    private String name;
    int index;

    public NewHighScore(int score, Font font) {
        this.score=score;
        arcadeTypings = new ArcadeTyping[3];
        arcadeTypings[0] = new ArcadeTyping();
        arcadeTypings[1] = new ArcadeTyping();
        arcadeTypings[2] = new ArcadeTyping();
        this.font=font;
        index=0;

    }

    public synchronized String getName() {
        return ""+(char)arcadeTypings[0].c+(char)arcadeTypings[1].c+(char)arcadeTypings[2].c;
    }

    synchronized void right() {
        index++;
        if (index>2) index = 0;
        System.out.println(index);
    }

    synchronized void left() {
        index--;
        if (index<0) index = 2;
        System.out.println(index);
    }

    synchronized void down() {
        arcadeTypings[index].next();
    }

    synchronized void up() {
        arcadeTypings[index].previous();
    }

    @Override
    public synchronized void render(Graphics g) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("NEW HIGH SCORE!",200,150);
        g.drawString("NAME",100,250);
        g.drawString("SCORE",400,250);
        g.drawString(Character.toString((char)arcadeTypings[0].c), 100,350);
        g.drawString(Character.toString((char)arcadeTypings[1].c), 120,350);
        g.drawString(Character.toString((char)arcadeTypings[2].c), 140,350);
        g.drawString(Integer.toString(score),400,350);
    }
    private class ArcadeTyping {

        int c;

        public ArcadeTyping() {
            c = '_';
        }
        public char next() {
            c++;
            if (c==('_'+1)) {
                c='A';
            } else if(c=='Z'+1) {
                c = '0';
            } else if(c=='9' +1) {
                c = '_';
            }
            return (char)c;
        }

        public char previous() {
            c--;
            if (c==('_'-1)) {
                c='9';
            } else if (c=='0'-1) {
                c='Z';
            } else if (c=='A'-1) {
                c='_';
            }
            return (char)c;
        }
    }
}
