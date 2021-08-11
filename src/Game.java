import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * Collections of all objects and sprites in the game.
 */
public class Game {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private int guiWidth = 800, guiHeight = 600;
    private GUI gui = new GUI("Brick breaker", guiWidth, guiHeight);
    private biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
    private Counter blockCounter = new Counter(0);
    private Counter ballCounter = new Counter(0);
    private Counter score = new Counter(0);

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
     * Removes collidable from the game.
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    };

    /**
     * Removes sprite from game.
     * @param s sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them
     * to the game.
     */
    public void initialize() {
        int blockWidth = 50;
        int blockHeight = 30;
        int wallWidth = 15;
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, guiWidth);
        PrintingHitListener printingHitListener = new PrintingHitListener();
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        BallAdder ballAdder = new BallAdder(this, environment, ballCounter);
        Ball ball1 = new Ball(400, 550, 5, Color.WHITE, environment);
        Ball ball2 = new Ball(500, 550, 5, Color.WHITE, environment);
        Ball ball3 = new Ball(500, 550, 5, Color.WHITE, environment);
        ball1.setVelocity(0, -4);
        ball2.setVelocity(5, -2);
        ball3.setVelocity(2, -3);
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        ballCounter.increase(3);
        Block top = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY);
        Block left = new Block(new Rectangle(new Point(0, 10), wallWidth, 630), Color.GRAY);
        Block right = new Block(new Rectangle(new Point(guiWidth - wallWidth, 10), wallWidth, 630), Color.GRAY);
        Block bottom = new Block(new Rectangle(new Point(0, guiHeight), 800, 10), Color.GRAY);
        bottom.addHitListener(ballRemover);
        Paddle paddle = new Paddle(new Rectangle(new Point(380, 580), 100, 10), Color.YELLOW, keyboard, 10);
        paddle.addToGame(this);
        bottom.addToGame(this);
        right.addToGame(this);
        left.addToGame(this);
        top.addToGame(this);
        scoreIndicator.addToGame(this);
        for (int i = 0; i < 5; i++) {
            Color color = new Color((int) (Math.random() * 0x1000000));
            for (int j = 0; j < 12 - i; j++) {
                Block block;
                // Death block that destroys ball and does not count as block that needs to be destroyed.
                if ((new Random()).nextInt(40) == 7) {
                    block = new Block(new Rectangle(new Point(guiWidth - wallWidth - blockWidth * (j + 1),
                            100 + blockHeight * i), blockWidth, blockHeight), Color.black);
                    block.addHitListener(ballRemover);
                    // Life block that adds ball and does not count as block that needs to be destroyed.
                } else if ((new Random()).nextInt(25) == 7) {
                    block = new Block(new Rectangle(new Point(guiWidth - wallWidth - blockWidth * (j + 1),
                            100 + blockHeight * i), blockWidth, blockHeight), Color.white);
                    block.addHitListener(ballAdder);
                } else {
                    block = new Block(
                            new Rectangle(new Point(guiWidth - wallWidth - blockWidth * (j + 1), 100 + blockHeight * i),
                                    blockWidth, blockHeight),
                            color);
                    blockCounter.increase(1);
                }
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
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
            if (blockCounter.getValue() == 0) {
                score.increase(100);
                gui.close();
            }
            if (ballCounter.getValue() == 0) {
                gui.close();
            }
        }
    }
}