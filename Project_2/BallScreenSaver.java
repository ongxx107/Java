/* Importing useful Java libraries */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/* Class called BallScreenSaver inheriting from Class AnimationFrame */

public class BallScreenSaver extends AnimationFrame {

    /* Data Members of the Class BallScreenSaver */

    private double ballX, ballY, ballXSpeed, ballYSpeed;
    private int ballSize = 18, numBall = 0;
    private final int START_SPEED = 300;
    private final int BORDER = 30;

    private int fps = 50;
    private Ball[] b = new Ball[numBall];
    private CollisionLogger collisionLogger;
    private static final int COLLISION_BUCKET_WIDTH = 20;
    private int saveCounter = 0;


    /* Constructor for the Class BallScreenSaver */

    public BallScreenSaver(int w, int h, String name, int numberBall) {

        super(w, h, name);

        numBall = numberBall;
        b = new Ball[numBall];

        for (int i = 0; i < numBall; i++) {

            b[i] = new Ball(randdouble(BORDER, (getWidth()-BORDER)), randdouble(BORDER, (getHeight()-BORDER)), ballSize, Color.GREEN);  // initialize the ball

            b[0].setColor(Color.RED);   /* set one ball in red */

        }

        setRandDir(START_SPEED);
        setFPS(50);

        collisionLogger = new CollisionLogger(this.getWidth(), this.getHeight(), COLLISION_BUCKET_WIDTH);

    }

    /* A utility method to get a random double between min and max. */

    public double randdouble(double min, double max) {

        return (Math.random() * (max - min) + min);
    }

    /* Assign random direction for each ball */

    public void setRandDir(double speed) {

        for (int i = 0; i < numBall; i++) {

            double dir = randdouble(0, Math.PI * 2);
            b[i].setSpeedX(Math.cos(dir) * speed);
            b[i].setSpeedY(Math.sin(dir) * speed);

        }
    }

    /* Action method to change velocities and update the colors of colliding balls from GREEN to RED */

    public void action() {

        int i = 0;

        while (i < numBall) {

            b[i].setPos(b[i].getXPos() + b[i].getSpeedX() / getFPS(),b[i].getYPos() + b[i].getSpeedY() / getFPS());

            if (b[i].getXPos() < BORDER || b[i].getXPos() + ballSize > getHeight() - BORDER) {

                b[i].setSpeedX(b[i].getSpeedX() * (-1));

            }

            if (b[i].getYPos() < BORDER || b[i].getYPos() + ballSize > getWidth() - BORDER) {

                b[i].setSpeedY(b[i].getSpeedY() * (-1));

            }

            for (int j = 0; j < numBall; j++) { //nested loop

                if (i != j && b[i].intersect(b[j])) {

                    b[i].collide(b[j]);   //ball 1 collide ball 2

                    collisionLogger.collide(b[i],b[j]);

                    if(((b[i].getColor().equals(Color.RED)) && (b[j].getColor().equals(Color.GREEN))) |
                       ((b[i].getColor().equals(Color.GREEN)) && (b[j].getColor().equals(Color.RED)))) {  //changing color

                        b[j].setColor(Color.RED);
                        b[i].setColor(Color.RED);

                    }

                }
            }

            i++;
        }
    }

    /* Method to set FPS */

    public void setFPS(int newFPS){

        if (newFPS!=0){
            fps=newFPS;
        }

    }

    /* Method to get FPS */

    public int getFPS(){
        return fps;
    }

    /* Method for drawing multiple balls and the border in the animation frame */

    public void draw(Graphics g){

        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());
        g.setColor(Color.white);
        g.drawRect(BORDER,BORDER,getWidth()-BORDER*2,getHeight()-BORDER*2);

        int i = 0;
        while (i < numBall) {
            g.setColor(b[i].getColor());

            g.fillOval((int) b[i].getXPos(), (int)b[i].getYPos(), ballSize, ballSize);
            i++;
        }
    }

    /* Method to process the keyevents like leftarrow press, rightarrow press and p key press */

    protected void processKeyEvent(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) { /* move if left key is pressed */

            System.out.println("Left key pressed...");

            for (int i=0; i < numBall ; i++){
                b[i].setSpeedX(0.9*b[i].getSpeedX());
                b[i].setSpeedY(0.9*b[i].getSpeedY());
            }

        }
        else if (keyCode == KeyEvent.VK_RIGHT) { /* move if right key is pressed */

            System.out.println("Right key pressed...");

            for (int i=0; i < numBall ; i++){
                b[i].setSpeedX(1.1*b[i].getSpeedX());
                b[i].setSpeedY(1.1*b[i].getSpeedY());
            }
        }

        /* This captures the user pressing the "p" key and prints out the current collisionLog to an image.
        	You can use this directly in your implementation. Add other cases to the if/else statement to
        	handle other key events.
        */
        if (e.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_P) {

            System.out.println("P key pressed...");

            EasyBufferedImage image = EasyBufferedImage.createImage(collisionLogger.getNormalizedHeatMap());

            try {
                image.save("heatmap"+saveCounter+".png", EasyBufferedImage.PNG);
                saveCounter++;
            } catch (IOException exc) {
                exc.printStackTrace();
                System.exit(1);
            }

        }
    }


}
