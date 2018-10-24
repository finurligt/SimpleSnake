package mainPackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Collection;

public class Window extends Canvas implements Runnable, KeyListener {
    Game game;
    int FPS = 60;
    int width, height;
    public Window(Game game, int width, int height) {
        super();
        setSize(width,height);
        this.game = game;
        this.width=width;
        this.height=height;
    }



    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent ke) {

        synchronized (this) {
            game.handleKey(ke);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void run() {

        Frame frame = new Frame("SimpleSnake");
        frame.add(this);
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                //graphics.stop();
                System.exit(0);
            }
        });
        frame.setVisible(true);

        long time = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double amountOfTicks = 1.5;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                delta--;
            }
            if(true){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer+= 1000;
                //System.out.println("FPS: " + frames);

                frames=0;
            }
        }

        //TODO write better game loop
    }

    private void render() {




        Image img = createImage(width,height);
        Graphics bufferImage = img.getGraphics();
        bufferImage.setColor(Color.BLACK);
        bufferImage.fillRect(0, 0, getWidth() , getHeight());
        bufferImage.setColor(Color.WHITE);

        Collection<GameObject> gameObjects = game.getGraphics();
        if (gameObjects != null) {
            for (GameObject gameObject : gameObjects) {
                gameObject.render(bufferImage);
            }
        } else {
            System.out.println("no game objects");
        }




        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(img,0,0,this);
        g.dispose();
        bs.show();

    }



    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }


}
