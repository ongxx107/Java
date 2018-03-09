import java.awt.*;
public class Triangle {

    private double xCoord;
    private double yCoord;
    private double width;
		private double height;
    private Color c;

    public Triangle(){}
    public Triangle(double x, double y, double w, double h){
        xCoord = x;
        yCoord = y;
    		width = w;
				height = h;
    }
    public double calculatePerimeter(){
				double side = Math.sqrt(Math.pow((width/2),2)+ Math.pow(height, 2));
        return width + side +side;
    }
    public double calculateArea(){
        return width/2*height *2;
    }
    public void setColor(Color circle){
        c = circle;
    }
    public void setPos( double x, double y){
        xCoord = x;
        yCoord = y;
    }
    public void setHeight( double h){
        height =h;
    }
		public void setWidth( double w){
        width =w;
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
