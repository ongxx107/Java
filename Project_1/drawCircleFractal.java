//Ren Jeik Ong 5343975
//William Janklow 5337204

import java.awt.*;
import java.util.Random;
//import Canvas;

public class drawCircleFractal {

    public static void drawCircleFractal(int i, double x, double y , double r, int repeat, Canvas drawing){


        Color color;

        if (i == 1) {
            color = Color.RED;
        }
        else if (i == 0) {
            color = Color.BLUE;
        }
        else {
            color = Color.GREEN;
        }

            Circle cir = new Circle(x, y, r);
            cir.setColor(color);
            drawing.drawShape(cir);
            if (repeat == 1) { //base caser
                return;

            }
            else {
                drawCircleFractal((i+1)%3,x-r, y, r / 3, repeat - 1, drawing);
                drawCircleFractal((i+1)%3,x + r, y, r / 3,  repeat - 1, drawing);
                drawCircleFractal((i+1)%3,x , y+r, r / 3, repeat - 1, drawing);
                drawCircleFractal((i+1)%3,x , y-r, r / 3, repeat - 1, drawing);
            }
    }
}
