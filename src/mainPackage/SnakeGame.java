package mainPackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

class SnakeGame extends Game {
    private ImageLoader il;
    int cred;
    int START_NANOS_IN_TICK = 500000000;
    int nanosInTick = START_NANOS_IN_TICK;
    LinkedList<GameObject> gameObjectsList;
    LinkedList<Score> highScores;
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
    final int HIGH_SCORE = 2;
    final int NEW_HIGH_SCORE = 3;
    final int GAME_OVER = 4;
    private NewHighScore newHighScore;


    public SnakeGame(int width,int height) {
        System.out.println("Loading resources");
        System.out.println("Starting game");
        gameObjectsList = new LinkedList<GameObject>();
        prevTickTime=System.nanoTime();

        head = new Head(OFFSET_X,OFFSET_Y,0,0,GRIDSIZE,map);
        this.width=width;
        this.height=height;
        map = new int[20][11];
        gameOn=false;
        font = fileToFont("res/LCD_Solid.ttf");
        highScores = readHighScore(new File("res/HighScore.txt"));
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
    public void keyTyped(KeyEvent ke) {
        if (ke.getKeyCode()==SparkyKeys.CRED) {
            addCred();
        }
        switch (mode) {
            case GAME_ON :
                switch (ke.getKeyCode()) {

                    case SparkyKeys.P1_DOWN : {
                        head.setDirection(Directions.DOWN);
                        break;
                    }
                    case SparkyKeys.P1_RIGHT : {
                        head.setDirection(Directions.RIGHT);
                        break;
                    }
                    case SparkyKeys.P1_LEFT : {
                        head.setDirection(Directions.LEFT);
                        break;
                    }
                    case SparkyKeys.P1_UP : {
                        head.setDirection(Directions.UP);
                        break;
                    }
                    case SparkyKeys.CRED : {
                        addCred();
                    }
                }
                break;
            case HIGH_SCORE :
                mode=CREDITS;
                break;
            case CREDITS :
                if (ke.getKeyCode()==SparkyKeys.START1) {
                    if (cred>0) {
                        useCred();
                        startNewGame();
                    }
                }
                break;
            case GAME_OVER :
                mode=HIGH_SCORE;
                break;
            case NEW_HIGH_SCORE :
                switch (ke.getKeyCode()) {
                    case SparkyKeys.P1_DOWN :
                        newHighScore.down();
                        break;
                    case SparkyKeys.P1_LEFT :
                        newHighScore.left();
                        break;
                    case SparkyKeys.P1_RIGHT :
                        newHighScore.right();
                        break;
                    case SparkyKeys.P1_UP :
                        newHighScore.up();
                        break;
                    case SparkyKeys.P1_A :
                        String nam = newHighScore.getName();
                        if (nam.charAt(0)=='_') {break;}
                        setNewHighScore(newHighScore.getName(),score);
                        mode=HIGH_SCORE;
                        break;


                }
                break;
        }
    }

    private synchronized void addCred() {
        cred++;
        insertCredits.update(cred);
    }
    private synchronized void useCred() {
        cred--;
    }

    private void setNewHighScore(String name, int score) {
        for(int i = 0; i<3;i++) {
            if (score>highScores.get(i).score) {
                highScores.add(i,new Score(name,score));
                highScores.removeLast();
                break;
            }
        }
        saveHighScores(highScores, "res/HighScore.txt");
    }

    private void saveHighScores(LinkedList<Score> highScores, String filePath) {
        PrintWriter printer;
        try {
            printer = new PrintWriter(filePath);
            for (Score score : highScores) {
                printer.println(score.name+" "+score.score);
            }
            printer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    HighScore highScoreGraphic;
    long timer;
    InsertCredits insertCredits;
    private void tick() {
        switch (mode) {
            case GAME_ON :
                gameTick();
                break;
            case HIGH_SCORE:



                if (highScoreGraphic ==null) {
                    highScoreGraphic = new HighScore(highScores,font);
                }
                gameObjectsList=new LinkedList<GameObject>();
                gameObjectsList.add(highScoreGraphic);
                if (System.currentTimeMillis()- timer >1000*3) mode=CREDITS;
                break;
            case GAME_OVER :
                if (System.currentTimeMillis()- timer >1000*3) {
                    mode=HIGH_SCORE;
                    timer=System.currentTimeMillis();
                }
                break;
            case NEW_HIGH_SCORE :
                if (System.currentTimeMillis()- timer >1000*3) {
                    gameObjectsList=new LinkedList<GameObject>();
                    gameObjectsList.add(newHighScore);

                }
                break;
            case CREDITS :
                if (insertCredits==null) insertCredits = new InsertCredits(font);
                insertCredits.update(cred);
                gameObjectsList=new LinkedList<>();
                gameObjectsList.add(insertCredits);
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
            food=makeFood();

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
                    gameObjectsList.removeFirst();
                    break;

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            gameOver();
        }
    }

    private Food makeFood() {
        LinkedList<Tuple> positions= new LinkedList<Tuple>();
        for(int x = 0; x<map.length;x++) {
            for(int y = 0;y<map[0].length;y++) {
                if(map[x][y]==0) positions.add(new Tuple(x,y));
            }
        }
        int random = (int)(Math.random()*positions.size());
        Tuple t = positions.get(random);
        return new Food(t.x,t.y,OFFSET_X,OFFSET_Y,GRIDSIZE);
    }

    private void eat() {
        head.eat();
        food=null;
        nanosInTick = (int)(((double)nanosInTick)*0.98);
        System.out.println(nanosInTick);
        score++;
    }

    private void startNewGame() {
        head=new Head(OFFSET_X,OFFSET_Y,10,5,GRIDSIZE,map);
        food=null;
        score=0;
        mode=GAME_ON;
        prevTickTime=System.nanoTime()-nanosInTick; //TODO think this trough lol, still a delay after highscores. might just sleep instead
        nanosInTick = START_NANOS_IN_TICK;

    }

    private void gameOver() {
        gameObjectsList.addLast(new GameOver());
        if(score<=highScores.getLast().score) {
            mode= GAME_OVER;
        } else {
            newHighScore = new NewHighScore(score,font);
            mode=NEW_HIGH_SCORE;
        }
        timer =System.currentTimeMillis(); //timer for the Game Over screen


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

    private LinkedList<Score> readHighScore(File scoreFile) { //TODO make highscore graphics read from this instead of the file
        LinkedList<Score> result = new LinkedList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(scoreFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split(" ");
            Score score = new Score(split[0], Integer.parseInt(split[1]));
            result.add(score);
        }
        //System.out.println(result);
        sc.close();
        return result;
    }


    private class Tuple {
        int x,y;
        public Tuple(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
}
