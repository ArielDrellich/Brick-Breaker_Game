import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * Shows randomly generated lines along with their intersection points and middles.
 */
public class AbstractArtDrawing {
    /**
     * genertes random lines and show middle and intersection points.
     */
    public void drawLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Lines Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] lineArr = new Line[10];
        for (int i = 0; i < 10; ++i) {
            Point p1 = new Point(rand.nextInt(400) + 1, rand.nextInt(300) + 1); // get integer in range 1-400
            Point p2 = new Point(rand.nextInt(400) + 1, rand.nextInt(300) + 1); // get integer in range 1-400
            d.setColor(Color.BLACK);
            d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            lineArr[i] = new Line(p1, p2);
            d.setColor(Color.BLUE);
            d.fillCircle((int) lineArr[i].middle().getX(), (int) lineArr[i].middle().getY(), 3);
            for (int j = 0; j < i; j++) {
                d.setColor(Color.RED);
                if (lineArr[i].intersectionWith(lineArr[j]) != null) {
                    d.fillCircle((int) lineArr[i].intersectionWith(lineArr[j]).getX(),
                            (int) lineArr[i].intersectionWith(lineArr[j]).getY(), 3);
                }
            }
        }
        gui.show(d);
    }
    /**
     * runs the drawLines method.
     * @param args receives from command line
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawLines();
    }
}