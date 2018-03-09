import java.awt.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Canvas drawing = new Canvas(800,800);

        Scanner input = new Scanner(System.in);
        System.out.println("Input circle, triangle, or rectangle. Ex: circle ");
        String s = input.nextLine();

        if(s.equals("circle")) {
            drawCircleFractal myCircle = new drawCircleFractal();
            myCircle.drawCircleFractal(0, 400, 400, 200, 7, drawing);
        }
        else if (s.equals("triangle")){
            drawTriangleFractal myTriangle = new drawTriangleFractal();
            myTriangle.drawTriangleFractal(0, 400,400,200,200,7, drawing);
        }
        else if (s.equals("rectangle")){
            drawRectangleFractal myRectangle = new drawRectangleFractal();
            myRectangle.drawRectangleFractal(0, 400,400,200,100,7,drawing);
        }
        else{
            System.out.println("Error!!!");
        }
    }
}
