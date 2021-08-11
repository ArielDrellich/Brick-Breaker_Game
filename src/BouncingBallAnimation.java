import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 *
 * @author Ariel Drellich 328935275
 */
public class BouncingBallAnimation {
    /**
     * bounces ball around the screen.
     * @param start start point of ball
     * @param dx velocity on x axis
     * @param dy velocity on y axis
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        int width = 600, height = 600;
        GUI gui = new GUI("title", width, height);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30, Color.BLACK);
        ball.setVelocity(dx, dy);
        Point zeroPoint = new Point(0, 0);
        while (true) {
            ball.moveOneStep(width, height, zeroPoint);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

    /**
     * runs drawAnimation based on the arguments given.
     * @param args recieves argument from command line, starting point, and velocity.
     */
    public static void main(String[] args) {
        drawAnimation(new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1])), Integer.parseInt(args[2]),
                Integer.parseInt(args[3]));

    }
}
