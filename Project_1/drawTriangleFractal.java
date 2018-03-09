//Ren Jeik Ong 5343975
//William Janklow 5337204

import java.awt.*;
//import Canvas;

public class drawTriangleFractal {

        public static void drawTriangleFractal(int i, double x, double y , double w,double h, int repeat, Canvas drawing){

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

            Triangle tri = new Triangle(x, y, w, h);
            tri.setColor(color);
            drawing.drawShape(tri);

            if (repeat == 1) { //base case
                return;
            }
            else {
                drawTriangleFractal((i+1)%3,x - (w / 2), y, w / 2, h / 2, repeat - 1, drawing);
                drawTriangleFractal((i+1)%3,x + 2 * (w / 2), y, w / 2, h / 2, repeat - 1, drawing);
                drawTriangleFractal((i+1)%3,x + (w / 4), (y - (7 / 4) * h), w / 2, h / 2, repeat - 1, drawing);
            }
        }
    }
