//Ren Jeik Ong 5343975
//William Janklow 5337204

import java.awt.*;
public class Rectangle {

    private double xCoord;
    private double yCoord;
    private double width;
    private double height;
    private Color c;

    public Rectangle(){}
    public Rectangle(double x, double y, double w, double h){
        xCoord = x;
        yCoord = y;
        width = w;
        height = h;
    }
    public double calculatePerimeter(){
        return height+height+width+width;
    }
    public double calculateArea(){
        return width*height;
    }
    public void setColor(Color circle){
        c = circle;
    }
    public void setPos( double x, double y){
        xCoord = x;
        yCoord = y;
    }
    public void setHeight( double h){
        if (height != width) {
            height = h;
        }
    }
    public void setWidth( double w){
        if (width != height) {
            width = w;
        }
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
    public double getHeight(){

        return height;
    }
		public double getWidth(){

        return width;
    }

}
