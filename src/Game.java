import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Collections of all objects and sprites in the game.
 */
public class Game {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private int guiWidth = 800, guiHeight = 600;
    private GUI gui = new GUI("Brick breaker", guiWidth, guiHeight);
    private biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();

    /**
     * @param c collidable to add to game enviroment.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * @param s sprite to add to Sprites.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them
     * to the game.
     */
    public void initialize() {
        int blockWidth = 50;
        int blockHeight = 30;
        Ball ball1 = new Ball(400, 550, 5, Color.WHITE, environment);
        Ball ball2 = new Ball(500, 550, 5, Color.WHITE, environment);
        ball1.setVelocity(0, -4);
        ball2.setVelocity(5, -2);
        ball1.addToGame(this);
        ball2.addToGame(this);
        Block top = new Block(new Rectangle(new Point(0, 0), 800, 10), Color.GRAY);
        Block left = new Block(new Rectangle(new Point(0, 10), 10, 630), Color.GRAY);
        Block right = new Block(new Rectangle(new Point(790, 10), 10, 630), Color.GRAY);
        Block bottom = new Block(new Rectangle(new Point(0, 590), 800, 10), Color.GRAY);
        Paddle paddle = new Paddle(new Rectangle(new Point(380, 580), 80, 10), Color.YELLOW, keyboard, 10);
        paddle.addToGame(this);
        bottom.addToGame(this);
        right.addToGame(this);
        top.addToGame(this);
        left.addToGame(this);
        for (int i = 0; i < 5; i++) {
            Color color = new Color((int) (Math.random() * 0x1000000));
            for (int j = 0; j < 12 - i; j++) {
                Block block = new Block(
                        new Rectangle(new Point(guiWidth - 10 - blockWidth * (j + 1), 100 + blockHeight * i),
                                blockWidth, blockHeight),
                        color);
                block.addToGame(this);
            }
        }

    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, guiWidth, guiHeight);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}