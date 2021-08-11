package game.gameLogic;

import game.geometryLogic.Ball;
import game.geometryLogic.Block;
import game.geometryLogic.Paddle;
import game.geometryLogic.Point;
import game.geometryLogic.Rectangle;
import game.levels.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.listeners.BallAdder;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.listeners.ScoreIndicator;
import game.listeners.ScoreTrackingListener;

import java.awt.Color;

/**
 * Collections of all objects and sprites in the game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private int guiWidth, guiHeight;
    private biuoop.KeyboardSensor keyboard;
    private Counter blockCounter = new Counter(0);
    private Counter ballCounter = new Counter(0);
    private Counter score;
    private boolean running = true;
    private AnimationRunner runner;
    private int ballRadius;
    private LevelInformation levelInformation;
    private int wallWidth;

    /**
     * Constructor.
     * @param levelInformation information to build level.
     * @param keyboard keyboard sensor.
     * @param animationRunner runs animation.
     * @param scoreCounter counts score.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard,
                     AnimationRunner animationRunner, Counter scoreCounter) {
        this.guiWidth = 800;
        this.guiHeight = 600;
        this.ballRadius = 5;
        this.runner = animationRunner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        this.wallWidth = 15;
        this.score = scoreCounter;
    }
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
     *
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }


    /**
     * Removes sprite from game.
     *
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
        levelInformation.getBackground().addToGame(this);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, guiWidth);
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        BallAdder ballAdder = new BallAdder(this, environment, ballCounter);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        for (Block blocks : levelInformation.blocks()) {
            Block block = blocks;
            if (block.getColor() == Color.black) {
                block.addHitListener(ballRemover);
            }
            if (block.getColor() == Color.magenta) {
                block.addHitListener(ballAdder);
            }
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
        }
        blockCounter.increase(levelInformation.numberOfBlocksToRemove());
        Paddle paddle = new Paddle(new Rectangle(new Point((guiWidth / 2) - (levelInformation.paddleWidth() / 2),
                580), levelInformation.paddleWidth(), 10), Color.YELLOW, keyboard, levelInformation.paddleSpeed());
        paddle.addToGame(this);
        Block top = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY);
        Block left = new Block(new Rectangle(new Point(0, 10), wallWidth, 630), Color.GRAY);
        Block right = new Block(new Rectangle(new Point(guiWidth - wallWidth, 10), wallWidth, 630), Color.GRAY);
        Block bottom = new Block(new Rectangle(new Point(0, guiHeight), 800, 10), Color.GRAY);
        bottom.addToGame(this);
        right.addToGame(this);
        left.addToGame(this);
        top.addToGame(this);
        bottom.addHitListener(ballRemover);
        scoreIndicator.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.createBallsOnTopOfPaddle();
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.runner.run(this);
    }

    /**
     * creates balls on top of paddle.
     */
    private void createBallsOnTopOfPaddle() {
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball((guiWidth / 2) - ((levelInformation.numberOfBalls() - 1) * ballRadius)
                    + (i * (ballRadius * 2)), 550, ballRadius, Color.WHITE, environment);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
        ballCounter.increase(levelInformation.numberOfBalls());
    }

    /**
     * Draws the game.
     * @param d surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        d.drawText(guiWidth - 100,  18, levelInformation.levelName(), 18);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", (new PauseScreen())));
        }
        if (blockCounter.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
        if (ballCounter.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     *
     * @return state to stop or not.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     *
     * @return amount of balls left.
     */
    public int ballsLeft() {
        return ballCounter.getValue();
    }
}