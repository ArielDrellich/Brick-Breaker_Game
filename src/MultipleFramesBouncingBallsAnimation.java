import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 * creates teo frames, a gray and a yellow, and has balls bouncing around in each set by the command line.
 * @author Ariel Drellich 328935275
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * generates the balls based off size recieved in command line, and draws the surface.
     * @param args recieves arguments from command line
     */
    private static void drawFramesBalls(String[] args) {
        int width = 800, height = 650;
        GUI gui = new GUI("MultipleBouncingBalls", width, height);
        Sleeper sleeper = new Sleeper();
        Ball[] ballArr = new Ball[args.length];
        Random rand = new Random();

        Point grayStart = new Point(50, 50);
        Point grayEnd = new Point(500, 500);
        int grayWidth = (int) (grayEnd.getX() - grayStart.getX());
        int grayHeight = (int) (grayEnd.getY() - grayStart.getY());

        Point yellowStart = new Point(450, 450);
        Point yellowEnd = new Point(600, 600);
        int yellowWidth = (int) (yellowEnd.getX() - yellowStart.getX());
        int yellowHeight = (int) (yellowEnd.getY() - yellowStart.getY());

        for (int i = 0; i < args.length; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            int radius = Integer.parseInt(args[i]);
            Color randomColor = new Color(r, g, b);
            int speed;
            if (radius > 50) {
                speed = 5;
            } else {
                speed = (int) (55 - radius);
            }
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(), speed);
            if (i < args.length / 2) {
                Ball ball = new Ball(
                        rand.nextInt(grayWidth - (2 * (radius + (int) v.getX())))
                         + radius + (int) v.getX() + (int) grayStart.getX() + 1,
                        rand.nextInt(grayHeight - (2 * (radius + (int) v.getY())))
                         + (int) v.getY() + radius + (int) grayStart.getY(), radius + 1,
                        randomColor);
                ball.setVelocity(v);
                ballArr[i] = ball;
            } else {
                Ball ball = new Ball(
                        rand.nextInt(yellowWidth - (2 * (radius + speed)))
                         + radius + speed + (int) yellowStart.getX() + 1,
                        rand.nextInt(yellowHeight - (2 * (radius + speed)))
                         + radius + speed + (int) yellowStart.getY() + 1,
                        radius, randomColor);
                ball.setVelocity(v);
                ballArr[i] = ball;
            }

        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(java.awt.Color.GRAY);
            d.fillRectangle((int) grayStart.getX(), (int) grayStart.getY(), grayWidth, grayHeight);
            d.setColor(java.awt.Color.YELLOW);
            d.fillRectangle((int) yellowStart.getX(), (int) yellowStart.getY(), yellowWidth, yellowHeight);

            for (int i = 0; i < args.length; i++) {
                Ball ball = ballArr[i];
                if (i < args.length / 2) {
                    ball.moveOneStep(grayWidth, grayHeight, grayStart);
                } else {
                    ball.moveOneStep(yellowWidth, yellowHeight, yellowStart);
                }
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

    /**
     * runs the drawFramesBalls method.
     * @param args recieves arguments from command line, specifically ball radii.
     */
    public static void main(String[] args) {
        drawFramesBalls(args);

    }
}
