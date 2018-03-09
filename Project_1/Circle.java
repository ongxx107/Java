//Ren Jeik Ong 5343975
//William Janklow 5337204

import java.awt.*;

public class Circle {

    private double xCoord;
    private double yCoord;
    private double r;
    private Color c;

    public Circle(){}
    public Circle(double x, double y, double radius){
        xCoord = x;
        yCoord = y;
        r = radius;
    }
    public double calculatePerimeter(){
        return 2*Math.PI * r;
    }
    public double calculateArea(){
        return Math.PI *r*r;
    }
    public void setColor(Color circle){
        c = circle;
    }
    public void setPos( double x, double y){
        xCoord = x;
        yCoord = y;
    }
    public void setRadius( double radius){
        r = radius;
    }
    public Color getColor(){
        return c;
    }
    public double getXPos(){
        return xCoord;
    }
    public double getYPos(){
        return yCoord;
    }
    public double getRadius(){
        return r;
    }

}
