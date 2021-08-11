import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.List;
import java.awt.Color;

/**
 * creates teo frames, a gray and a yellow, and has balls bouncing around in
 * each set by the command line.
 * @author Ariel Drellich 328935275
 */
public class SimpleGuiTest1 {

    /**
     * generates the balls based off size recieved in command line, and draws the
     * surface.
     */
    private static void guiTest() {
        int width = 800, height = 650;
        GUI gui = new GUI("SimpleGuiTest1", width, height);
        Sleeper sleeper = new Sleeper();
        GameEnvironment gameEnvironment = new GameEnvironment();
        Block b1 = new Block(new Rectangle(new Point(500, 500), 30, 10), Color.BLUE);
        Block b2 = new Block(new Rectangle(new Point(400, 200), 30, 10), Color.BLUE);
        Block top = new Block(new Rectangle(new Point(0, 0), 800, 10), Color.RED);
        Block left = new Block(new Rectangle(new Point(0, 10), 10, 630), Color.RED);
        Block right = new Block(new Rectangle(new Point(790, 10), 10, 630), Color.RED);
        Block bottom = new Block(new Rectangle(new Point(0, 640), 800, 10), Color.RED);
        gameEnvironment.addCollidable(b1);
        gameEnvironment.addCollidable(b2);
        gameEnvironment.addCollidable(top);
        gameEnvironment.addCollidable(left);
        gameEnvironment.addCollidable(right);
        gameEnvironment.addCollidable(bottom);
        Ball ball = new Ball(150, 150, 5, java.awt.Color.GRAY, gameEnvironment);
        ball.setVelocity(0, 55);
        List<Collidable> collidables = gameEnvironment.getCollidablesList();

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < collidables.size(); i++) {
                collidables.get(i).drawOn(d);
            }
            ball.moveOneStep();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

    /**
     * runs the drawFramesBalls method.
     * @param args recieves arguments from command line, specifically ball radii.
     */
    public static void main(String[] args) {
        guiTest();

    }
}
