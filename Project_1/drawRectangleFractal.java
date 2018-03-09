//Ren Jeik Ong 5343975
//William Janklow 5337204

import java.awt.*;

public class drawRectangleFractal {

        public static void drawRectangleFractal(int i, double x, double y , double w, double h, int repeat, Canvas drawing){
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

            Rectangle rec = new Rectangle(x, y, w, h);
            rec.setColor(color);
            drawing.drawShape(rec);
            if (repeat == 1) { //base caser
                return;

            }
            else {
                drawRectangleFractal((i+1)%3,x - (w /2), y+h, w / 2, h / 2, repeat - 1, drawing);
                drawRectangleFractal((i+1)%3,x + (w ), y-h/2, w / 2, h / 2, repeat - 1, drawing);
                drawRectangleFractal((i+1)%3,x + (w ), y+h, w / 2, h / 2, repeat - 1, drawing);
                drawRectangleFractal((i+1)%3,x - (w/2 ), y-h/2, w / 2, h / 2, repeat - 1, drawing);
            }
        }

}
