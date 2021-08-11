import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 *
 * @author Ariel Drellich 328935275
 */
public class MultipleBouncingBallsAnimation {

    /**
     * @param args recieves arguments from the command line
     */
    private static void drawBalls(String[] args) {
        int width = 600, height = 600;
        GUI gui = new GUI("MultipleBouncingBalls", width, height);
        Sleeper sleeper = new Sleeper();
        Ball[] ballArr = new Ball[args.length];
        Random rand = new Random();
        Point zeroPoint = new Point(0, 0);
        for (int i = 0; i < args.length; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            int radius = Integer.parseInt(args[i]);
            int speed;
            if (radius > 50) {
                speed = 5;
            } else {
                speed = 55 - radius;
            }
            Color randomColor = new Color(r, g, b);
            Ball ball = new Ball(rand.nextInt(width - (2 * (radius + speed))) + speed + radius + 1,
                    rand.nextInt(height - (2 * (radius + speed))) + speed + radius + 1, radius, randomColor);
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(), speed);
            ball.setVelocity(v);
            ballArr[i] = ball;
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < args.length; i++) {
                Ball ball = ballArr[i];
                ball.moveOneStep(width, height, zeroPoint);
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

    /**
     * runs the drawBalls method.
     * @param args recieves arguments from the command line
     */
    public static void main(String[] args) {
        drawBalls(args);

    }
}
