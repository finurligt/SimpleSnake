package mainPackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

class SnakeGame implements Game {

    int nanosInTick = 1000000000;
    LinkedList<GameObject> gameObjectsList;
    long prevTickTime;
    Head head;
    int width,height;
    int[][] map;
    boolean gameOn;
    final int OFFSET_X = 10;
    final int OFFSET_Y = 80;
    final int GRIDSIZE = 31;
    int score = 0;
    Font font;
    Food food = null;
    int mode;
    final int CREDITS = 0;
    final int GAME_ON = 1;
    final int HIGHSCORE = 2;




    public SnakeGame(int width,int height) {
        gameObjectsList = new LinkedList<GameObject>();
        prevTickTime=System.nanoTime();

        head = new Head(OFFSET_X,OFFSET_Y,0,0,GRIDSIZE,map);
        this.width=width;
        this.height=height;
        map = new int[20][11];
        gameOn=false;
        font = fileToFont("res/LCD_Solid.ttf");
    }

    @Override
    public Collection<GameObject> getGraphics() {
        long timePassed = System.nanoTime() - prevTickTime;
        if (timePassed> nanosInTick) {
            tick();
            prevTickTime+= nanosInTick;
        }

        gameObjectsList.addFirst(new Background(new Color(134, 179, 0),width,height));

        return gameObjectsList;
    }

    @Override
    public void handleKey(KeyEvent ke) {
        if (mode==HIGHSCORE) {
            startNewGame();
        } else if (mode==CREDITS) {
            //TODO
            startNewGame();
        }
        switch (ke.getKeyCode()) {

            case SparkyKeys.P1_DOWN : {
                head.setDirection(Head.DOWN);
                break;
            }
            case SparkyKeys.P1_RIGHT : {
                head.setDirection(Head.RIGHT);
                break;
            }
            case SparkyKeys.P1_LEFT : {
                head.setDirection(Head.LEFT);
                break;
            }
            case SparkyKeys.P1_UP : {
                head.setDirection(Head.UP);
                break;
            }
        }
    }



    HighScore highScore;
    private void tick() {
        System.out.println(mode);
        switch (mode) {
            case GAME_ON :
                gameTick();
                break;
            case HIGHSCORE :
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    //skips game over screen
                }
                if (highScore==null) {
                    highScore = new HighScore(font);
                }
                gameObjectsList=new LinkedList<GameObject>();
                gameObjectsList.add(highScore);
                break;
        }


    }

    private void gameTick() {
        LinkedList<GameObject> newList = new LinkedList<GameObject>();
        map = new int[20][11];
        head.map=map;
        head.tick();

        newList.add(head);


        if (head.x>19 || head.x<0 || head.y>10 || head.y<0) {
            gameOver();
            return;
        }

        if (food==null) {
            int foodX = (int)(Math.random()*map.length);
            int foodY = (int)(Math.random()*map[0].length);
            System.out.println("food at ("+ foodX + ", " + foodY + ").");

            food = new Food(foodX,foodY,OFFSET_X,OFFSET_Y,GRIDSIZE);

        }
        map[food.x][food.y] = 2;
        newList.addFirst(food);
        gameObjectsList = newList;
        gameObjectsList.addLast(new ScoreCounter(score,font));

        try {
            switch (map[head.x][head.y]) {
                case 1 :
                    gameOver();
                    break;
                case 2 :
                    eat();
                    System.out.println("nomnom");
                    break;

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            gameOver();
        }
    }

    private void eat() {
        head.eat();
        food=null;
        nanosInTick = (int)(((double)nanosInTick)*0.98);
        System.out.println(nanosInTick);
        score++;
    }

    private void startNewGame() {
        head=new Head(OFFSET_X,OFFSET_Y,0,0,GRIDSIZE,map);
        food=null;
        score=0;
        mode=GAME_ON;
        prevTickTime=System.nanoTime()-nanosInTick; //TODO think this trough lol, still a delay after highscores. might just sleep instead
        nanosInTick = 1000000000;

    }

    private void gameOver() {
        gameObjectsList.addLast(new GameOver());
        mode=HIGHSCORE;



    }

    private Font fileToFont(String fileName) {
        File fontFile = new File(fileName);
        try {
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(25F);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
