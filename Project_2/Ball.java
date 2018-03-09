/* Importing Java library for Color */

import java.awt.*;

/* Class called Ball inheriting from Class Circle */

public class Ball extends Circle {

    /* Data Members of the Class Ball */

    public Color c;
    public double sX, sY;
    public double ballPositionX, ballPositionY;
    public double deltaX, deltaY;
    public boolean isIntersect = false;

    /* Constructor for the Class Ball */

    public Ball(double xcoord, double ycoord, double radius, Color color){

        super(xcoord, ycoord, radius);

        c = color;
        setColor( color );

    }

    /* Set speed X method */

    public void setSpeedX(double speedX){
        sX = speedX;
    }

    /* Set speed Y method */

    public void setSpeedY(double speedY){
        sY = speedY;
    }

    /* Get speed X method */

    public double getSpeedX(){
        return sX;
    }

    /* Get speed Y method */

    public double getSpeedY(){
        return sY;
    }

    /* Update X, Y Position method */

    public void updatePosition(double time){

        ballPositionX = getXPos() + sX*time;
        ballPositionY = getYPos() + sY*time;

    }

    /* Intersect method to check intersection of two balls */

    public boolean intersect(Ball ball2){

        double distance = Math.sqrt(((this.getXPos() - ball2.getXPos())*(this.getXPos() - ball2.getXPos())) +
                                    ((this.getYPos() - ball2.getYPos())*(this.getYPos() - ball2.getYPos())));

        if (distance <= this.getRadius() + ball2.getRadius()){
            isIntersect = true;
        }
        else{
            isIntersect = false;
        }
        return isIntersect;

    }

    /* Collide method to set speeds of balls after collision */

    public void collide(Ball ball2){


        if (this.intersect(ball2)){

            double x1 = this.getXPos() - ball2.getXPos();
            double y1 = this.getYPos() - ball2.getYPos();
            double d = Math.sqrt(x1*x1 + y1*y1);
            double deltaX = x1/d ;
            double deltaY = y1/d ;

            double velocityIx = this.getSpeedX();
            double velocityIy = this.getSpeedY();
            double velocityJx = ball2.getSpeedX();
            double velocityJy = ball2.getSpeedY();

            double velocityXafterb2 = (velocityIx*deltaX + velocityIy*deltaY)*deltaX - ((-1)*velocityJx*deltaY+velocityJy*deltaX)*deltaY;
            double velocityYafterb2 = (velocityIx*deltaX + velocityIy*deltaY)*deltaY + ((-1)*velocityJx*deltaY+velocityJy*deltaX)*deltaX;
            double velocityXafterb1 = (velocityJx*deltaX + velocityJy*deltaY)*deltaX - ((-1)*velocityIx*deltaY+velocityIy*deltaX)*deltaY;
            double velocityYafterb1 = (velocityJx*deltaX + velocityJy*deltaY)*deltaY + ((-1)*velocityIx*deltaY+velocityIy*deltaX)*deltaX;

            this.setSpeedX(velocityXafterb1);
            this.setSpeedY(velocityYafterb1);
            ball2.setSpeedX(velocityXafterb2);
            ball2.setSpeedY(velocityYafterb2);


        }
    }
}
